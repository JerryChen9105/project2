package au.edu.unimelb.swen.oop.mokepon.view.view_interface.battle;

import au.edu.unimelb.swen.oop.mokepon.model.Mokepon;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.View;

public interface BattleCardView extends View {

    public void show(Mokepon mokepon);
    public void updateHP(int current, int max);
    public void updateEnergy(int current, int max);

}
