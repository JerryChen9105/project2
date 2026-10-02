package au.edu.unimelb.swen.oop.mokepon.view.view_interface.select;

import au.edu.unimelb.swen.oop.mokepon.model.Mokepon;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.View;

public interface MokeponDetailView extends View {

    public void show(Mokepon mokepon);
    public void clear();

}
