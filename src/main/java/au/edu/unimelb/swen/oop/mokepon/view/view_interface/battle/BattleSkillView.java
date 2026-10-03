package au.edu.unimelb.swen.oop.mokepon.view.view_interface.battle;

import au.edu.unimelb.swen.oop.mokepon.view.view_interface.View;

import java.util.ArrayList;
import java.util.function.Consumer;

public interface BattleSkillView extends View {

    public void setSkills(ArrayList<String> skillNames, Consumer<String> onSkillClicked);
    public void setDisabled(boolean disabled);

}
