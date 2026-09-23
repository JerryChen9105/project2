package au.edu.unimelb.swen.oop.mokepon.UIElement.StaticUI.BattleScreen;

import javafx.scene.image.ImageView;

import static au.edu.unimelb.swen.oop.mokepon.UIElement.Utils.ImageMethod.createImageView;

public class SelfBlock {

    public static  ImageView  createSelfBG() {

        ImageView selfBg = createImageView("/assets/scroll.png", 530, 430, false);

        return selfBg;
    }


}
