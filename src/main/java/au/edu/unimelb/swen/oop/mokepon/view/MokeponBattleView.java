package au.edu.unimelb.swen.oop.mokepon.view;

import au.edu.unimelb.swen.oop.mokepon.driven.model.Mokepon;
import au.edu.unimelb.swen.oop.mokepon.util.ImageLoader;
import au.edu.unimelb.swen.oop.mokepon.util.ScreenPosition;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.battle.BattleCardView;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.battle.BattleLossMarkerView;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.battle.BattleWinMarkerView;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.battle.enemy.BattleEnemyView;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.battle.self.BattleItemView;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.battle.BattleLogView;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.battle.self.BattleSelfView;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.battle.self.BattleSkillView;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MokeponBattleView {

    private Group battleRoot = new Group();

    private BattleLogView battleLogView;
    private BattleSelfView battleSelfView;
    private BattleEnemyView battleEnemyView;
    private BattleWinMarkerView battleWinMarkerView;
    private BattleLossMarkerView battleLossMarkerView;





    public MokeponBattleView(BattleSelfView battleSelfView,
                             BattleEnemyView battleEnemyView,
                             BattleLogView battleLogView,
                             BattleWinMarkerView battleWinMarkerView,
                             BattleLossMarkerView battleLossMarkerView) {
        this.battleSelfView = battleSelfView;
        this.battleEnemyView = battleEnemyView;
        this.battleLogView = battleLogView;
        this.battleWinMarkerView = battleWinMarkerView;
        this.battleLossMarkerView = battleLossMarkerView;

        battleRoot.getChildren().addAll(battleSelfView.node(),
                battleEnemyView.node(),
                battleLogView.node(),
                battleWinMarkerView.node(),
                battleLossMarkerView.node());
    }


    public Group getMokeponBattleRoot() {
        return battleRoot;
    }

    public BattleSelfView getSelfView() {
        return battleSelfView;
    }

    public BattleEnemyView getEnemyView() {
        return battleEnemyView;
    }

    public BattleLogView getLogView() {
        return battleLogView;
    }

    public BattleWinMarkerView getWinMarkerView() {
        return battleWinMarkerView;
    }

    public BattleLossMarkerView getLossMarkerView() {
        return battleLossMarkerView;
    }

}


