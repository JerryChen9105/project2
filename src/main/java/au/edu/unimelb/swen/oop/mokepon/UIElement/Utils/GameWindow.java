package au.edu.unimelb.swen.oop.mokepon.UIElement.Utils;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import static au.edu.unimelb.swen.oop.mokepon.UIElement.Utils.ScreenPosition.*;

public class GameWindow extends Stage {

    public GameWindow() {
        setTitle("Mokepon");
        setResizable(false);
        setScene(new Scene(new Pane(), screenWidth, screenHeight, Color.LIGHTGREY));
    }

}
