package au.edu.unimelb.swen.oop.mokepon.view.fx.select;

import au.edu.unimelb.swen.oop.mokepon.driven.model.Mokepon;
import au.edu.unimelb.swen.oop.mokepon.driven.model.MokeponType;
import au.edu.unimelb.swen.oop.mokepon.util.FontLoader;
import au.edu.unimelb.swen.oop.mokepon.util.ImageLoader;
import au.edu.unimelb.swen.oop.mokepon.util.MokeponTypeImageLoader;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.select.MokeponDetailView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;

import static au.edu.unimelb.swen.oop.mokepon.util.ScreenPosition.*;

public class FxMokeponDetailView implements MokeponDetailView {

    private final static Font font = FontLoader.getGostischFont(30);

    private StackPane mokeponImgBlock = new StackPane();
    private ImageView mokeponImgView = new ImageView(ImageLoader.load("/assets/scroll.png", 256, 256));
    private Circle mokeponImgViewBg = new Circle(0.127778 * SCREENWIDTH);

    private HBox mokeponNameBlock = new HBox(10);
    private Text mokeponName = new Text("Select your Mokepon");
    private ImageView mokeponTypeImgView = new ImageView();

    private StackPane attrDetailBlock = new StackPane();
    private ImageView scrollImgView = new ImageView(
            ImageLoader.load(ImageLoader.scrollURL,
                    0.32 * SCREENWIDTH + 20,
                    0.43 * SCREENHEIGHT - 35,
                    false
            )
    );
    private final String[] attrArr = {
            "HP", "Energy", "Strength", "Accuracy", "Defence", "Dodge", "Skills", "Type"
    };

    private HBox attrTxtTable = new HBox(40);
    private VBox attrTxtPrefix = new VBox(10);
    private VBox attrTxtRealValue = new VBox(10);

    private ArrayList<Text> attrTxtList = new ArrayList<>();


    private VBox mokeponDetailBlock = new VBox(10);


    public FxMokeponDetailView() {
        mokeponImgView.setImage(null);
        mokeponImgViewBg.setStyle("-fx-fill: #9a837c;" +
                "-fx-stroke: #000000;" +
                "-fx-stroke-width: 2px;");
        mokeponImgBlock.getChildren().addAll(mokeponImgViewBg, mokeponImgView);


        mokeponName.setFont(font);
        mokeponName.setStyle("-fx-fill: #000000;" +
                "-fx-stroke: #ffffff;" +
                "-fx-stroke-width: 2px;");
        mokeponNameBlock.getChildren().addAll(mokeponName, mokeponTypeImgView);
        mokeponNameBlock.setAlignment(Pos.CENTER);


        for (int i = 0; i < attrArr.length; i++) {
            Text attrTxt = new Text(attrArr[i] + ":");
            attrTxt.setFont(Font.font("default", FontWeight.BOLD, 15));
            Text appendTxt = new Text();
            appendTxt.setFont(Font.font("default", 15));
            attrTxtList.add(appendTxt);

            if (i == attrArr.length - 1) {
                attrTxtPrefix.getChildren().add(new Text());
                attrTxtRealValue.getChildren().add(new Text());
            }
            attrTxtPrefix.getChildren().add(attrTxt);
            attrTxtRealValue.getChildren().add(appendTxt);
        }

        attrTxtPrefix.setAlignment(Pos.CENTER_LEFT);
        attrTxtRealValue.setAlignment(Pos.CENTER_LEFT);

        attrTxtTable.getChildren().addAll(attrTxtPrefix, attrTxtRealValue);
        attrTxtTable.setMaxSize(0.32 * SCREENWIDTH + 20, 0.43 * SCREENHEIGHT - 40);

        attrDetailBlock.getChildren().addAll(scrollImgView, attrTxtTable);
        attrDetailBlock.setAlignment(Pos.CENTER);
        StackPane.setAlignment(attrTxtTable, Pos.CENTER_LEFT);
        StackPane.setMargin(attrTxtTable, new Insets(0, 0, 0, 50));


        mokeponDetailBlock.getChildren().addAll(mokeponImgBlock, mokeponNameBlock, attrDetailBlock);
        mokeponDetailBlock.setAlignment(Pos.CENTER);
    }


    public ImageView getMokeponImgView() {
        return mokeponImgView;
    }

    public Text getMokeponName() {
        return mokeponName;
    }

    public void setMokeponImgView(Image newImg) {
        mokeponImgView.setImage(newImg);
    }

    public void setMokeponName(String newName) {
        if (newName == null || newName.isBlank()) newName = "Select your Mokepon";
        mokeponName.setText(newName);
    }

    public void setMokeponTypeImgView(MokeponType mokeponType) {
        mokeponTypeImgView.setImage(MokeponTypeImageLoader.getTypeImg(mokeponType));
    }


    @Override
    public Node node() {
        return mokeponDetailBlock;
    }

    @Override
    public void show(Mokepon mokepon) {
        if (mokepon == null) {
            clear();
        } else {
            setMokeponImgView(ImageLoader.load("/assets/" + mokepon.getName() + ".png", 256, 256));
            setMokeponName(mokepon.getName());
            setMokeponTypeImgView(mokepon.getMokeponType());
            attrTxtList.get(0).setText(mokepon.getMaxHP() + "");
            attrTxtList.get(1).setText(mokepon.getMaxEnergy() + "");
            attrTxtList.get(2).setText(mokepon.getBaseStrength() + "");
            attrTxtList.get(3).setText(mokepon.getBaseAccuracy() + "");
            attrTxtList.get(4).setText(mokepon.getBaseDefence() + "");
            attrTxtList.get(5).setText(mokepon.getBaseDodge() + "");
            attrTxtList.get(6).setText(String.join(", ", mokepon.getSkills()));
            attrTxtList.get(7).setText(mokepon.getMokeponType() + "");
        }
    }

    @Override
    public void clear() {
        setMokeponImgView(null);
        setMokeponName(null);
        setMokeponTypeImgView(null);
        attrTxtList.forEach(text -> {
            text.setText("");
        });
    }

}

