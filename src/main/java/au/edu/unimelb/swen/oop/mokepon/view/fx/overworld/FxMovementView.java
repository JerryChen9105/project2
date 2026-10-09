package au.edu.unimelb.swen.oop.mokepon.view.fx.overworld;

import au.edu.unimelb.swen.oop.mokepon.util.overworld.MovementBtnImageLoader;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.overworld.MovementView;
import javafx.animation.PauseTransition;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.util.Duration;

public class FxMovementView implements MovementView {

    private GridPane movementRoot = new GridPane();
    private Button upBtn = new Button("", MovementBtnImageLoader.getBtnImgView("up"));
    private Button downBtn = new Button("", MovementBtnImageLoader.getBtnImgView("down"));
    private Button leftBtn = new Button("", MovementBtnImageLoader.getBtnImgView("left"));
    private Button rightBtn = new Button("", MovementBtnImageLoader.getBtnImgView("right"));

    private Button[] btnArr = new Button[]{upBtn, downBtn, leftBtn, rightBtn};


    private static final double BTN_SIZE = 50;


    public FxMovementView() {
        setupGridConstraints(movementRoot, 3, 2);

        for (Button btn : btnArr) {
            btn.setStyle("-fx-pref-width: 50;" +
                    "-fx-pref-height: 50;" +
                    "-fx-background-radius: 999px;" +
                    "-fx-background-color: #d3d3d3");
            btn.setOpacity(0.5);
            btn.setOnAction(click -> {
                btn.setOpacity(0.2);
            });
            btn.setOnMouseExited(exit -> {
                btn.setOpacity(0.5);
            });
        }

        movementRoot.setFocusTraversable(true);
        movementRoot.setOnKeyPressed(press -> {
            switch (press.getCode()) {
                case UP    -> upBtn.fire();
                case DOWN  -> downBtn.fire();
                case LEFT  -> leftBtn.fire();
                case RIGHT -> rightBtn.fire();
            }
        });

        movementRoot.add(upBtn, 1, 0);
        movementRoot.add(downBtn, 1, 1);
        movementRoot.add(leftBtn, 0, 1);
        movementRoot.add(rightBtn, 2, 1);
    }


    private void setupGridConstraints(GridPane grid, int cols, int rows) {
        grid.getColumnConstraints().clear();
        grid.getRowConstraints().clear();

        for (int c = 0; c < cols; c++) {
            ColumnConstraints cc = new ColumnConstraints(BTN_SIZE + 5);
            cc.setMinWidth(BTN_SIZE + 5);
            cc.setPrefWidth(BTN_SIZE + 5);
            cc.setMaxWidth(BTN_SIZE + 5);
            grid.getColumnConstraints().add(cc);
        }
        for (int r = 0; r < rows; r++) {
            RowConstraints rc = new RowConstraints(BTN_SIZE + 5);
            rc.setMinHeight(BTN_SIZE + 5);
            rc.setPrefHeight(BTN_SIZE + 5);
            rc.setMaxHeight(BTN_SIZE + 5);
            grid.getRowConstraints().add(rc);
        }
    }

    private void attachButton(Button btn, Runnable action) {
        btn.setOnAction(e -> {
            btn.setOpacity(0.2);
            action.run();
            PauseTransition pause = new PauseTransition(Duration.millis(120));
            pause.setOnFinished(ev -> btn.setOpacity(0.5));
            pause.play();
        });
    }


    @Override
    public Node node() {
        return movementRoot;
    }

    @Override
    public void setUpAction(Runnable upAction) {
        upBtn.setOnAction(action -> upAction.run());
    }

    @Override
    public void setDownAction(Runnable downAction) {
        downBtn.setOnAction(action -> downAction.run());
    }

    @Override
    public void setLeftAction(Runnable leftAction) {
        leftBtn.setOnAction(action -> leftAction.run());
    }

    @Override
    public void setRightAction(Runnable rightAction) {
        rightBtn.setOnAction(action -> rightAction.run());
    }

    @Override
    public void setDisable(boolean disable) {
        for (Button btn : btnArr) btn.setDisable(disable);
    }
}

