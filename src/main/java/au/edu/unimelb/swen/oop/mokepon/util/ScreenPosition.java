package au.edu.unimelb.swen.oop.mokepon.util;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

public class ScreenPosition {

    public static final Rectangle2D SCREENBOUNDS = Screen.getPrimary().getBounds();
    public static final double BGWIDTH = 1280;
    public static final double BGHEIGHT = 768;
    public static final double MENUBARHEIGHT = 15;
    public static final double WINDOWHEIGHT = 10;
    public static final double WINDOWWIDTH = 5;
    public static final double SCREENWIDTH = BGWIDTH + WINDOWWIDTH * 2;
    public static final double SCREENHEIGHT = BGHEIGHT + MENUBARHEIGHT + WINDOWHEIGHT * 2;
    public static final double SCREENLEFT = SCREENBOUNDS.getMinX();
    public static final double SCREENTOP = SCREENBOUNDS.getMinY();


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
