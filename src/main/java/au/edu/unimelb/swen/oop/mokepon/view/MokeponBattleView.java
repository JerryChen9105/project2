package au.edu.unimelb.swen.oop.mokepon.view;

import au.edu.unimelb.swen.oop.mokepon.driven.model.Mokepon;
import au.edu.unimelb.swen.oop.mokepon.util.ImageLoader;
import au.edu.unimelb.swen.oop.mokepon.util.ScreenPosition;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.battle.BattleCardView;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.battle.BattleItemView;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.battle.BattleLogView;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.battle.BattleSkillView;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.bg.ButtonControl;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MokeponBattleView {

    // main root
    private Group battleRoot = new Group();

    private BattleLogView battleLogView;

    private ButtonControl buttonControl;

    //============================================


    //self block
    private BattleCardView selfCardView;
    private BattleSkillView selfSkillView;
    private BattleItemView selfItemView;


    private VBox battleSelfBlock = new VBox(15);

    private HBox selfItemBlock;

    private StackPane selfCardSkillNameBgBlock = new StackPane();
    private ImageView selfBgImgView = new ImageView();
    private VBox selfCardSkillNameBlock = new VBox(15);

    private Text selfNameTxt = new Text();
    private HBox selfCardSkillBlock = new HBox(15);

    private VBox selfCardBlock;

    private VBox selfSkillBlock;
    //============================================


    //enemy block
    private BattleCardView enemyCardView;


    private StackPane battleEnemyBlock = new StackPane();

    private ImageView enemyBgImgView = new ImageView();
    private VBox enemyCardNameBlock = new VBox(10);

    private Text enemyNameTxt = new Text();
    private VBox enemyCardBlock;
    //============================================



    private Mokepon selfMokepon = null;
    private Mokepon enemyMokepon = null;




    public MokeponBattleView(BattleCardView selfCardView,
                        BattleSkillView selfSkillView,
                        BattleItemView selfItemView,
                        BattleCardView enemyCardView,
                        BattleLogView battleLogView,
                        ButtonControl buttonControl) {
        this.selfCardView = selfCardView;
        this.selfSkillView = selfSkillView;
        this.selfItemView = selfItemView;
        this.enemyCardView = enemyCardView;
        this.battleLogView = battleLogView;
        this.buttonControl = buttonControl;

        this.selfItemBlock = (HBox)this.selfItemView.node();
        this.selfCardBlock = (VBox)this.selfCardView.node();
        this.selfSkillBlock = (VBox)this.selfSkillView.node();
        this.enemyCardBlock = (VBox)this.enemyCardView.node();
        selfBgImgView.setImage(
                ImageLoader.load(ImageLoader.scrollURL, 530, 430, false)
        );
        enemyBgImgView.setImage(
                ImageLoader.load(ImageLoader.scrollURL, 320, 420, false)
        );

        //self block
        selfCardSkillBlock.getChildren().addAll(this.selfCardBlock, this.selfSkillBlock);
        selfCardSkillBlock.setAlignment(Pos.CENTER);

        selfCardSkillNameBlock.getChildren().addAll(selfNameTxt, selfCardSkillBlock);
        selfCardSkillNameBlock.setAlignment(Pos.CENTER);

        selfCardSkillNameBgBlock.getChildren().addAll(selfBgImgView, selfCardSkillNameBlock);
        selfCardSkillNameBgBlock.setAlignment(Pos.CENTER);

        battleSelfBlock.getChildren().addAll(selfItemBlock, selfCardSkillNameBgBlock);
        battleSelfBlock.setAlignment(Pos.CENTER);
        battleSelfBlock.setLayoutX(ScreenPosition.SCREENLEFT + 40);
        battleSelfBlock.setLayoutY(ScreenPosition.SCREENHEIGHT - 470);

        //enemy block
        enemyCardNameBlock.getChildren().addAll(enemyNameTxt, enemyCardBlock);
        enemyCardNameBlock.setAlignment(Pos.CENTER);

        battleEnemyBlock.getChildren().addAll(enemyBgImgView, enemyCardBlock);
        battleEnemyBlock.setAlignment(Pos.CENTER);
        battleEnemyBlock.setLayoutX(ScreenPosition.SCREENWIDTH - 460);
        battleEnemyBlock.setLayoutY(ScreenPosition.SCREENTOP + ScreenPosition.MENUBARHEIGHT + 30);

        //main root
        battleRoot.getChildren().addAll(battleSelfBlock, battleEnemyBlock);
        battleRoot.getChildren().addAll(battleLogView.node(), buttonControl.node());
    }


    public Group getMokeponBattleRoot() {
        return battleRoot;
    }

    public BattleCardView getSelfCard() {
        return selfCardView;
    }

    public BattleCardView getEnemyCard() {
        return enemyCardView;
    }

    public BattleSkillView getSkillView() {
        return selfSkillView;
    }

    public BattleLogView getLogView() {
        return battleLogView;
    }

    public ButtonControl getBackButton() {
        return buttonControl;
    }


}
