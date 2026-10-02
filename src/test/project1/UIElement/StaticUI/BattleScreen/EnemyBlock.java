package project1.UIElement.StaticUI.BattleScreen;

import javafx.scene.image.ImageView;

import static project1.UIElement.Utils.ImageMethod.createImageView;

public class EnemyBlock {

    public static ImageView createEnemyBG() {

        ImageView enemyBg = createImageView("/assets/scroll.png", 320, 420, false);

        return enemyBg;
    }

}
