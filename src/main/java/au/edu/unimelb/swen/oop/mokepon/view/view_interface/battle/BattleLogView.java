package au.edu.unimelb.swen.oop.mokepon.view.view_interface.battle;

import au.edu.unimelb.swen.oop.mokepon.view.view_interface.View;

import java.util.ArrayList;
import java.util.function.Consumer;

public interface BattleLogView extends View {

    public void append(String message);
    public void clear();

}
