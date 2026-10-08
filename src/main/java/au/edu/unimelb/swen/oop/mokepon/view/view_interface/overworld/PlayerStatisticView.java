package au.edu.unimelb.swen.oop.mokepon.view.view_interface.overworld;

import au.edu.unimelb.swen.oop.mokepon.driven.model.Mokepon;
import au.edu.unimelb.swen.oop.mokepon.state.Inventory;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.View;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;

public interface PlayerStatisticView extends View {

    public void setMokeponStatistic(ObjectProperty<Mokepon> mokeponObjectProperty);

    public void setInventoryStatistic(Inventory inventory);

    public void setFightCount(IntegerProperty fightCount);

}

