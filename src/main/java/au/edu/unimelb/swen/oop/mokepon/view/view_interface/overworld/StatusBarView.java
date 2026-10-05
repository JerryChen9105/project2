package au.edu.unimelb.swen.oop.mokepon.view.view_interface.overworld;

import au.edu.unimelb.swen.oop.mokepon.view.view_interface.View;

public interface StatusBarView extends View {

    public void setStatusText(String text);

    public void clear();

}
