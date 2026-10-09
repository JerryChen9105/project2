package au.edu.unimelb.swen.oop.mokepon.view.view_interface.overworld;

import au.edu.unimelb.swen.oop.mokepon.view.view_interface.View;

public interface EncounterMarkerView extends View {

    public void setTitle(String text);

    public void setSelfHint(String text);

    public void setEnemyHint(String text);

    public void setFightButton(Runnable switchToBattle);

    public void setRunButton(Runnable backToOverworld);

    public void setVisible(boolean visible);

}

