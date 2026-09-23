package au.edu.unimelb.swen.oop.mokepon.UIElement.Utils;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

public class ScreenPosition {

    public static final Rectangle2D screenBounds = Screen.getPrimary().getBounds();
    public static final double bgWidth = 1280;
    public static final double bgHeight = 768;
    public static final double menuBarHeight = 15;
    public static final double screenWidth = bgWidth;
    public static final double screenHeight = bgHeight + menuBarHeight;
    public static final double screenLeft = screenBounds.getMinX();
    public static final double screenTop = screenBounds.getMinY();


    public static double relativeCenterX(double idealX, double lengthX)
    {
        return idealX - (lengthX / 2);
    }

    public static double relativeCenterY(double idealY, double lengthY)
    {
        return idealY - (lengthY / 2);
    }

    private ScreenPosition()
    {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

}
