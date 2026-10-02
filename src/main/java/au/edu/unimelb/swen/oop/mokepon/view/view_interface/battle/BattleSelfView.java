package au.edu.unimelb.swen.oop.mokepon.view.view_interface.battle;

import au.edu.unimelb.swen.oop.mokepon.battle.skill.Skill;
import au.edu.unimelb.swen.oop.mokepon.model.Mokepon;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.View;

import java.util.ArrayList;
import java.util.function.Consumer;

public interface BattleSelfView extends View {

    public void setSkills(ArrayList<Skill> skillList, Consumer<Skill> onReleaseSkill);

    public void updateState(Mokepon self);

}
