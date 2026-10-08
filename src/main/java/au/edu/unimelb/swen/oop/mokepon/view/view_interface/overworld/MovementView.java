package au.edu.unimelb.swen.oop.mokepon.view.view_interface.overworld;

import au.edu.unimelb.swen.oop.mokepon.view.view_interface.View;

public interface MovementView extends View {

    public void setUpAction(Runnable upAction);

    public void setDownAction(Runnable downAction);

    public void setLeftAction(Runnable leftAction);

    public void setRightAction(Runnable rightAction);

}

