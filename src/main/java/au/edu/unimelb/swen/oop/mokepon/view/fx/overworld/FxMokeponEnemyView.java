package au.edu.unimelb.swen.oop.mokepon.view.fx.overworld;

import au.edu.unimelb.swen.oop.mokepon.util.ImageLoader;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.overworld.MokeponEnemyView;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

public class FxMokeponEnemyView implements MokeponEnemyView {

    private ImageView enemyImgView = new ImageView();


    public FxMokeponEnemyView() {
        enemyImgView.setImage(ImageLoader.load("/assets/enemy.png", 48, 48));
    }


    @Override
    public Node node() {
        return enemyImgView;
    }

    @Override
    public void setVisible(boolean visible) {
        enemyImgView.setVisible(visible);
    }
}
