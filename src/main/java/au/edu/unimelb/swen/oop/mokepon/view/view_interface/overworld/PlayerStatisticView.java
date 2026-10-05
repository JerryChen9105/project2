package au.edu.unimelb.swen.oop.mokepon.view.view_interface.overworld;

import au.edu.unimelb.swen.oop.mokepon.driven.model.Mokepon;
import au.edu.unimelb.swen.oop.mokepon.state.Inventory;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.View;

public interface PlayerStatisticView extends View {

    public void setMokeponStatistic(Mokepon mokepon);

    public void setInventoryStatistic(Inventory inventory);

}
