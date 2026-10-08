package au.edu.unimelb.swen.oop.mokepon.controller;

import au.edu.unimelb.swen.oop.mokepon.app.ScreenManager;
import au.edu.unimelb.swen.oop.mokepon.driven.battle.BattleContext;
import au.edu.unimelb.swen.oop.mokepon.driven.battle.BattleOutcome;
import au.edu.unimelb.swen.oop.mokepon.driven.battle.BattleResult;
import au.edu.unimelb.swen.oop.mokepon.driven.battle.BattleService;
import au.edu.unimelb.swen.oop.mokepon.driven.model.Mokepon;
import au.edu.unimelb.swen.oop.mokepon.driven.model.skill.Skill;
import au.edu.unimelb.swen.oop.mokepon.driven.model.skill.SkillRegistry;
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
        view.getSelfView().initSelfStatus(self, this::onSkillClicked,
                state.getInventory().getItemMap(), this::onItemUsed);
        view.getEnemyView().initEnemyStatus(enemy);
        view.getLogView().clear();
        view.getLogView().append("Player chose " + self.getName() +
                ". Began a fight against enemy " + enemy.getName() + "!\n\n");
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

        BattleResult battleResult = skill.execute(state.getPlayerMokepon(),
                state.getEnemyMokepon(),
                context);
        battleResult.getMessageList().forEach(message -> {
            view.getLogView().append(message);
        });
    }

    private void onItemUsed(ItemType itemType) {
        System.out.println("used item type " + itemType);
        state.getInventory().use(itemType);
    }

    private void runEnemyTurn() {

    }

    private void handleBattleEnd(BattleOutcome battleOutcome) {

    }

}


