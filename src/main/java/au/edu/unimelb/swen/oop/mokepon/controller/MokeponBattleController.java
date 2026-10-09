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
    }


    @Override
    public void init() {
        Mokepon self = state.getPlayerMokepon();
        Mokepon enemy = state.getEnemyMokepon();
        if (self == null || enemy == null) return;

        self.setMokeponStatus();
        enemy.setMokeponStatus();
        view.getSelfView().setDisable(false);
        view.getSelfView().initSelfStatus(self, this::onSkillClicked,
                state.getInventory().getItemMap(), this::onItemUsed);
        view.getEnemyView().initEnemyStatus(enemy);
        view.getLogView().clear();
        view.getLogView().append("Player chose " + self.getName() +
                ". Began a fight against enemy " + enemy.getName() + "!\n\n");
        view.getWinMarkerView().setReturnBtn(this::onReturnOverworldClicked);
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
                skill ,state.getPlayerMokepon(), state.getEnemyMokepon(), context
        );
        battleResult.getMessageList().forEach(message -> {
            view.getLogView().append(message);
        });
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

    private void runEnemyTurn() {
        Mokepon enemy = state.getEnemyMokepon();
        Mokepon player = state.getPlayerMokepon();

        BattleResult battleResult = battleService.useEnemyRandomSkill(
                enemy, player, context
        );
        battleResult.getMessageList().forEach(message -> {
            view.getLogView().append(message);
        });
        view.getSelfView().updateSelfStatus(player);
        view.getEnemyView().updateEnemyStatus(enemy);

        runTurnEnd();
    }

    private void runTurnEnd() {
        Mokepon enemy = state.getEnemyMokepon();
        Mokepon player = state.getPlayerMokepon();
        handleBattleEnd(battleService.resolveTurnEnd(player, enemy, context));
    }

    private void handleBattleEnd(BattleOutcome battleOutcome) {
        switch (battleOutcome) {
            case PLAYER_WIN -> {
                view.getLogView().append("You won the battle!\n");
                state.fightCountProperty().set(state.getFightCount() + 1);
                view.getSelfView().setDisable(true);
                view.getWinMarkerView().setVisible(true);
            }
            case PLAYER_LOSE -> {
                view.getLogView().append("You lost the battle...\n");
                view.getSelfView().setDisable(true);
            }
            default -> {
                view.getLogView().append("\n");
            }
        }
    }

    private void onReturnOverworldClicked() {
        state.getEnemyMokepon().clearMokeponStatus();
        state.getPlayerMokepon().clearMokeponStatus();
        screenManager.switchTo(ScreenState.OVERWORLD);
        eventBus.publish(new BattleToOverworldEvent());
    }

}


