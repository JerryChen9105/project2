package au.edu.unimelb.swen.oop.mokepon.view.fx.battle.enemy;

import au.edu.unimelb.swen.oop.mokepon.driven.model.Mokepon;
import au.edu.unimelb.swen.oop.mokepon.util.ImageLoader;
import au.edu.unimelb.swen.oop.mokepon.util.ScreenPosition;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.battle.BattleCardView;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.battle.BattleNameView;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.battle.enemy.BattleEnemyView;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class FxBattleEnemyView implements BattleEnemyView {

    private BattleCardView enemyCardView;
    private BattleNameView enemyNameView;


    private StackPane battleEnemyRoot = new StackPane();

    private ImageView enemyBgImgView = new ImageView();
    private VBox enemyCardNameBlock = new VBox(5);


    public FxBattleEnemyView(BattleCardView enemyCardView,
                             BattleNameView enemyNameView) {
        this.enemyCardView = enemyCardView;
        this.enemyNameView = enemyNameView;

        this.enemyCardView.setTextVisible(false);
        enemyBgImgView.setImage(
                ImageLoader.load(ImageLoader.scrollURL, 320, 420, false)
        );

        enemyCardNameBlock.getChildren().addAll(enemyNameView.node(), enemyCardView.node());
        enemyCardNameBlock.setAlignment(Pos.CENTER);

        battleEnemyRoot.getChildren().addAll(enemyBgImgView, enemyCardNameBlock);
        battleEnemyRoot.setAlignment(Pos.CENTER);
        battleEnemyRoot.setLayoutX(ScreenPosition.SCREENWIDTH - 380);
        battleEnemyRoot.setLayoutY(ScreenPosition.SCREENTOP + ScreenPosition.MENUBARHEIGHT + 35);
    }

    @Override
    public Node node() {
        return battleEnemyRoot;
    }

    @Override
    public void initEnemyStatus(Mokepon enemy) {
        enemyCardView.show(enemy);
        enemyNameView.setText(enemy.getName());
        updateEnemyStatus(enemy);
    }

    @Override
    public void updateEnemyStatus(Mokepon enemy) {
        enemyCardView.updateHP(enemy.getMokeponStatus().getCurrentHP());
        enemyCardView.updateEnergy(enemy.getMokeponStatus().getCurrentEnergy());
    }

}


