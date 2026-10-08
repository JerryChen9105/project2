package au.edu.unimelb.swen.oop.mokepon.util.battle;

import au.edu.unimelb.swen.oop.mokepon.data.GameData;
import au.edu.unimelb.swen.oop.mokepon.state.ItemType;
import au.edu.unimelb.swen.oop.mokepon.util.ImageLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Locale;

public class ItemImgViewLoader {

    private static final String prefix = "items.";

    public static ImageView getItemImgView(ItemType itemType) {
        if (!itemType.isReal()) return new ImageView();
        String url = GameData.getGameData().getProperty(prefix + itemType.name().toLowerCase(Locale.ROOT), "");
        Image img = ImageLoader.load(url, 32, 32, true);
        return new ImageView(img);
    }

}
