package au.edu.unimelb.swen.oop.mokepon.view.view_interface;

import au.edu.unimelb.swen.oop.mokepon.battle.skill.Skill;
import au.edu.unimelb.swen.oop.mokepon.model.Mokepon;
import javafx.beans.property.IntegerProperty;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.function.Consumer;

public interface BattleSelfView extends View {

    public void setSkills(ArrayList<Skill> skillList, Consumer<Skill> onReleaseSkill);

    public void setItems(ArrayList<IntegerProperty> itemDeposit, Consumer<IntegerProperty> onUseItem);

    public void setMokeponImg(Image img);

    public void updateState(Mokepon self);

}
