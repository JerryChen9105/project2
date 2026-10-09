package au.edu.unimelb.swen.oop.mokepon.view.view_interface.battle.enemy;

import au.edu.unimelb.swen.oop.mokepon.driven.model.Mokepon;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.View;

public interface BattleEnemyView extends View {

    public void initEnemyStatus(Mokepon enemy);

    public void updateEnemyStatus(Mokepon enemy);

}


