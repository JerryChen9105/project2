package au.edu.unimelb.swen.oop.mokepon.view.view_interface.select;

import au.edu.unimelb.swen.oop.mokepon.model.Mokepon;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.View;

import java.util.ArrayList;
import java.util.function.Consumer;

public interface MokeponListView extends View {

    public void setItems(ArrayList<Mokepon> mokeponList, Consumer<Mokepon> onSelect);
    public String getSelectedButtonName();
    public void select(Mokepon mokepon);
    public void clearSelection();

}
