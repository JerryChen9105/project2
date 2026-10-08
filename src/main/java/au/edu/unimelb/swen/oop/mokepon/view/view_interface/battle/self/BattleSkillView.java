package au.edu.unimelb.swen.oop.mokepon.view.view_interface.battle.self;

import au.edu.unimelb.swen.oop.mokepon.driven.model.skill.Skill;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.View;

import java.util.ArrayList;
import java.util.function.Consumer;

public interface BattleSkillView extends View {

    public void setSkills(ArrayList<Skill> skillList, Consumer<Skill> onSkillClicked);
    public void setDisabled(boolean disabled);

}

