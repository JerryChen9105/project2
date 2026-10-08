package au.edu.unimelb.swen.oop.mokepon.view.view_interface.battle;

import au.edu.unimelb.swen.oop.mokepon.view.view_interface.View;

public interface BattleLogView extends View {

    public void append(String message);
    public void clear();

}

