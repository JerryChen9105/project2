package au.edu.unimelb.swen.oop.mokepon.view.fx.battle.self;

import au.edu.unimelb.swen.oop.mokepon.state.ItemType;
import au.edu.unimelb.swen.oop.mokepon.util.FontLoader;
import au.edu.unimelb.swen.oop.mokepon.util.battle.ItemImgViewLoader;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.battle.self.BattleItemView;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.function.Consumer;

public class FxBattleItemView implements BattleItemView {


    private HBox battleItemRoot = new HBox(10);
    private LinkedHashMap<String, Button> itemBtnMap = new LinkedHashMap<>();

    private BooleanProperty globalDisableProperty = new SimpleBooleanProperty(false);


    public FxBattleItemView() {
        for (ItemType itemType : ItemType.realValues()) {
            Button itemBtn = new Button("0", ItemImgViewLoader.getItemImgView(itemType));
            itemBtn.setContentDisplay(ContentDisplay.TOP);
            itemBtn.setGraphicTextGap(10);
            itemBtn.setOpacity(0.2);
            itemBtn.setStyle("-fx-pref-width: 50;" +
                    "-fx-pref-height: 85;" +
                    "-fx-background-color: #d3d3d3;" +
                    "-fx-background-radius: 30px;" +
                    "-fx-text-fill: #000000");
            itemBtn.setFont(FontLoader.getGostischFont(24));
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
    public void setItems(HashMap<ItemType, IntegerProperty> itemDeposit, Consumer<ItemType> onUseItem) {
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
                onUseItem.accept(itemType);
            });

            itemBtn.disableProperty().bind(
                    integerProperty.lessThanOrEqualTo(0).or(globalDisableProperty)
            );
            itemBtn.textProperty().bind(integerProperty.asString());
        });
    }

    @Override
    public void setDisable(boolean disable) {
        globalDisableProperty.set(disable);
    }
}


