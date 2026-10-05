package au.edu.unimelb.swen.oop.mokepon.view.fx.overworld;

import au.edu.unimelb.swen.oop.mokepon.driven.overworld.Coordinate;
import au.edu.unimelb.swen.oop.mokepon.util.ImageLoader;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.overworld.EntityType;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.overworld.TileView;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.HashMap;

public class FxTileView implements TileView {

    private static final double TILE_SIZE = 32;

    private GridPane tileRoot = new GridPane();
    private HashMap<Coordinate, ImageView> entityViewMap = new HashMap<>();
    private ArrayList<ArrayList<Character>> tileCharMatrix;


    public FxTileView() {
    }


    @Override
    public Node node() {
        return tileRoot;
    }

    @Override
    public void setTile(ArrayList<ArrayList<Character>> tileCharMatrix) {
        this.tileCharMatrix = tileCharMatrix;
        tileRoot.getChildren().clear();
        entityViewMap.clear();

        for (int y = 0; y < tileCharMatrix.size(); y++) {
            ArrayList<Character> row = tileCharMatrix.get(y);
            for (int x = 0; x < row.size(); x++) {
                Image tileImg = ImageLoader.load(
                        "/assets/tile_" + row.get(x) + ".png", TILE_SIZE, TILE_SIZE);
                ImageView tileImgView = new ImageView(tileImg);
                tileRoot.add(tileImgView, x, y);
            }
        }
    }

    @Override
    public void putEntity(Coordinate coordinate, EntityType entityType) {
        Image entityImg = ImageLoader.load(
                "/assets/" + entityType.name().toLowerCase() + ".png", TILE_SIZE, TILE_SIZE);
        ImageView entityImgView = new ImageView(entityImg);
        entityViewMap.put(coordinate, entityImgView);
        tileRoot.add(entityImgView, coordinate.getX(), coordinate.getY());
    }

    @Override
    public void popEntity(Coordinate coordinate) {
        ImageView entityImgView = entityViewMap.remove(coordinate);
        if (entityImgView != null) {
            tileRoot.getChildren().remove(entityImgView);
        }
    }

    @Override
    public void setVisible(boolean visible) {
        tileRoot.setVisible(visible);
    }
}
