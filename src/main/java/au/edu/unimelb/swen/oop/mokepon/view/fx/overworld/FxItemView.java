package au.edu.unimelb.swen.oop.mokepon.view.fx.overworld;

import au.edu.unimelb.swen.oop.mokepon.util.ImageLoader;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.overworld.ItemView;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

public class FxItemView implements ItemView {

    private ImageView itemImgView = new ImageView();


    public FxItemView() {
        itemImgView.setImage(ImageLoader.load("/assets/item.png", 48, 48));
    }


    @Override
    public Node node() {
        return itemImgView;
    }

    @Override
    public void setVisible(boolean visible) {
        itemImgView.setVisible(visible);
    }
}
