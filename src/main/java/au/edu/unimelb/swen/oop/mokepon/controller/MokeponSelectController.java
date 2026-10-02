package au.edu.unimelb.swen.oop.mokepon.controller;

import au.edu.unimelb.swen.oop.mokepon.app.ScreenManager;
import au.edu.unimelb.swen.oop.mokepon.app.ScreenState;
import au.edu.unimelb.swen.oop.mokepon.event.EventBus;
import au.edu.unimelb.swen.oop.mokepon.model.Mokepon;
import au.edu.unimelb.swen.oop.mokepon.state.GameState;
import au.edu.unimelb.swen.oop.mokepon.util.random.RandomSource;
import au.edu.unimelb.swen.oop.mokepon.view.MokeponSelectView;
import javafx.scene.Node;

import java.util.ArrayList;
import java.util.function.Consumer;

public class MokeponSelectController implements ScreenController {

    private MokeponSelectView view;
    private GameState state;
    private ArrayList<Mokepon> mokeponList;
    private EventBus eventBus;
    private RandomSource randomSource;
    private ScreenManager screenManager;


    public MokeponSelectController(
            MokeponSelectView view,
            GameState state,
            ArrayList<Mokepon> mokeponList,
            EventBus eventBus,
            RandomSource randomSource,
            ScreenManager screenManager) {
        this.view = view;
        this.state = state;
        this.mokeponList = mokeponList;
        this.eventBus = eventBus;
        this.randomSource = randomSource;
        this.screenManager = screenManager;
    }


    @Override
    public void init() {
        Consumer<Mokepon> btnClickEvent = mokepon -> {
            if ((state.getSelectedMokepon() == null) || !state.getSelectedMokepon().equals(mokepon)) {
                onMokeponSelected(mokepon);
            } else {
                onMokeponCancelSelected();
            }
        };
        view.getMokeponListView().setItems(mokeponList, btnClickEvent);
        state.selectedMokeponProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                view.getMokeponListView().clearSelection();
                view.getMokeponDetailView().clear();
            }
        });
    }

    @Override
    public void show() {
        view.getMokeponSelectRoot().setVisible(true);
    }

    @Override
    public void hide() {
        view.getMokeponSelectRoot().setVisible(false);
    }

    @Override
    public Node getRoot() {
        return view.getMokeponSelectRoot();
    }

    private void onMokeponSelected(Mokepon mokepon) {
        state.setSelectedMokepon(mokepon);
        view.getMokeponListView().select(mokepon);
        view.getMokeponDetailView().show(mokepon);
    }

    private void onMokeponCancelSelected() {
        state.setSelectedMokepon(null);
        view.getMokeponDetailView().clear();
    }

    private void onChooseClicked() {
        Mokepon selectedMokepon = state.getSelectedMokepon();
        if (selectedMokepon == null) return;

        state.setPlayerMokepon(selectedMokepon);

        Mokepon enemyMokepon = mokeponList.get(randomSource.nextInt(0, mokeponList.size()));
        state.setEnemyMokepon(enemyMokepon);

        //eventBus.publish(new ChooseMokeponEvent(selectedMokepon, enemyMokepon));
        screenManager.switchTo(ScreenState.BATTLE);
    }
}
