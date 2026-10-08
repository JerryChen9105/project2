package au.edu.unimelb.swen.oop.mokepon.view.view_interface.battle.self;

import au.edu.unimelb.swen.oop.mokepon.driven.model.Mokepon;
import au.edu.unimelb.swen.oop.mokepon.driven.model.skill.Skill;
import au.edu.unimelb.swen.oop.mokepon.state.ItemType;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.View;
import javafx.beans.property.IntegerProperty;

import java.util.HashMap;
import java.util.function.Consumer;

public interface BattleSelfView extends View {
    public void initSelfStatus(Mokepon self, Consumer<Skill> onSkillBtnClicked,
                        HashMap<ItemType, IntegerProperty> itemDeposit,
                        Consumer<ItemType> onUseItem);

    public void updateSelfStatus(Mokepon self);

    public void setDisable(boolean disable);
}
