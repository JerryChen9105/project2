package au.edu.unimelb.swen.oop.mokepon.view;

import au.edu.unimelb.swen.oop.mokepon.view.view_interface.overworld.*;
import javafx.scene.Group;

public class MokeponOverworldView {


    private TileView tileView;
    private ItemView itemView;
    private MokeponEnemyView mokeponEnemyView;
    private MokeponPlayerView mokeponPlayerView;
    private MovementView movementView;
    private PlayerStatisticView playerStatisticView;
    private StatusBarView statusBarView;
    private BattleMarkerView battleMarkerView;
    private ExitMarkerView exitMarkerView;

    private Group mokeponOverworldRoot = new Group();


    public MokeponOverworldView(TileView tileView,
                                ItemView itemView,
                                MokeponEnemyView mokeponEnemyView,
                                MokeponPlayerView mokeponPlayerView,
                                MovementView movementView,
                                PlayerStatisticView playerStatisticView,
                                StatusBarView statusBarView,
                                BattleMarkerView battleMarkerView,
                                ExitMarkerView exitMarkerView) {
        this.tileView = tileView;
        this.itemView = itemView;
        this.mokeponEnemyView = mokeponEnemyView;
        this.mokeponPlayerView = mokeponPlayerView;
        this.movementView = movementView;
        this.playerStatisticView = playerStatisticView;
        this.statusBarView = statusBarView;
        this.battleMarkerView = battleMarkerView;
        this.exitMarkerView = exitMarkerView;
    }


    public Group getMokeponOverworldRoot() {
        return mokeponOverworldRoot;
    }

    public TileView getTileView() {
        return tileView;
    }

    public ItemView getItemView() {
        return itemView;
    }

    public MokeponEnemyView getMokeponEnemyView() {
        return mokeponEnemyView;
    }

    public MokeponPlayerView getMokeponPlayerView() {
        return mokeponPlayerView;
    }

    public MovementView getMovementView() {
        return movementView;
    }

    public PlayerStatisticView getPlayerStatisticView() {
        return playerStatisticView;
    }

    public StatusBarView getStatusBarView() {
        return statusBarView;
    }

    public BattleMarkerView getMokeponMarkerView() {
        return battleMarkerView;
    }

    public ExitMarkerView getExitMarkerView() {
        return exitMarkerView;
    }
}
