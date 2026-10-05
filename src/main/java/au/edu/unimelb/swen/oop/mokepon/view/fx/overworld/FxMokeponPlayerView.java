package au.edu.unimelb.swen.oop.mokepon.view.fx.overworld;

import au.edu.unimelb.swen.oop.mokepon.util.ImageLoader;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.overworld.MokeponPlayerView;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

public class FxMokeponPlayerView implements MokeponPlayerView {

    private ImageView playerImgView = new ImageView();


    public FxMokeponPlayerView() {
        playerImgView.setImage(ImageLoader.load("/assets/player.png", 48, 48));
    }


    @Override
    public Node node() {
        return playerImgView;
    }
}
