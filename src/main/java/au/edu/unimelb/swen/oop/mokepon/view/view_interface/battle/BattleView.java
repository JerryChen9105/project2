package au.edu.unimelb.swen.oop.mokepon.view.view_interface.battle;

import au.edu.unimelb.swen.oop.mokepon.view.view_interface.View;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.bg.ButtonControl;

public interface BattleView extends View {

    public BattleCardView getSelfCard();
    public BattleCardView getEnemyCard();
    public BattleSkillView getSkillView();
    public BattleLogView getLogView();
    public ButtonControl getBackButton();

}
