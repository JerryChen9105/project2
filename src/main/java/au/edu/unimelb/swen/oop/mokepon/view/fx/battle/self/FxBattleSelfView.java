package au.edu.unimelb.swen.oop.mokepon.view.fx.battle.self;

import au.edu.unimelb.swen.oop.mokepon.driven.model.Mokepon;
import au.edu.unimelb.swen.oop.mokepon.driven.model.skill.Skill;
import au.edu.unimelb.swen.oop.mokepon.state.ItemType;
import au.edu.unimelb.swen.oop.mokepon.util.ImageLoader;
import au.edu.unimelb.swen.oop.mokepon.util.ScreenPosition;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.battle.BattleCardView;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.battle.BattleNameView;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.battle.self.BattleItemView;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.battle.self.BattleSelfView;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.battle.self.BattleSkillView;
import javafx.beans.property.IntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.function.Consumer;

public class FxBattleSelfView implements BattleSelfView {


    private BattleCardView selfCardView;
    private BattleItemView selfItemView;
    private BattleSkillView selfSkillView;
    private BattleNameView selfNameView;


    private VBox battleSelfRoot = new VBox(15);

    private StackPane selfCardSkillNameBgBlock = new StackPane();
    private ImageView selfBgImgView = new ImageView();
    private VBox selfCardSkillNameBlock = new VBox(10);

    private HBox selfCardSkillBlock = new HBox(20);


    public FxBattleSelfView(BattleCardView selfCardView,
                            BattleItemView selfItemView,
                            BattleSkillView selfSkillView,
                            BattleNameView selfNameView) {

        this.selfCardView = selfCardView;
        this.selfItemView = selfItemView;
        this.selfSkillView = selfSkillView;
        this.selfNameView = selfNameView;

        this.selfCardView.setTextVisible(true);
        selfBgImgView.setImage(
                ImageLoader.load(ImageLoader.scrollURL, 510, 430, false)
        );

        selfCardSkillBlock.getChildren().addAll(selfCardView.node(), selfSkillView.node());
        selfCardSkillBlock.setAlignment(Pos.CENTER);

        selfCardSkillNameBlock.getChildren().addAll(selfNameView.node(), selfCardSkillBlock);
        selfCardSkillNameBlock.setAlignment(Pos.CENTER);

        selfCardSkillNameBgBlock.getChildren().addAll(selfBgImgView, selfCardSkillNameBlock);
        selfCardSkillNameBgBlock.setAlignment(Pos.CENTER);

        battleSelfRoot.getChildren().addAll(selfItemView.node(), selfCardSkillNameBgBlock);
        battleSelfRoot.setAlignment(Pos.CENTER);
        battleSelfRoot.setLayoutX(ScreenPosition.SCREENLEFT + 40);
        battleSelfRoot.setLayoutY(ScreenPosition.SCREENHEIGHT - 575);
    }

    @Override
    public Node node() {
        return battleSelfRoot;
    }

    @Override
    public void initSelfStatus(Mokepon self, Consumer<Skill> onSkillBtnClicked,
                               HashMap<ItemType, IntegerProperty> itemDeposit,
                               Consumer<ItemType> onUseItem) {
        selfCardView.show(self);
        selfNameView.setText(self.getName());
        selfSkillView.setSkills(self.getMokeponStatus().getSkillList(), onSkillBtnClicked);
        selfItemView.setItems(itemDeposit, onUseItem);
        updateSelfStatus(self);
    }

    @Override
    public void updateSelfStatus(Mokepon self) {
        selfCardView.updateHP(self.getMokeponStatus().getCurrentHP());
        selfCardView.updateEnergy(self.getMokeponStatus().getCurrentEnergy());
    }

    @Override
    public void setDisable(boolean disable) {
        selfSkillView.setDisabled(disable);
        selfItemView.setDisable(disable);
    }


}
