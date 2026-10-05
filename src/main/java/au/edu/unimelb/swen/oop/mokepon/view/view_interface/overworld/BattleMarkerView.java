package au.edu.unimelb.swen.oop.mokepon.view.view_interface.overworld;

import au.edu.unimelb.swen.oop.mokepon.driven.model.Mokepon;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.View;

import java.util.function.Consumer;

public interface BattleMarkerView extends View {

    public void setTitle(String text);

    public void setHint(String text);

    public void setFightButton(Mokepon enemy, Consumer<Mokepon> switchToBattle);

    public void setVisible(boolean visible);

}
