package au.edu.unimelb.swen.oop.mokepon.app;

import au.edu.unimelb.swen.oop.mokepon.util.ScreenPosition;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameWindow extends Stage {

    public GameWindow() {
        setTitle("Mokepon");
        setResizable(false);
        setWidth(ScreenPosition.SCREENWIDTH);
        setHeight(ScreenPosition.SCREENHEIGHT);
        setScene(new Scene(new Pane(), ScreenPosition.SCREENWIDTH, ScreenPosition.SCREENHEIGHT, Color.LIGHTGREY));
    }

}
