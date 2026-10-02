package au.edu.unimelb.swen.oop.mokepon.view.view_interface.battle;

import au.edu.unimelb.swen.oop.mokepon.model.Mokepon;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.View;

public interface BattleEnemyView extends View {

    public void updateState(Mokepon self);

}
