package au.edu.unimelb.swen.oop.mokepon.view.fx.battle;

import au.edu.unimelb.swen.oop.mokepon.driven.model.skill.Skill;
import au.edu.unimelb.swen.oop.mokepon.util.FontLoader;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.battle.BattleSkillView;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.function.Consumer;

public class FxBattleSkillView implements BattleSkillView {


    private VBox battleSkillRoot = new VBox(5);
    private Text skillTitle = new Text("Actions");
    private ArrayList<Button> skillBtnList = new ArrayList<>();

    private Font skillFont = FontLoader.getGostischFont(18);


    public FxBattleSkillView() {
        battleSkillRoot.getChildren().add(skillTitle);
        battleSkillRoot.setAlignment(Pos.CENTER);
    }



    @Override
    public Node node() {
        return battleSkillRoot;
    }

    @Override
    public void setSkills(ArrayList<Skill> skillList, Consumer<Skill> onSkillClicked) {
        if (skillList.isEmpty()) return;

        skillList.forEach(skill -> {
            Button skillBtn = new Button(skill.getName() + "[" + skill.getEnergyCost() + "]");
            skillBtn.setFont(skillFont);
            skillBtn.setStyle("-fx-pref-width: 120;" +
                    "-fx-pref-height: 30;" +
                    "-fx-background-radius: 999px;" +
                    "-fx-background-color: #d3d3d3;" +
                    "-fx-text-fill: #000000;");
            skillBtn.setOpacity(0.2);
            skillBtn.setOnMouseEntered(enter -> {
                skillBtn.setOpacity(0.5);
            });
            skillBtn.setOnMouseExited(exit -> {
                skillBtn.setOpacity(0.2);
            });
            skillBtn.setOnMouseClicked(ckick -> {
                skillBtn.setOpacity(0.75);
                onSkillClicked.accept(skill);
            });
            skillBtnList.add(skillBtn);
        });
        battleSkillRoot.getChildren().addAll(skillBtnList);
    }

    @Override
    public void setDisabled(boolean disabled) {
        skillBtnList.forEach(skillBtn -> {
            skillBtn.setDisable(disabled);
        });
    }
}
