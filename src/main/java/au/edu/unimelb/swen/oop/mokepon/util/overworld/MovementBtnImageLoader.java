package au.edu.unimelb.swen.oop.mokepon.util.overworld;

import au.edu.unimelb.swen.oop.mokepon.data.GameData;
import au.edu.unimelb.swen.oop.mokepon.util.ImageLoader;
import javafx.scene.image.ImageView;

public class MovementBtnImageLoader {

    private static final String PREFIX = "map.buttons.";

    public static ImageView getBtnImgView(String btnName) {
        String url = GameData.getGameData().getProperty(PREFIX + btnName, null);
        return new ImageView(
                ImageLoader.load(url, 32, 32, true)
        );
    }

}
