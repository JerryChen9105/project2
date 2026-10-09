package au.edu.unimelb.swen.oop.mokepon.controller;

import au.edu.unimelb.swen.oop.mokepon.app.ScreenManager;
import au.edu.unimelb.swen.oop.mokepon.app.ScreenState;
import au.edu.unimelb.swen.oop.mokepon.data.GameData;
import au.edu.unimelb.swen.oop.mokepon.data.MokeponLoader;
import au.edu.unimelb.swen.oop.mokepon.event.ChooseMokeponEvent;
import au.edu.unimelb.swen.oop.mokepon.event.EventBus;
import au.edu.unimelb.swen.oop.mokepon.driven.model.Mokepon;
import au.edu.unimelb.swen.oop.mokepon.state.GameState;
import au.edu.unimelb.swen.oop.mokepon.view.MokeponSelectView;
import javafx.scene.Node;

import java.util.ArrayList;
import java.util.function.Consumer;

public class MokeponSelectController implements ScreenController {

    private MokeponSelectView view;
    private GameState state;
    private EventBus eventBus;
    private ScreenManager screenManager;

    private ArrayList<Mokepon> mokeponList;


    public MokeponSelectController(
            MokeponSelectView view,
            GameState state,
            EventBus eventBus,
            ScreenManager screenManager) {
        this.view = view;
        this.state = state;
        this.eventBus = eventBus;
        this.screenManager = screenManager;

        MokeponLoader mokeponLoader = new MokeponLoader(GameData.getGameData());
        this.mokeponList = new ArrayList<>(mokeponLoader.loadAll().values());
    }


    @Override
    public void init() {
        state.resetForNewSelection();
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

        view.getMokeponChooseView().setChooseAction(this::onChooseClicked);
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
        eventBus.publish(new ChooseMokeponEvent(selectedMokepon, state));
        screenManager.switchTo(ScreenState.OVERWORLD);
    }
}
