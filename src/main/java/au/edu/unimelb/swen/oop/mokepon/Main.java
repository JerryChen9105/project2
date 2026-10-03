package au.edu.unimelb.swen.oop.mokepon;

import au.edu.unimelb.swen.oop.mokepon.app.GameWindow;
import au.edu.unimelb.swen.oop.mokepon.app.ScreenManager;
import au.edu.unimelb.swen.oop.mokepon.app.ScreenState;
import au.edu.unimelb.swen.oop.mokepon.controller.MokeponSelectController;
import au.edu.unimelb.swen.oop.mokepon.controller.ScreenController;
import au.edu.unimelb.swen.oop.mokepon.data.ItemLoader;
import au.edu.unimelb.swen.oop.mokepon.data.MokeponLoader;
import au.edu.unimelb.swen.oop.mokepon.event.ChooseMokeponEvent;
import au.edu.unimelb.swen.oop.mokepon.event.EventBus;
import au.edu.unimelb.swen.oop.mokepon.model.Mokepon;
import au.edu.unimelb.swen.oop.mokepon.state.GameState;
import au.edu.unimelb.swen.oop.mokepon.state.ItemType;
import au.edu.unimelb.swen.oop.mokepon.util.random.JavaRandomSource;
import au.edu.unimelb.swen.oop.mokepon.util.random.RandomSource;
import au.edu.unimelb.swen.oop.mokepon.view.GameBgView;
import au.edu.unimelb.swen.oop.mokepon.view.MokeponSelectView;
import au.edu.unimelb.swen.oop.mokepon.view.fx.bg.FxGameMenuView;
import au.edu.unimelb.swen.oop.mokepon.view.fx.select.FxMokeponChooseView;
import au.edu.unimelb.swen.oop.mokepon.view.fx.select.FxMokeponDetailView;
import au.edu.unimelb.swen.oop.mokepon.view.fx.select.FxMokeponListView;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.select.MokeponChooseView;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.select.MokeponDetailView;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.select.MokeponListView;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
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

        Consumer<ChooseMokeponEvent> chooseMokeponEventConsumer = event -> {};
        eventBus.subscribe(ChooseMokeponEvent.class, chooseMokeponEventConsumer);



        Properties gameProperty = loadPropertiesFromArgument();

        ItemType.setItemLoader(new ItemLoader(gameProperty));

        MokeponLoader mokeponLoader = new MokeponLoader(gameProperty);
        ArrayList<Mokepon> mokeponList = new ArrayList<>(mokeponLoader.loadAll().values());






        MokeponListView fxMokeponListView = new FxMokeponListView();
        MokeponDetailView fxMokeponDetailView = new FxMokeponDetailView();
        MokeponChooseView fxMokeponChooseView = new FxMokeponChooseView();

        MokeponSelectView mokeponSelectView = new MokeponSelectView(
                fxMokeponListView, fxMokeponDetailView, fxMokeponChooseView
        );

        ScreenController mokeponSelectController = new MokeponSelectController(
                mokeponSelectView, gameState, mokeponList, eventBus, randomSource, screenManager
        );






        screenManager.register(ScreenState.SELECT, mokeponSelectController);




        mokeponSelectController.init();


        Group mainRoot = (Group) gameBgView.getBgViewRoot();
        mainRoot.getChildren().addAll(
                mokeponSelectView.getMokeponSelectRoot()
        );
        Scene scene = new Scene(mainRoot);
        primaryStage.setScene(scene);
        primaryStage.show();

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
