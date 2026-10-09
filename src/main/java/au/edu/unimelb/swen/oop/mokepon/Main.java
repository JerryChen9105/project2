package au.edu.unimelb.swen.oop.mokepon;

import au.edu.unimelb.swen.oop.mokepon.app.GameWindow;
import au.edu.unimelb.swen.oop.mokepon.app.ScreenManager;
import au.edu.unimelb.swen.oop.mokepon.app.ScreenState;
import au.edu.unimelb.swen.oop.mokepon.controller.MokeponBattleController;
import au.edu.unimelb.swen.oop.mokepon.controller.MokeponOverworldController;
import au.edu.unimelb.swen.oop.mokepon.controller.MokeponSelectController;
import au.edu.unimelb.swen.oop.mokepon.controller.ScreenController;
import au.edu.unimelb.swen.oop.mokepon.data.GameData;
import au.edu.unimelb.swen.oop.mokepon.data.MokeponLoader;
import au.edu.unimelb.swen.oop.mokepon.driven.battle.BattleContext;
import au.edu.unimelb.swen.oop.mokepon.driven.battle.BattleService;
import au.edu.unimelb.swen.oop.mokepon.driven.model.Mokepon;
import au.edu.unimelb.swen.oop.mokepon.event.*;
import au.edu.unimelb.swen.oop.mokepon.state.GameState;
import au.edu.unimelb.swen.oop.mokepon.state.ItemType;
import au.edu.unimelb.swen.oop.mokepon.util.random.JavaRandomSource;
import au.edu.unimelb.swen.oop.mokepon.util.random.RandomSource;
import au.edu.unimelb.swen.oop.mokepon.view.GameBgView;
import au.edu.unimelb.swen.oop.mokepon.view.MokeponBattleView;
import au.edu.unimelb.swen.oop.mokepon.view.MokeponOverworldView;
import au.edu.unimelb.swen.oop.mokepon.view.MokeponSelectView;
import au.edu.unimelb.swen.oop.mokepon.view.fx.battle.*;
import au.edu.unimelb.swen.oop.mokepon.view.fx.battle.enemy.FxBattleEnemyView;
import au.edu.unimelb.swen.oop.mokepon.view.fx.battle.self.FxBattleItemView;
import au.edu.unimelb.swen.oop.mokepon.view.fx.battle.self.FxBattleSelfView;
import au.edu.unimelb.swen.oop.mokepon.view.fx.battle.self.FxBattleSkillView;
import au.edu.unimelb.swen.oop.mokepon.view.fx.bg.FxGameMenuView;
import au.edu.unimelb.swen.oop.mokepon.view.fx.overworld.*;
import au.edu.unimelb.swen.oop.mokepon.view.fx.select.FxMokeponChooseView;
import au.edu.unimelb.swen.oop.mokepon.view.fx.select.FxMokeponDetailView;
import au.edu.unimelb.swen.oop.mokepon.view.fx.select.FxMokeponListView;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.battle.*;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.battle.enemy.BattleEnemyView;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.battle.self.BattleItemView;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.battle.self.BattleSelfView;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.battle.self.BattleSkillView;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.overworld.*;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.select.MokeponChooseView;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.select.MokeponDetailView;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.select.MokeponListView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;
import java.util.function.Consumer;

public class Main extends Application {

    private ScreenManager screenManager;
    private GameState gameState;
    private RandomSource randomSource;
    private EventBus eventBus;
    private GameBgView gameBgView;


    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage = new GameWindow();
        screenManager = new ScreenManager(primaryStage);
        gameState = new GameState();
        randomSource = new JavaRandomSource();
        eventBus = new EventBus();
        gameBgView = new GameBgView(new FxGameMenuView());


        Properties gameProperty = loadPropertiesFromArgument();
        GameData.setGameData(gameProperty);


        // select block construction
        MokeponListView fxMokeponListView = new FxMokeponListView();
        MokeponDetailView fxMokeponDetailView = new FxMokeponDetailView();
        MokeponChooseView fxMokeponChooseView = new FxMokeponChooseView();

        MokeponSelectView mokeponSelectView = new MokeponSelectView(
                fxMokeponListView,
                fxMokeponDetailView,
                fxMokeponChooseView
        );

        ScreenController mokeponSelectController = new MokeponSelectController(
                mokeponSelectView, gameState, eventBus, screenManager
        );

        screenManager.register(ScreenState.SELECT, mokeponSelectController);
        mokeponSelectController.init();
        //===============================================================================================

        // overworld block construction
        TileView fxTileView = new FxTileView();
        MovementView fxMovementView = new FxMovementView();
        PlayerStatisticView fxPlayerStatisticView = new FxPlayerStatisticView();
        StatusBarView fxStatusBarView = new FxStatusBarView();
        ExitMarkerView fxExitMarkerView = new FxExitMarkerView();
        EncounterMarkerView fxEncounterMarkerView = new FxEncounterMarkerView();

        MokeponOverworldView mokeponOverworldView = new MokeponOverworldView(
                fxTileView,
                fxMovementView,
                fxPlayerStatisticView,
                fxStatusBarView,
                fxEncounterMarkerView,
                fxExitMarkerView);

        ScreenController mokeponOverworldController = new MokeponOverworldController(
                mokeponOverworldView, gameState, eventBus, randomSource, screenManager
        );

        screenManager.register(ScreenState.OVERWORLD, mokeponOverworldController);
        mokeponOverworldController.init();
        mokeponOverworldController.hide();
        //===============================================================================================

        // battle block construction
        BattleCardView fxBattleCardView = new FxBattleCardView();
        BattleSkillView fxBattleSkillView = new FxBattleSkillView();
        BattleItemView fxBattleItemView = new FxBattleItemView();
        BattleNameView fxBattleNameView = new FxBattleNameView();
        BattleSelfView fxBattleSelfView = new FxBattleSelfView(
                fxBattleCardView, fxBattleItemView, fxBattleSkillView, fxBattleNameView
        );

        BattleCardView fxBattleCardView2 = new FxBattleCardView();
        BattleNameView fxBattleNameView2 = new FxBattleNameView();
        BattleEnemyView fxBattleEnemyView = new FxBattleEnemyView(
                fxBattleCardView2, fxBattleNameView2
        );

        BattleLogView fxBattleLogView = new FxBattleLogView();
        BattleWinMarkerView fxWinMarkerView = new FxWinMarkerView();
        BattleLossMarkerView fxLossMarkerView = new FxLossMarkerView();

        MokeponBattleView mokeponBattleView = new MokeponBattleView(
                fxBattleSelfView,
                fxBattleEnemyView,
                fxBattleLogView,
                fxWinMarkerView,
                fxLossMarkerView
        );

        BattleService battleService = new BattleService(randomSource);
        BattleContext battleContext = new BattleContext();
        MokeponBattleController mokeponBattleController = new MokeponBattleController(
                mokeponBattleView, gameState, battleService, eventBus, battleContext, randomSource, screenManager
        );

        screenManager.register(ScreenState.BATTLE, mokeponBattleController);
        mokeponBattleController.init();
        mokeponBattleController.hide();
        //===============================================================================================



        // test space
        //===============================================================================================




        // select -> overworld event
        Consumer<ChooseMokeponEvent> chooseMokeponEventConsumer = event -> {
            event.state().setPlayerMokepon(event.selectedMokepon());
        };
        eventBus.subscribe(ChooseMokeponEvent.class, chooseMokeponEventConsumer);

        Consumer<ChooseMokeponEvent> chooseMokeponEventConsumer2 = event -> {
            gameBgView.enable(1);
        };
        eventBus.subscribe(ChooseMokeponEvent.class, chooseMokeponEventConsumer2);
        //===============================================================================================
        // overworld -> select event
        Consumer<ExitOverworldEvent> exitOverworldEventConsumer = event -> {
            event.gameState().resetForNewSelection();
            gameState.setOverworldIsEnded(true);
            gameBgView.disable(1);
        };
        eventBus.subscribe(ExitOverworldEvent.class, exitOverworldEventConsumer);
        //===============================================================================================


        // overworld -> battle event
        Consumer<EnterBattleEvent> enterBattleEventConsumer = event -> {
            gameBgView.enable(0);
            event.state().getPlayerMokepon().clearMokeponStatus();
            event.state().getEnemyMokepon().clearMokeponStatus();
            event.state().getPlayerMokepon().setMokeponStatus();
            event.state().getEnemyMokepon().setMokeponStatus();
            event.state().setSelfDamageMult(event.selfDamageMult());
            event.state().setEnemyDamageMult(event.enemyDamageMult());
        };
        eventBus.subscribe(EnterBattleEvent.class, enterBattleEventConsumer);
        //===============================================================================================
        // battle -> Overworld event
        Consumer<BattleToOverworldEvent> battleToOverworldEventConsumer = event -> {
            gameBgView.disable(0);
        };
        eventBus.subscribe(BattleToOverworldEvent.class, battleToOverworldEventConsumer);
        //===============================================================================================


        // battle -> select event
        Consumer<BattleToSelectEvent> battleToSelectEventConsumer = event -> {
            gameBgView.disable(0);
            gameBgView.disable(1);
        };
        eventBus.subscribe(BattleToSelectEvent.class, battleToSelectEventConsumer);
        //===============================================================================================




        // menu bar button action
        gameBgView.setExitBtn(primaryStage::close);
        gameBgView.setRunAwayBtn(() -> {
            eventBus.publish(new BattleToOverworldEvent());
            screenManager.switchTo(ScreenState.OVERWORLD);

        });
        gameBgView.setMainMenuBtn(() -> {
            gameState.resetForNewSelection();
            eventBus.publish(new BattleToOverworldEvent());
            eventBus.publish(new ExitOverworldEvent(gameState));
            screenManager.switchTo(ScreenState.SELECT);
        });
        //===============================================================================================



        Group mainRoot = (Group) gameBgView.getBgViewRoot();
        mainRoot.getChildren().addAll(
                mokeponSelectController.getRoot(),
                mokeponOverworldController.getRoot(),
                mokeponBattleController.getRoot()
        );
        Scene scene = new Scene(mainRoot);
        primaryStage.setScene(scene);
        primaryStage.show();
        screenManager.switchTo(ScreenState.SELECT);

    }

    private Properties loadPropertiesFromArgument() throws IOException {
        String argument = getParameters().getRaw().getFirst();
        if (!argument.startsWith("data=")) {
            throw new IllegalArgumentException("Missing expected argument in the form data=relativeFilePath");
        }

        Properties properties = new Properties();
        try (var input = Files.newInputStream(Path.of(argument.substring("data=".length())))) {
            properties.load(input);
        }
        return properties;
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}

