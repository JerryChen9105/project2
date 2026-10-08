package au.edu.unimelb.swen.oop.mokepon.view.view_interface.overworld;

import au.edu.unimelb.swen.oop.mokepon.view.view_interface.View;

public interface ExitMarkerView extends View {

    public void setTitle(String text);

    public void setReturnButton(Runnable onReturnAction);

    public void setQuitButton(Runnable onTerminateProgram);

    public void setVisible(boolean visible);

}

