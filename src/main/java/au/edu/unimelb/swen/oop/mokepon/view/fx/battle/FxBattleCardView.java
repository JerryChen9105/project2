package au.edu.unimelb.swen.oop.mokepon.view.fx.battle;

import au.edu.unimelb.swen.oop.mokepon.driven.model.Mokepon;
import au.edu.unimelb.swen.oop.mokepon.util.ImageLoader;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.battle.BattleCardView;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class FxBattleCardView implements BattleCardView {


    private VBox battleCardRoot = new VBox(4);
    private ImageView selfImgView = new ImageView();
    private StackPane[] selfBarArr = new StackPane[2];
    private Text[] selfBarTxtArr = new Text[2];
    private ProgressBar[] selfProgressBarArr = new ProgressBar[2];

    private int maxHp = 0;
    private int maxEnergy = 0;

    private IntegerProperty curHp = new SimpleIntegerProperty(1);
    private IntegerProperty curEnergy = new SimpleIntegerProperty(1);

    private boolean textVisible = true;


    public FxBattleCardView() {

        for (int i = 0; i < selfBarArr.length; i++) {
            selfProgressBarArr[i] = new ProgressBar(1.0);
            selfBarTxtArr[i] = new Text("- / 0");
            selfBarTxtArr[i].setStyle("-fx-font-size: 16");
            selfBarArr[i] = new StackPane(selfProgressBarArr[i], selfBarTxtArr[i]);
        }

        selfProgressBarArr[0].setStyle("-fx-pref-width: 250;" +
                "-fx-pref-height: 27;" +
                "-fx-accent: #31c831;");

        selfProgressBarArr[1].setStyle("-fx-pref-width: 250;" +
                "-fx-pref-height: 27;" +
                "-fx-accent: #3e64d8;");

        curHp.addListener((observable, oldValue, newValue) -> {
            if (textVisible) selfBarTxtArr[0].setText(newValue + " / " + maxHp);
            selfProgressBarArr[0].setProgress(newValue.doubleValue() / maxHp);
        });

        curEnergy.addListener((observable, oldValue, newValue) -> {
            if (textVisible) selfBarTxtArr[1].setText(newValue + " / " + maxEnergy);
            selfProgressBarArr[1].setProgress(newValue.doubleValue() / maxEnergy);
        });


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
        maxHp = mokepon.getMaxHP();
        maxEnergy = mokepon.getMaxEnergy();
    }

    @Override
    public void updateHP(int current) {
        curHp.setValue(current);
    }

    @Override
    public void updateEnergy(int current) {
        curEnergy.setValue(current);
    }

    @Override
    public void setTextVisible(boolean visible) {
        textVisible = visible;
        if (!visible) {
            for (Text txt : selfBarTxtArr) txt.setText("");
        }
    }
}

