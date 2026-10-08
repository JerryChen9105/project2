package au.edu.unimelb.swen.oop.mokepon.controller;

import au.edu.unimelb.swen.oop.mokepon.app.ScreenManager;
import au.edu.unimelb.swen.oop.mokepon.app.ScreenState;
import au.edu.unimelb.swen.oop.mokepon.data.GameData;
import au.edu.unimelb.swen.oop.mokepon.data.TileLoader;
import au.edu.unimelb.swen.oop.mokepon.driven.overworld.Coordinate;
import au.edu.unimelb.swen.oop.mokepon.driven.overworld.OverworldStatus;
import au.edu.unimelb.swen.oop.mokepon.driven.overworld.OverworldSystem;
import au.edu.unimelb.swen.oop.mokepon.event.EventBus;
import au.edu.unimelb.swen.oop.mokepon.event.ExitOverworldEvent;
import au.edu.unimelb.swen.oop.mokepon.state.GameState;
import au.edu.unimelb.swen.oop.mokepon.state.ItemType;
import au.edu.unimelb.swen.oop.mokepon.util.overworld.EnemyInfoLoader;
import au.edu.unimelb.swen.oop.mokepon.util.overworld.ExitInfoLoader;
import au.edu.unimelb.swen.oop.mokepon.util.overworld.ItemInfoLoader;
import au.edu.unimelb.swen.oop.mokepon.util.overworld.PlayerInfoLoader;
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
    }


    @Override
    public void init() {
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


        view.getStatusBarView().clear();


        view.getExitMarkerView().setReturnButton(this::onReturnClicked);
        view.getExitMarkerView().setQuitButton(this::onQuitClicked);
        view.getExitMarkerView().setVisible(false);
    }

    private void initOverworldSys() {
        Coordinate playerCoordinate = PlayerInfoLoader.getStartingCoordinate();
        Coordinate exitCoordinate = ExitInfoLoader.getExitCoordinate();

        this.overworldSystem = new OverworldSystem(tileMatrix,
                playerCoordinate, exitCoordinate,
                EnemyInfoLoader.getCoordinateEnemyMap(),
                ItemInfoLoader.getCoordinateItemMap());
    }

    private void handleMovement(int dx, int dy) {
        // 1. 后端引擎运转
        OverworldStatus overworldStatus = overworldSystem.movePlayer(dx, dy);

        // 2. 只有合法移动，UI 才会更新
        if (playerMoveControl(overworldStatus)) {
            // 直接向系统要绝对准确的最新坐标！
            Coordinate realPos = overworldSystem.getPlayerCoordinate();
            // 由于 FxTileView 里 putPlayer 使用的是 GridPane 修改 Index，不用担心残影问题
            view.getTileView().putPlayer(realPos);
        }
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

    private boolean playerMoveControl(OverworldStatus overworldStatus) {
        view.getStatusBarView().clear();
        switch (overworldStatus) {
            case STOP -> {
                view.getStatusBarView().setStatusText("Illegal path to go!");
                return false; // 撞墙，中断操作
            }
            case MOVE -> {
                return true; // 空地，允许移动
            }
            case ITEM -> {
                ItemType itemType = overworldSystem.checkOutItemType();
                if (itemType != ItemType.UNKNOWN) {
                    gameState.getInventory().add(itemType); // 存入背包
                    view.getPlayerStatisticView().setInventoryStatistic(gameState.getInventory()); // 刷新侧边栏
                    view.getStatusBarView().setStatusText("You picked up a " + itemType.name() + "!");

                    // 通知 UI 从地图上擦除那个宝箱
                    view.getTileView().popEntity(overworldSystem.getPlayerCoordinate());
                }
                return true; // 允许移动
            }
            case ENEMY -> {
                // ⚠️ 极其关键！这里必须 return true 允许玩家走到这一格，
                // 然后才能触发遇敌机制。由于你还没写战斗模块，先弹个提示。
                System.out.println("Encountered enemy at: " + overworldSystem.getPlayerX() + ", " + overworldSystem.getPlayerY());
                view.getStatusBarView().setStatusText("Encountered a wild Mokepon!");

                // TODO: 结合难度和随机数生成敌方精灵，并弹出 view.getMokeponMarkerView()

                return true;
            }
            case EXIT -> {
                view.getExitMarkerView().setVisible(true);
                return true; // 允许踩到门上
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
        eventBus.publish(new ExitOverworldEvent(gameState));
        screenManager.switchTo(ScreenState.SELECT);
    }

}



