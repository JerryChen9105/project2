package au.edu.unimelb.swen.oop.mokepon.view.view_interface.battle.self;

import au.edu.unimelb.swen.oop.mokepon.state.ItemType;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.View;
import javafx.beans.property.IntegerProperty;

import java.util.HashMap;
import java.util.function.Consumer;

public interface BattleItemView extends View {

    public void setItems(HashMap<ItemType, IntegerProperty> itemDeposit, Consumer<ItemType> onUseItem);

    public void setDisable(boolean disable);

}


