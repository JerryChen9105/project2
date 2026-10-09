package au.edu.unimelb.swen.oop.mokepon.controller;

import au.edu.unimelb.swen.oop.mokepon.app.ScreenManager;
import au.edu.unimelb.swen.oop.mokepon.app.ScreenState;
import au.edu.unimelb.swen.oop.mokepon.driven.battle.BattleContext;
import au.edu.unimelb.swen.oop.mokepon.driven.battle.BattleOutcome;
import au.edu.unimelb.swen.oop.mokepon.driven.battle.BattleResult;
import au.edu.unimelb.swen.oop.mokepon.driven.battle.BattleService;
import au.edu.unimelb.swen.oop.mokepon.driven.model.Mokepon;
import au.edu.unimelb.swen.oop.mokepon.driven.model.skill.Skill;
import au.edu.unimelb.swen.oop.mokepon.event.BattleToOverworldEvent;
import au.edu.unimelb.swen.oop.mokepon.event.BattleToSelectEvent;
import au.edu.unimelb.swen.oop.mokepon.event.EventBus;
import au.edu.unimelb.swen.oop.mokepon.state.GameState;
import au.edu.unimelb.swen.oop.mokepon.state.ItemType;
import au.edu.unimelb.swen.oop.mokepon.util.random.RandomSource;
import au.edu.unimelb.swen.oop.mokepon.view.MokeponBattleView;
import javafx.scene.Node;

import java.util.ArrayList;

public class MokeponBattleController implements ScreenController {

    private MokeponBattleView view;
    private GameState state;
    private BattleService battleService;
    private EventBus eventBus;
    private BattleContext context = new BattleContext();
    private RandomSource randomSource;
    private ScreenManager screenManager;


    public MokeponBattleController(MokeponBattleView view,
                                   GameState state,
                                   BattleService battleService,
                                   EventBus eventBus,
                                   BattleContext context,
                                   RandomSource randomSource,
                                   ScreenManager screenManager) {
        this.view = view;
        this.state = state;
        this.battleService = battleService;
        this.eventBus = eventBus;
        this.context = context;
        this.randomSource = randomSource;
        this.screenManager = screenManager;

        view.getWinMarkerView().setReturnBtn(this::onReturnOverworldClicked);
    }


    @Override
    public void init() {
        Mokepon self = state.getPlayerMokepon();
        Mokepon enemy = state.getEnemyMokepon();
        if (self == null || enemy == null) return;

        self.setMokeponStatus();
        self.getMokeponStatus().setPlayerID("Player");
        enemy.setMokeponStatus();
        enemy.getMokeponStatus().setPlayerID("Enemy");
        view.getSelfView().setDisable(false);
        view.getSelfView().initSelfStatus(self, this::onSkillClicked,
                state.getInventory().getItemMap(), this::onItemUsed);
        view.getEnemyView().initEnemyStatus(enemy);

        view.getLogView().clear();
        view.getLogView().append(self.getMokeponStatus().getPlayerID() + " chose " + self.getName() +
                ". Began a fight against " + enemy.getMokeponStatus().getPlayerID() + " " + enemy.getName() + "!\n\n");

        view.getWinMarkerView().setReturnBtn(this::onReturnOverworldClicked);
        view.getWinMarkerView().setVisible(false);

        view.getLossMarkerView().setReturnBtn(this::onReturnMainMenuClicked);
        view.getLossMarkerView().setQuitBtn(this::onQuitClicked);
        view.getLossMarkerView().setVisible(false);

    }

    @Override
    public void show() {
        view.getMokeponBattleRoot().setVisible(true);
    }

    @Override
    public void hide() {
        view.getMokeponBattleRoot().setVisible(false);
    }

    @Override
    public Node getRoot() {
        return view.getMokeponBattleRoot();
    }



    private void onSkillClicked(Skill skill) {
        if (state.getPlayerMokepon().getMokeponStatus().getCurrentEnergy() < skill.getEnergyCost()) return;

        BattleResult battleResult = battleService.useSkill(
                skill ,state.getPlayerMokepon(), state.getEnemyMokepon(), context, state.getSelfDamageMult()
        );

        battleResult.getMessageList().forEach(message -> {
            view.getLogView().append(message);
        });
        flushContextLog();

        view.getSelfView().updateSelfStatus(state.getPlayerMokepon());
        view.getEnemyView().updateEnemyStatus(state.getEnemyMokepon());
        if (battleResult.isEnded()) {
            handleBattleEnd(battleResult.getOutcome());
            return;
        }

        runEnemyTurn();
    }


    private void onItemUsed(ItemType itemType) {
        state.getInventory().use(itemType);

        switch (itemType) {
            case COFFEE -> {
                int maxEnergy = state.getPlayerMokepon().getMaxEnergy();
                state.getPlayerMokepon().getMokeponStatus().setCurrentEnergy(maxEnergy);
                view.getLogView().append("You used your Coffee on " + state.getPlayerMokepon().getName() +
                        ". It restored all your energy!\n");
            }
            case HEALTH -> {
                int maxHP = state.getPlayerMokepon().getMaxHP();
                state.getPlayerMokepon().getMokeponStatus().setCurrentHP(maxHP);
                view.getLogView().append("You used your Health Tonic on " + state.getPlayerMokepon().getName() +
                        ". It healed you to max hp!\n");
            }
            case POISON -> {
                state.getEnemyMokepon().getMokeponStatus().setCurrentHP(1);
                view.getLogView().append("You used your Poison on " + state.getEnemyMokepon().getName() +
                        ". It devastated them!\n");
            }
            default -> {
                return;
            }
        }

        view.getSelfView().updateSelfStatus(state.getPlayerMokepon());
        view.getEnemyView().updateEnemyStatus(state.getEnemyMokepon());
    }

    private void onReturnOverworldClicked() {
        view.getWinMarkerView().setVisible(false);
        state.setOverworldIsEnded(false);
        state.getEnemyMokepon().clearMokeponStatus();
        state.getPlayerMokepon().clearMokeponStatus();
        eventBus.publish(new BattleToOverworldEvent());
        screenManager.switchTo(ScreenState.OVERWORLD);
    }

    private void onReturnMainMenuClicked() {
        view.getLossMarkerView().setVisible(false);
        state.setOverworldIsEnded(true);
        state.resetForNewSelection();
        eventBus.publish(new BattleToSelectEvent());
        screenManager.switchTo(ScreenState.SELECT);
    }

    private void onQuitClicked() {
        screenManager.terminateGame();
    }




    private void flushContextLog() {
        context.getLogList().forEach(message -> {
            view.getLogView().append(message);
        });
        context.getLogList().clear();
    }

    private void runEnemyTurn() {
        Mokepon enemy = state.getEnemyMokepon();
        Mokepon player = state.getPlayerMokepon();

        BattleResult battleResult = battleService.useEnemyRandomSkill(
                enemy, player, context, state.getEnemyDamageMult()
        );

        battleResult.getMessageList().forEach(message -> {
            view.getLogView().append(message);
        });
        flushContextLog();

        runTurnEnd();
    }

    private void runTurnEnd() {
        Mokepon enemy = state.getEnemyMokepon();
        Mokepon player = state.getPlayerMokepon();

        BattleOutcome outcome = battleService.resolveTurnEnd(player, enemy, context);
        flushContextLog();

        view.getSelfView().updateSelfStatus(player);
        view.getEnemyView().updateEnemyStatus(enemy);

        handleBattleEnd(outcome);
    }

    private void handleBattleEnd(BattleOutcome battleOutcome) {
        switch (battleOutcome) {
            case PLAYER_WIN -> {
                view.getLogView().append("\tYou won\n");
                view.getSelfView().setDisable(true);
                state.fightCountProperty().set(state.getFightCount() + 1);
                view.getWinMarkerView().setVisible(true);
            }
            case PLAYER_LOSE -> {
                view.getLogView().append("\tYou loss\n");
                view.getSelfView().setDisable(true);
                view.getLossMarkerView().setVisible(true);
            }
            default -> {
                view.getLogView().append("\n");
            }
        }
    }

}


