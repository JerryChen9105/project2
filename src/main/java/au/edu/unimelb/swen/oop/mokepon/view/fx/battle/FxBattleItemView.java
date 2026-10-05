package au.edu.unimelb.swen.oop.mokepon.view.fx.battle;

import au.edu.unimelb.swen.oop.mokepon.state.ItemType;
import au.edu.unimelb.swen.oop.mokepon.util.FontLoader;
import au.edu.unimelb.swen.oop.mokepon.util.ItemImageLoader;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.battle.BattleItemView;
import javafx.beans.property.IntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.function.Consumer;

public class FxBattleItemView implements BattleItemView {


    private HBox battleItemRoot = new HBox(10);
    private LinkedHashMap<String, Button> itemBtnMap = new LinkedHashMap<>();

    private Font itemFont = FontLoader.getGostischFont(18);


    public FxBattleItemView() {
        for (ItemType itemType : ItemType.values()) {
            Button itemBtn = new Button("0", new ImageView(ItemImageLoader.getItemImg(itemType)));
            itemBtn.setOpacity(0.2);
            itemBtn.setStyle("-fx-pref-width: 50;" +
                    "-fx-pref-height: 75;" +
                    "-fx-background-color: #d3d3d3;" +
                    "-fx-background-radius: 25px;" +
                    "-fx-text-fill: #000000");
            itemBtn.setFont(itemFont);
            itemBtnMap.put(itemType.name(), itemBtn);
        }
        battleItemRoot.getChildren().addAll(itemBtnMap.values());
        battleItemRoot.setAlignment(Pos.CENTER);
    }




    @Override
    public Node node() {
        return battleItemRoot;
    }

    @Override
    public void setItems(HashMap<ItemType, IntegerProperty> itemDeposit, Consumer<IntegerProperty> onUseItem) {
        itemDeposit.forEach((itemType, integerProperty) -> {
            Button itemBtn = itemBtnMap.get(itemType.name());
            itemBtn.setOnMouseEntered(enter -> {
                itemBtn.setOpacity(0.5);
            });
            itemBtn.setOnMouseExited(exit -> {
                itemBtn.setOpacity(0.2);
            });
            itemBtn.setOnMouseClicked(event -> {
                itemBtn.setOpacity(0.75);
                onUseItem.accept(integerProperty);
            });
            integerProperty.addListener((observable, oldValue, newValue) -> {
                if ((Integer)newValue <= 0) {
                    itemBtn.setDisable(true);
                } else {
                    itemBtn.setDisable(false);
                }
            });
        });
    }

    @Override
    public void setDisable(boolean disable) {
        itemBtnMap.values().forEach(itemBtn -> {
            itemBtn.setDisable(disable);
        });
    }
}
