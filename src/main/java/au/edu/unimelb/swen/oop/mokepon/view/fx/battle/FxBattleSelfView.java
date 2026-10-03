package au.edu.unimelb.swen.oop.mokepon.view.fx.battle;

import au.edu.unimelb.swen.oop.mokepon.battle.skill.Skill;
import au.edu.unimelb.swen.oop.mokepon.model.Mokepon;
import au.edu.unimelb.swen.oop.mokepon.state.ItemType;
import au.edu.unimelb.swen.oop.mokepon.util.FontLoader;
import au.edu.unimelb.swen.oop.mokepon.util.ImageLoader;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.BattleSelfView;
import javafx.beans.property.IntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.function.Consumer;

public class FxBattleSelfView implements BattleSelfView {

    private VBox battleSelfBlock = new VBox(15);


    private HBox itemBtnBlock = new HBox(10);
    private ArrayList<Button> itemBtnList = new ArrayList<>();


    private StackPane selfStateBlock = new StackPane();
    private ImageView scrollImgView = new ImageView();
    private VBox selfStateSkillNameBlock = new VBox(15);


    private Text selfNameTxt = new Text();
    private HBox selfStateSkillBlock = new HBox(15);


    private VBox selfImgStateBlock = new VBox(5);
    private ImageView selfImgView = new ImageView();
    private StackPane[] selfBarArr = new StackPane[2];
    private Text[] selfBarTxtArr = new Text[2];
    private ProgressBar[] selfProgressBarArr = new ProgressBar[2];


    private VBox selfSkillBlock = new VBox(5);
    private Text skillTitle = new Text("Actions");
    private ArrayList<Button> skillBtnList = new ArrayList<>();


    private Mokepon selfMokepon = null;
    private Font skillItemFont = FontLoader.getGostischFont(18);



    public FxBattleSelfView() {

        for (ItemType itemType : ItemType.values()) {
            Button itemBtn = new Button("0", new ImageView(ItemType.getItemImg(itemType)));
            itemBtn.setOpacity(0.2);
            itemBtn.setStyle("-fx-pref-width: 50;" +
                    "-fx-pref-height: 75;" +
                    "-fx-background-color: #d3d3d3;" +
                    "-fx-background-radius: 25px;" +
                    "-fx-text-fill: #000000");
            itemBtn.setFont(skillItemFont);
            itemBtn.textProperty().addListener((observable, oldValue, newValue) -> {
                if (Integer.parseInt(newValue) <= 0) {
                    itemBtn.setDisable(true);
                } else if (Integer.parseInt(oldValue) <= 0 && Integer.parseInt(newValue) > 0){
                    itemBtn.setDisable(false);
                }
            });
            itemBtnList.add(itemBtn);
        }


        scrollImgView.setImage(ImageLoader.load(ImageLoader.scrollURL, 530, 430, false));


        selfProgressBarArr[0].setStyle("-fx-pref-width: 250;" +
                "-fx-pref-height: 27;" +
                "-fx-accent: #31c831;");
        selfProgressBarArr[0].setProgress(1.0);
        selfBarTxtArr[0].setStyle("-fx-font-size: 16");
        selfProgressBarArr[1].setStyle("-fx-pref-width: 250;" +
                "-fx-pref-height: 27;" +
                "-fx-accent: #3e64d8;");
        selfProgressBarArr[1].setProgress(1.0);
        selfBarTxtArr[1].setStyle("-fx-font-size: 16");



        selfImgStateBlock.getChildren().add(selfImgView); selfImgStateBlock.getChildren().addAll(selfBarArr);
        selfImgStateBlock.setAlignment(Pos.CENTER);
        selfSkillBlock.getChildren().add(skillTitle);
        selfSkillBlock.setAlignment(Pos.CENTER);

        selfStateSkillBlock.getChildren().addAll(selfImgStateBlock, selfSkillBlock);
        selfStateSkillBlock.setAlignment(Pos.CENTER);

        selfStateSkillNameBlock.getChildren().addAll(selfNameTxt, selfStateSkillBlock);
        selfStateSkillNameBlock.setAlignment(Pos.CENTER);

        selfStateBlock.getChildren().addAll(scrollImgView, selfStateSkillNameBlock);
        selfStateBlock.setAlignment(Pos.CENTER);
        itemBtnBlock.getChildren().addAll(itemBtnList);
        itemBtnBlock.setAlignment(Pos.CENTER);

        battleSelfBlock.getChildren().addAll(itemBtnBlock, selfStateBlock);
        battleSelfBlock.setAlignment(Pos.CENTER);
    }




    @Override
    public Node node() {
        return null;
    }

    @Override
    public void setSkills(ArrayList<Skill> skillList, Consumer<Skill> onReleaseSkill) {
        if (skillList.size() <= 0) return;

        skillList.forEach(skill -> {
            Button skillBtn = new Button(skill.getName() + "[" + skill.getEnergyCost() + "]");
            skillBtn.setFont(skillItemFont);
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
                onReleaseSkill.accept(skill);
            });
            skillBtnList.add(skillBtn);
        });
        selfSkillBlock.getChildren().addAll(skillBtnList);
    }

    @Override
    public void setItems(ArrayList<IntegerProperty> itemDeposit, Consumer<IntegerProperty> onUseItem) {
        if (itemDeposit.size() != itemBtnList.size()) return;

        int btnIndex = 0;
        itemBtnList.forEach(itemBtn -> {
            itemBtn.setOnMouseEntered(enter -> {
                itemBtn.setOpacity(0.5);
            });
            itemBtn.setOnMouseExited(exit -> {
                itemBtn.setOpacity(0.2);
            });
            itemBtn.setOnMouseClicked(event -> {
                itemBtn.setOpacity(0.75);
                onUseItem.accept(itemDeposit.get(btnIndex));
            });
        });
    }

    @Override
    public void setMokeponImg(Image img) {
        selfImgView.setImage(img);
    }

    @Override
    public void updateState(Mokepon self) {

    }
}






/*
VBox skillBlock = new VBox(15);
        skillBlock.setLayoutX(screenLeft + 365);
        skillBlock.setLayoutY(screenHeight + menuBarHeight - 375);
        skillBlock.setAlignment(Pos.CENTER);

Text skillTitle = new Text("Actions");
        skillTitle.setFont(titleFont);

VBox skillBtnBlock = new VBox(10);
        skillBtnBlock.setAlignment(Pos.CENTER);
        for (String skill : skillArr) {
Button btn = new Button(skill);
            btn.setFont(btnFont);
            btn.setStyle("-fx-background-radius: 999px;" +
                                 "-fx-background-color: #d3d3d3;" +
                                 "-fx-text-fill: #000000;"
);
            btn.setOpacity(0.2);
            btn.setPrefSize(120, 30);
            skillBtnBlock.getChildren().add(btn);
            skillBtnList.add(btn);
        }

                skillBlock.getChildren().addAll(skillTitle, skillBtnBlock);*/






