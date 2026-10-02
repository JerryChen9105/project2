package au.edu.unimelb.swen.oop.mokepon.view.fx.battle;

import au.edu.unimelb.swen.oop.mokepon.battle.skill.Skill;
import au.edu.unimelb.swen.oop.mokepon.model.Mokepon;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.battle.BattleSelfView;
import javafx.scene.Node;

import java.util.ArrayList;
import java.util.function.Consumer;

public class FxBattleSelfView implements BattleSelfView {





    @Override
    public Node node() {
        return null;
    }

    @Override
    public void setSkills(ArrayList<Skill> skillList, Consumer<Skill> onReleaseSkill) {

    }

    @Override
    public void updateState(Mokepon self) {

    }
}
