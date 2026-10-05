package au.edu.unimelb.swen.oop.mokepon;

import au.edu.unimelb.swen.oop.mokepon.app.GameWindow;
import au.edu.unimelb.swen.oop.mokepon.app.ScreenManager;
import au.edu.unimelb.swen.oop.mokepon.data.MokeponLoader;
import au.edu.unimelb.swen.oop.mokepon.event.EventBus;
import au.edu.unimelb.swen.oop.mokepon.driven.model.Mokepon;
import au.edu.unimelb.swen.oop.mokepon.state.GameState;
import au.edu.unimelb.swen.oop.mokepon.util.random.JavaRandomSource;
import au.edu.unimelb.swen.oop.mokepon.util.random.RandomSource;
import au.edu.unimelb.swen.oop.mokepon.view.GameBgView;
import au.edu.unimelb.swen.oop.mokepon.view.fx.bg.FxGameMenuView;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Properties;

public class TestMain extends Application {


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

        MokeponLoader mokeponLoader = new MokeponLoader(gameProperty);
        ArrayList<Mokepon> mokeponList = new ArrayList<>(mokeponLoader.loadAll().values());








        Group mainRoot = (Group) gameBgView.getBgViewRoot();
        mainRoot.getChildren().addAll(

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
