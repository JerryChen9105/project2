package au.edu.unimelb.swen.oop.mokepon.controller;

import au.edu.unimelb.swen.oop.mokepon.app.ScreenManager;
import au.edu.unimelb.swen.oop.mokepon.app.ScreenState;
import au.edu.unimelb.swen.oop.mokepon.data.GameData;
import au.edu.unimelb.swen.oop.mokepon.data.TileLoader;
import au.edu.unimelb.swen.oop.mokepon.driven.model.Mokepon;
import au.edu.unimelb.swen.oop.mokepon.driven.overworld.*;
import au.edu.unimelb.swen.oop.mokepon.event.EnterBattleEvent;
import au.edu.unimelb.swen.oop.mokepon.event.EventBus;
import au.edu.unimelb.swen.oop.mokepon.event.ExitOverworldEvent;
import au.edu.unimelb.swen.oop.mokepon.state.GameState;
import au.edu.unimelb.swen.oop.mokepon.state.ItemType;
import au.edu.unimelb.swen.oop.mokepon.util.overworld.*;
import au.edu.unimelb.swen.oop.mokepon.util.random.RandomSource;
import au.edu.unimelb.swen.oop.mokepon.view.MokeponOverworldView;
import javafx.scene.Node;

import java.util.ArrayList;

public class MokeponOverworldController implements ScreenController {


    private MokeponOverworldView view;
    private GameState gameState;
    private EventBus eventBus;
    private RandomSource randomSource;
    private ScreenManager screenManager;

    private ArrayList<ArrayList<Character>> tileMatrix;
    private OverworldSystem overworldSystem;


    public MokeponOverworldController(MokeponOverworldView view,
                                      GameState gameState,
                                      EventBus eventBus,
                                      RandomSource randomSource,
                                      ScreenManager screenManager) {
        this.view = view;
        this.gameState = gameState;
        this.eventBus = eventBus;
        this.randomSource = randomSource;
        this.screenManager = screenManager;

        TileLoader tileLoader = new TileLoader(GameData.getGameData());
        this.tileMatrix = new ArrayList<>(tileLoader.loadAll().values());

        initOverworldSys();
        view.getMovementView().setUpAction(this::onUpClicked);
        view.getMovementView().setDownAction(this::onDownClicked);
        view.getMovementView().setLeftAction(this::onLeftClicked);
        view.getMovementView().setRightAction(this::onRightClicked);

        view.getEncounterMarkerView().setRunButton(this::onRunAwayClicked);
        view.getEncounterMarkerView().setFightButton(this::onFightClicked);

        view.getExitMarkerView().setReturnButton(this::onReturnClicked);
        view.getExitMarkerView().setQuitButton(this::onQuitClicked);

        gameState.fightCountProperty().addListener(_ -> {
            if (gameState.getEnemyMokepon() == null) return;
            view.getStatusBarView().setStatusText("You beat the enemy " + gameState.getEnemyMokepon().getName() + "!");
            view.getTileView().popEntity(overworldSystem.getPlayerCoordinate());
            overworldSystem.checkOutEnemyType();
        });
    }


    @Override
    public void init() {
        view.getExitMarkerView().setVisible(false);
        view.getEncounterMarkerView().setVisible(false);
        view.getStatusBarView().clear();
        view.getMovementView().setDisable(false);

        if (!gameState.isOverworldIsEnded()) return;

        initOverworldSys();

        view.getTileView().clear();
        view.getTileView().setTile(tileMatrix);
        view.getTileView().putPlayer(PlayerInfoLoader.getStartingCoordinate());

        EnemyInfoLoader.getCoordinateEnemyMap().forEach((coordinate, enemyType) -> {
            view.getTileView().putEntity(coordinate, EnemyInfoLoader.getEnemyImageView(enemyType));
        });
        ItemInfoLoader.getCoordinateItemMap().forEach((coordinate, itemType) -> {
            view.getTileView().putEntity(coordinate, ItemInfoLoader.getItemImgView());
        });
        view.getTileView().putEntity(ExitInfoLoader.getExitCoordinate(), ExitInfoLoader.getExitImgView());


        view.getPlayerStatisticView().setMokeponStatistic(gameState.selectedMokeponProperty());
        view.getPlayerStatisticView().setInventoryStatistic(gameState.getInventory());
        view.getPlayerStatisticView().setFightCount(gameState.fightCountProperty());


        gameState.setOverworldIsEnded(false);
    }

    private void initOverworldSys() {
        Coordinate playerCoordinate = PlayerInfoLoader.getStartingCoordinate();
        Coordinate exitCoordinate = ExitInfoLoader.getExitCoordinate();

        this.overworldSystem = new OverworldSystem(tileMatrix,
                playerCoordinate, exitCoordinate,
                EnemyInfoLoader.getCoordinateEnemyMap(),
                ItemInfoLoader.getCoordinateItemMap());
    }

    private void onUpClicked() {
        handleMovement(0, -1);
    }
    private void onDownClicked() {
        handleMovement(0, 1);
    }
    private void onLeftClicked() {
        handleMovement(-1, 0);
    }
    private void onRightClicked() {
        handleMovement(1, 0);
    }

    private void handleMovement(int dx, int dy) {
        OverworldStatus overworldStatus = overworldSystem.movePlayer(dx, dy);
        if (playerMoveControl(overworldStatus)) {
            Coordinate realPos = overworldSystem.getPlayerCoordinate();
            view.getTileView().putPlayer(realPos);
        }
    }

    private boolean playerMoveControl(OverworldStatus overworldStatus) {
        view.getStatusBarView().clear();
        switch (overworldStatus) {
            case STOP -> {
                view.getStatusBarView().setStatusText("Illegal path to go!");
                return false;
            }
            case MOVE -> {
                return true;
            }
            case ITEM -> {
                ItemType itemType = overworldSystem.checkOutItemType();
                if (itemType != ItemType.UNKNOWN) {
                    gameState.getInventory().add(itemType);
                    view.getPlayerStatisticView().setInventoryStatistic(gameState.getInventory());
                    view.getStatusBarView().setStatusText("You picked up a " + itemType.name() + "!");
                    view.getTileView().popEntity(overworldSystem.getPlayerCoordinate());
                }
                return true;
            }
            case ENEMY -> {
                view.getMovementView();

                String enemyHint = "";
                int enemyHasAdv = 0;
                EnemyType enemyType = overworldSystem.checkCurEnemyType();
                if (enemyType == EnemyType.Strong) {
                    enemyHint = "Your enemy will be at an advantage here!";
                    enemyHasAdv++;
                } else if (enemyType == EnemyType.Weak) {
                    enemyHint = "Your enemy will be at a disadvantage here!";
                    enemyHasAdv--;
                }

                String selfHint = "";
                int selfHasAdv = TerrainAdvantage.hasTerrainAdvantage(gameState.getPlayerMokepon().getMokeponType(),
                        overworldSystem.checkCurTileType());
                if (selfHasAdv > 0) selfHint = "Your mokepon will be at an advantage here!";
                else if (selfHasAdv < 0) selfHint = "Your mokepon will be at a disadvantage here!";

                ArrayList<Mokepon> enemyList = TypeMokeponLoader.getTypeMokeponList(
                        overworldSystem.checkCurTileType(), enemyHasAdv
                );
                Mokepon enemy = enemyList.get(randomSource.nextInt(0, enemyList.size()));
                gameState.setEnemyMokepon(enemy);

                view.getEncounterMarkerView().setTitle("Encountered a " + enemy.getName());
                view.getEncounterMarkerView().setEnemyHint(enemyHint);
                view.getEncounterMarkerView().setSelfHint(selfHint);
                view.getEncounterMarkerView().setVisible(true);

                view.getStatusBarView().setStatusText("Encountered a wild Mokepon!");

                view.getMovementView().setDisable(true);
                return true;
            }
            case EXIT -> {
                view.getExitMarkerView().setVisible(true);
                view.getExitMarkerView().setTitle("You finished the game! You won " +
                        gameState.fightCountProperty().getValue() +" fights!");
                gameState.setOverworldIsEnded(true);
                return true;
            }
            default -> {
                return false;
            }
        }
    }

    @Override
    public void show() {
        view.getMokeponOverworldRoot().setVisible(true);
    }

    @Override
    public void hide() {
        view.getMokeponOverworldRoot().setVisible(false);
    }

    @Override
    public Node getRoot() {
        return view.getMokeponOverworldRoot();
    }



    private void onQuitClicked() {
        screenManager.terminateGame();
    }

    private void onReturnClicked() {
        gameState.resetForNewSelection();
        gameState.setOverworldIsEnded(true);
        eventBus.publish(new ExitOverworldEvent(gameState));
        screenManager.switchTo(ScreenState.SELECT);
    }

    private void onRunAwayClicked() {
        view.getEncounterMarkerView().setVisible(false);

        view.getMovementView().setDisable(false);
    }

    private void onFightClicked() {
        view.getEncounterMarkerView().setVisible(false);
        double enemyDamageMult = 1;
        EnemyType enemyType = overworldSystem.checkCurEnemyType();
        if (enemyType == EnemyType.Strong) enemyDamageMult += 0.5;
        else if (enemyType == EnemyType.Weak) enemyDamageMult -= 0.5;

        double selfDamageMult = (double)TerrainAdvantage.hasTerrainAdvantage(gameState.getPlayerMokepon().getMokeponType(),
                overworldSystem.checkCurTileType()) / 2 + 1;

        gameState.setSelfDamageMult(selfDamageMult);
        gameState.setEnemyDamageMult(enemyDamageMult);
        eventBus.publish(new EnterBattleEvent(gameState, selfDamageMult, enemyDamageMult));
        screenManager.switchTo(ScreenState.BATTLE);
    }

}



