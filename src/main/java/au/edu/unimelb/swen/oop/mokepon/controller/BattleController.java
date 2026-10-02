package au.edu.unimelb.swen.oop.mokepon.controller;

import au.edu.unimelb.swen.oop.mokepon.battle.BattleContext;
import au.edu.unimelb.swen.oop.mokepon.battle.BattleOutcome;
import au.edu.unimelb.swen.oop.mokepon.battle.BattleService;
import au.edu.unimelb.swen.oop.mokepon.battle.skill.Skill;
import au.edu.unimelb.swen.oop.mokepon.event.EventBus;
import au.edu.unimelb.swen.oop.mokepon.state.GameState;
import au.edu.unimelb.swen.oop.mokepon.view.MokeponBattleView;
import javafx.scene.Node;

public class BattleController implements ScreenController {

    private MokeponBattleView view;
    private GameState state;
    private BattleService battleService;
    private EventBus eventBus;
    private BattleContext context;

    @Override
    public void init() {

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public Node getRoot() {
        return null;
    }

    private void onSkillClicked(Skill skill) {

    }

    private void runEnrmyTurn() {

    }

    private void handleBattleEnd(BattleOutcome battleOutcome) {

    }

}

