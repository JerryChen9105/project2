package project1.UIElement.StaticUI;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;

import static project1.UIElement.Utils.ScreenPosition.*;
import static project1.UIElement.Utils.ImageMethod.*;

public class BGBlock {

    public static final String bgURL = "/assets/bg.png";

    public static ImageView createBGImageView() {
        ImageView bgImgView = createImageView(bgURL, screenWidth, screenHeight, false);
        setLocation(bgImgView, screenLeft, screenTop + menuBarHeight);
        return bgImgView;
    }

    public static Button createStartButton(Font chooseFont) {
        Button startButton = new Button("Choose Mokepon");
        startButton.setStyle("-fx-background-color: #d3d3d3;" +
                            "-fx-text-fill: #000000;" +
                            "-fx-wrap-text: true;" +
                            "-fx-text-alignment: center;" +
                            "-fx-max-width: 160;" +
                            "-fx-min-height: 170;" +
                            "-fx-background-radius: 15;");
        startButton.setOpacity(0.2);
        startButton.setFont(chooseFont);
        startButton.setLayoutX(1045);
        startButton.setLayoutY(515);

        return startButton;
    }



}
