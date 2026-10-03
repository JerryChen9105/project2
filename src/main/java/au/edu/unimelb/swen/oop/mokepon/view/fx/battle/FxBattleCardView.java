package au.edu.unimelb.swen.oop.mokepon.view.fx.battle;

import au.edu.unimelb.swen.oop.mokepon.model.Mokepon;
import au.edu.unimelb.swen.oop.mokepon.util.ImageLoader;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.battle.BattleCardView;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class FxBattleCardView implements BattleCardView {


    private VBox battleCardRoot = new VBox(5);
    private ImageView selfImgView = new ImageView();
    private StackPane[] selfBarArr = new StackPane[2];
    private Text[] selfBarTxtArr = new Text[2];
    private ProgressBar[] selfProgressBarArr = new ProgressBar[2];


    public FxBattleCardView() {
        selfProgressBarArr[0] = new ProgressBar(1.0);
        selfProgressBarArr[0].setStyle("-fx-pref-width: 250;" +
                "-fx-pref-height: 27;" +
                "-fx-accent: #31c831;");
        selfBarTxtArr[0].setStyle("-fx-font-size: 16");

        selfProgressBarArr[1] = new ProgressBar(1.0);
        selfProgressBarArr[1].setStyle("-fx-pref-width: 250;" +
                "-fx-pref-height: 27;" +
                "-fx-accent: #3e64d8;");
        selfProgressBarArr[1].setProgress(1.0);
        selfBarTxtArr[1].setStyle("-fx-font-size: 16");

        battleCardRoot.getChildren().add(selfImgView);
        battleCardRoot.getChildren().addAll(selfBarArr);
        battleCardRoot.setAlignment(Pos.CENTER);
    }



    @Override
    public Node node() {
        return battleCardRoot;
    }

    @Override
    public void show(Mokepon mokepon) {
        selfImgView.setImage(ImageLoader.load(mokepon.getImgURL(), 256, 256));
    }

    @Override
    public void updateHP(int current, int max) {
        selfProgressBarArr[0].setProgress((double)(current / max));
    }

    @Override
    public void updateEnergy(int current, int max) {
        selfProgressBarArr[1].setProgress((double)(current / max));
    }
}
