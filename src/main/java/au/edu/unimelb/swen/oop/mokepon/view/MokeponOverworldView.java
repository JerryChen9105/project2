package au.edu.unimelb.swen.oop.mokepon.view;

import au.edu.unimelb.swen.oop.mokepon.util.ScreenPosition;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.overworld.*;
import javafx.scene.Group;

public class MokeponOverworldView {


    private TileView tileView;
    private MovementView movementView;
    private PlayerStatisticView playerStatisticView;
    private StatusBarView statusBarView;
    private EncounterMarkerView encounterMarkerView;
    private ExitMarkerView exitMarkerView;

    private Group mokeponOverworldRoot = new Group();


    public MokeponOverworldView(TileView tileView,
                                MovementView movementView,
                                PlayerStatisticView playerStatisticView,
                                StatusBarView statusBarView,
                                EncounterMarkerView battleMarkerView,
                                ExitMarkerView exitMarkerView) {
        this.tileView = tileView;
        this.movementView = movementView;
        this.playerStatisticView = playerStatisticView;
        this.statusBarView = statusBarView;
        this.encounterMarkerView = battleMarkerView;
        this.exitMarkerView = exitMarkerView;

        tileView.node().setLayoutX(ScreenPosition.relativeCenterX(ScreenPosition.SCREENWIDTH / 2 - 10, 20 * 32));
        tileView.node().setLayoutY(60);

        playerStatisticView.node().setLayoutX(ScreenPosition.SCREENWIDTH - 320);
        playerStatisticView.node().setLayoutY(60);

        statusBarView.node().setLayoutX(ScreenPosition.relativeCenterX(ScreenPosition.SCREENWIDTH / 2 - 10, 450));
        statusBarView.node().setLayoutY(ScreenPosition.SCREENHEIGHT - 85);

        movementView.node().setLayoutX(ScreenPosition.SCREENWIDTH - 300);
        movementView.node().setLayoutY(ScreenPosition.SCREENHEIGHT - 170);

        mokeponOverworldRoot.getChildren().addAll(tileView.node(),
                playerStatisticView.node(),
                statusBarView.node(),
                movementView.node(),
                exitMarkerView.node());
    }


    public Group getMokeponOverworldRoot() {
        return mokeponOverworldRoot;
    }

    public TileView getTileView() {
        return tileView;
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

    public EncounterMarkerView getMokeponMarkerView() {
        return encounterMarkerView;
    }

    public ExitMarkerView getExitMarkerView() {
        return exitMarkerView;
    }
}


