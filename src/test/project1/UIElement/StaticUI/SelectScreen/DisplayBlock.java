package project1.UIElement.StaticUI.SelectScreen;

import project1.Main2;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import static project1.UIElement.Utils.GotischFont.createGotischFont;
import static project1.UIElement.Utils.ImageMethod.createImageView;
import static project1.UIElement.Utils.ImageMethod.setLocation;
import static project1.UIElement.Utils.ScreenPosition.*;

public class DisplayBlock {

    public static double displayX = 0.627778 * screenWidth;
    public static double displayY = 0.347826 * screenHeight - 40;
    public static double displayCircleRadius = 0.127778 * screenWidth;
    public static Font mokeponNameFont = createGotischFont(30, new Main2());

    public static String scrollURL = "/assets/scroll.png";
    public static double scrollWidth = (0.32 * screenWidth) + 20;
    public static double scrollHeight = (0.43 * screenHeight) - 40;


    public static Text createDisplayName(String name) {
        if (name == null || name.isEmpty()) name = "Select your Mokepon";

        Text displayName = new Text(name);
        displayName.setFont(mokeponNameFont);
        displayName.setStyle("-fx-fill: #000000;" +
                    "-fx-stroke: #ffffff;" +
                    "-fx-stroke-width: 2px;");
        displayName.setLayoutX(relativeCenterX(displayX, 267.8294));
        displayName.setLayoutY(relativeCenterY(displayY + displayCircleRadius + 50, 19.575));

        return displayName;
    }

    public static Circle createDisplayCircle() {
        Circle circle = new Circle(displayCircleRadius);
        circle.setStyle("-fx-fill: #9a837c;" +
                    "-fx-stroke: #000000;" +
                    "-fx-stroke-width: 2px;");
        circle.setCenterX(displayX);
        circle.setCenterY(displayY);
        return circle;
    }

    public static ImageView createDisplayImg(String name) {
        if (name == null || name.isEmpty()) return null;

        String imgURL = "/assets/" + name + ".png";
        ImageView imgView = createImageView(imgURL, 256, 256, false);
        setLocation(imgView, relativeCenterX(displayX, 256), relativeCenterY(displayY, 256));
        return imgView;
    }

    public static ImageView createDisplayScroll() {
        ImageView scrollImgView = createImageView(scrollURL, scrollWidth, scrollHeight, false);
        setLocation(scrollImgView, relativeCenterX(displayX, scrollWidth),
                relativeCenterY(displayY + 2 * displayCircleRadius + 50, scrollHeight));

        return scrollImgView;
    }

}
