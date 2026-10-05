package au.edu.unimelb.swen.oop.mokepon.view.view_interface.overworld;

import au.edu.unimelb.swen.oop.mokepon.driven.overworld.Coordinate;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.View;

import java.util.ArrayList;

public interface TileView extends View {

    public void setTile(ArrayList<ArrayList<Character>> tileCharMatrix);

    public void putEntity(Coordinate coordinate, EntityType entityType);

    public void popEntity(Coordinate coordinate);

    public void setVisible(boolean visible);

}
