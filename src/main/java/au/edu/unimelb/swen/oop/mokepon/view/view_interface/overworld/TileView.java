package au.edu.unimelb.swen.oop.mokepon.view.view_interface.overworld;

import au.edu.unimelb.swen.oop.mokepon.driven.overworld.Coordinate;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.View;
import javafx.scene.image.ImageView;


import java.util.ArrayList;

public interface TileView extends View {

    public void setTile(ArrayList<ArrayList<Character>> tileCharMatrix);

    public void putEntity(Coordinate coordinate, ImageView entityImgView);


    public void popEntity(Coordinate coordinate);

    public void putPlayer(Coordinate coordinate);

    public void popPlayer();

    public void setVisible(boolean visible);

    public void clear();

}

