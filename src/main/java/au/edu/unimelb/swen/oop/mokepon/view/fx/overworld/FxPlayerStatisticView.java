package au.edu.unimelb.swen.oop.mokepon.view.fx.overworld;

import au.edu.unimelb.swen.oop.mokepon.driven.model.Mokepon;
import au.edu.unimelb.swen.oop.mokepon.state.Inventory;
import au.edu.unimelb.swen.oop.mokepon.state.ItemType;
import au.edu.unimelb.swen.oop.mokepon.util.ImageLoader;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.overworld.PlayerStatisticView;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class FxPlayerStatisticView implements PlayerStatisticView {

    private StackPane playerStatisticRoot = new StackPane();

    private ImageView playerStaticBg = new ImageView(
            ImageLoader.load(ImageLoader.scrollURL, 270, 375, false)
    );

    private VBox playerStatisticTxtBlock = new VBox(3);

    private Line splitLine = new Line(0, 0, 230, 0);

    private HBox mokeponStatisticTxtBlock = new HBox(40);
    private VBox mokeponStatisticTxtHead = new VBox(7);
    private VBox mokeponStatisticTxtReal = new VBox(7);

    private HBox inventoryStatisticTxtBlock = new HBox(40);
    private VBox inventoryStatisticTxtHead = new VBox(7);
    private VBox inventoryStatisticTxtReal = new VBox(7);

    private ArrayList<Text> mokeponStatisticTxtList = new ArrayList<>();
    private ArrayList<Text> inventoryStatisticTxtList = new ArrayList<>();
    private Text fightCountTxt = new Text("0");


    private static final String[] mokeponStatisticTxtHeadArr = {
            "Mokepon", "HP", "Energy", "Strength", "Accuracy", "Defence", "Dodge"
    };

    private static final String[] inventoryStatisticTxtHeadArr = {
            "Health Tonic", "Coffee", "Poison"
    };


    public FxPlayerStatisticView() {
        splitLine.setFill(Paint.valueOf("blue"));

        for (int i = 0; i < mokeponStatisticTxtHeadArr.length; i++) {
            Text txtHead = new Text(mokeponStatisticTxtHeadArr[i] + ":");
            txtHead.setFont(Font.font(15));
            Text txtReal = new Text("-");
            txtReal.setFont(Font.font(15));
            mokeponStatisticTxtList.add(txtReal);

            mokeponStatisticTxtHead.getChildren().add(txtHead);
            mokeponStatisticTxtReal.getChildren().add(txtReal);
        }
        mokeponStatisticTxtBlock.getChildren().addAll(mokeponStatisticTxtHead, mokeponStatisticTxtReal);
        mokeponStatisticTxtBlock.setAlignment(Pos.CENTER_LEFT);

        Text fightCountTitle = new Text("Fights:");
        fightCountTitle.setFont(Font.font(15));
        fightCountTxt.setFont(Font.font(15));
        mokeponStatisticTxtHead.getChildren().add(fightCountTitle);
        mokeponStatisticTxtReal.getChildren().add(fightCountTxt);


        Text inventoryTitle = new Text("Inventory");
        inventoryTitle.setFont(Font.font(15));
        Text inventoryReal = new Text("");
        inventoryReal.setFont(Font.font(15));
        inventoryStatisticTxtHead.getChildren().add(inventoryTitle);
        inventoryStatisticTxtReal.getChildren().add(inventoryReal);

        for (int i = 0; i < inventoryStatisticTxtHeadArr.length; i++) {
            Text txtHead = new Text(inventoryStatisticTxtHeadArr[i] + ":");
            txtHead.setFont(Font.font(15));
            Text txtReal = new Text("0");
            txtReal.setFont(Font.font(15));
            inventoryStatisticTxtList.add(txtReal);

            inventoryStatisticTxtHead.getChildren().add(txtHead);
            inventoryStatisticTxtReal.getChildren().add(txtReal);
        }
        inventoryStatisticTxtBlock.getChildren().addAll(inventoryStatisticTxtHead, inventoryStatisticTxtReal);
        inventoryStatisticTxtBlock.setAlignment(Pos.CENTER_LEFT);


        playerStatisticTxtBlock.getChildren().addAll(mokeponStatisticTxtBlock, splitLine, inventoryStatisticTxtBlock);
        playerStatisticTxtBlock.setAlignment(Pos.CENTER_LEFT);

        playerStatisticRoot.getChildren().addAll(playerStaticBg, playerStatisticTxtBlock);
        playerStatisticRoot.setAlignment(Pos.CENTER);
    }


    @Override
    public Node node() {
        return playerStatisticRoot;
    }

    @Override
    public void setMokeponStatistic(ObjectProperty<Mokepon> mokeponObjectProperty) {
        mokeponObjectProperty.addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                mokeponStatisticTxtList.get(0).setText(newValue.getName());
                mokeponStatisticTxtList.get(1).setText(newValue.getMaxHP() + "");
                mokeponStatisticTxtList.get(2).setText(newValue.getMaxEnergy() + "");
                mokeponStatisticTxtList.get(3).setText(newValue.getBaseStrength() + "");
                mokeponStatisticTxtList.get(4).setText(newValue.getBaseAccuracy() + "");
                mokeponStatisticTxtList.get(5).setText(newValue.getBaseDefence() + "");
                mokeponStatisticTxtList.get(6).setText(newValue.getBaseDodge() + "");
            }
        });
    }

    @Override
    public void setInventoryStatistic(Inventory inventory) {
        LinkedHashMap<ItemType, IntegerProperty> map = inventory.getItemMap();
        for (int i = 0; i < ItemType.values().length - 1; i++) {
            final int index = i;
            IntegerProperty property = map.get(ItemType.getItemType(i));
            if (property == null) continue;
            property.addListener((observable, oldValue, newValue) -> {
                inventoryStatisticTxtList.get(index).setText(newValue + "");
            });
        }
    }

    @Override
    public void setFightCount(IntegerProperty fightCount) {
        fightCount.addListener((observable, oldValue, newValue) -> {
            fightCountTxt.setText(newValue + "");
        });
    }
}

