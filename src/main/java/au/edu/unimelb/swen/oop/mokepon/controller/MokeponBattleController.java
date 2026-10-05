package au.edu.unimelb.swen.oop.mokepon.controller;

import au.edu.unimelb.swen.oop.mokepon.app.ScreenManager;
import au.edu.unimelb.swen.oop.mokepon.driven.battle.BattleContext;
import au.edu.unimelb.swen.oop.mokepon.driven.battle.BattleOutcome;
import au.edu.unimelb.swen.oop.mokepon.driven.battle.BattleService;
import au.edu.unimelb.swen.oop.mokepon.driven.model.skill.SkillRegistry;
import au.edu.unimelb.swen.oop.mokepon.driven.model.skill.Skill;
import au.edu.unimelb.swen.oop.mokepon.event.EventBus;
import au.edu.unimelb.swen.oop.mokepon.driven.model.Mokepon;
import au.edu.unimelb.swen.oop.mokepon.state.GameState;
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
        view.getSelfCard().show(self);
        view.getEnemyCard().show(enemy);
        ArrayList<Skill> skillList = new ArrayList<>();
        self.getSkills().forEach(skillName -> {
            skillList.add(SkillRegistry.getSkill(skillName));
        });
        view.getSkillView().setSkills(skillList, this::onSkillClicked);
        view.getLogView().clear();
        view.getLogView().append("Player's " + self.getName()
                + " begins a fight against enemy " + enemy.getName() + "!");
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public Node getRoot() {
        return view.getMokeponBattleRoot();
    }

    private void onSkillClicked(Skill skill) {

    }

    private void runEnrmyTurn() {

    }

    private void handleBattleEnd(BattleOutcome battleOutcome) {

    }

}

