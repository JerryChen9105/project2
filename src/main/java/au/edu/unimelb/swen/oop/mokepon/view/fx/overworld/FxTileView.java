package au.edu.unimelb.swen.oop.mokepon.view.fx.overworld;

import au.edu.unimelb.swen.oop.mokepon.driven.overworld.Coordinate;
import au.edu.unimelb.swen.oop.mokepon.util.TileImgLoader;
import au.edu.unimelb.swen.oop.mokepon.util.overworld.PlayerInfoLoader;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.overworld.TileView;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.HashMap;

public class FxTileView implements TileView {

    private static final double TILE_SIZE = 32;

    private final StackPane wholeTileRoot = new StackPane();
    private final GridPane tileRoot = new GridPane();
    private final GridPane entityRoot = new GridPane();
    private final GridPane playerRoot = new GridPane();

    private final HashMap<Coordinate, ImageView> entityViewMap = new HashMap<>();
    private ImageView playerImgView = PlayerInfoLoader.getImgView();

    public FxTileView() {
        wholeTileRoot.getChildren().addAll(tileRoot, entityRoot, playerRoot);
        StackPane.setAlignment(tileRoot, Pos.TOP_LEFT);
        StackPane.setAlignment(entityRoot, Pos.TOP_LEFT);
        StackPane.setAlignment(playerRoot, Pos.TOP_LEFT);
    }

    @Override
    public Node node() {
        return wholeTileRoot;
    }

    @Override
    public void setTile(ArrayList<ArrayList<Character>> tileCharMatrix) {
        tileRoot.getChildren().clear();
        entityRoot.getChildren().clear();
        playerRoot.getChildren().clear();
        entityViewMap.clear();

        int rows = tileCharMatrix.size();
        int cols = rows == 0 ? 0 : tileCharMatrix.get(0).size();

        setupGridConstraints(tileRoot, cols, rows);
        setupGridConstraints(entityRoot, cols, rows);
        setupGridConstraints(playerRoot, cols, rows);

        for (int y = 0; y < rows; y++) {
            ArrayList<Character> row = tileCharMatrix.get(y);
            for (int x = 0; x < row.size(); x++) {
                Image tileImg = TileImgLoader.getTileImg(row.get(x));
                ImageView tileImgView = new ImageView(tileImg);
                tileRoot.add(tileImgView, x, y);
            }
        }

        playerImgView.setVisible(false);
        playerRoot.add(playerImgView, 0, 0);
    }

    private void setupGridConstraints(GridPane grid, int cols, int rows) {
        grid.getColumnConstraints().clear();
        grid.getRowConstraints().clear();

        for (int c = 0; c < cols; c++) {
            ColumnConstraints cc = new ColumnConstraints(TILE_SIZE);
            cc.setMinWidth(TILE_SIZE);
            cc.setPrefWidth(TILE_SIZE);
            cc.setMaxWidth(TILE_SIZE);
            grid.getColumnConstraints().add(cc);
        }
        for (int r = 0; r < rows; r++) {
            RowConstraints rc = new RowConstraints(TILE_SIZE);
            rc.setMinHeight(TILE_SIZE);
            rc.setPrefHeight(TILE_SIZE);
            rc.setMaxHeight(TILE_SIZE);
            grid.getRowConstraints().add(rc);
        }
    }

    @Override
    public void putEntity(Coordinate coordinate, ImageView entityImgView) {
        popEntity(coordinate);
        entityViewMap.put(coordinate, entityImgView);
        entityRoot.add(entityImgView, coordinate.getX(), coordinate.getY());
    }

    @Override
    public void popEntity(Coordinate coordinate) {
        ImageView entityImgView = entityViewMap.remove(coordinate);
        if (entityImgView != null) {
            entityRoot.getChildren().remove(entityImgView);
        }
    }

    @Override
    public void putPlayer(Coordinate coordinate) {
        playerImgView.setVisible(true);
        GridPane.setColumnIndex(playerImgView, coordinate.getX());
        GridPane.setRowIndex(playerImgView, coordinate.getY());
    }

    @Override
    public void popPlayer() {
        playerImgView.setVisible(false);
    }

    @Override
    public void setVisible(boolean visible) {
        wholeTileRoot.setVisible(visible);
    }

    @Override
    public void clear() {
        tileRoot.getChildren().clear();
        entityRoot.getChildren().clear();
        entityViewMap.clear();
        playerImgView.setVisible(false);
    }
}


