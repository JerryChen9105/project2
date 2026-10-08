package au.edu.unimelb.swen.oop.mokepon.view.view_interface.bg;

import au.edu.unimelb.swen.oop.mokepon.view.view_interface.View;

public interface GameMenuView extends View {

    public void enable(int index);

    public void disable(int index);

    public void setExitAction(Runnable exitClick);

    public void setRunAwayAction(Runnable runAwayAction);

    public void setMainMenuAction(Runnable mainMenuAction);

}

