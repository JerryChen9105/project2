package au.edu.unimelb.swen.oop.mokepon.util.overworld;

import au.edu.unimelb.swen.oop.mokepon.driven.overworld.Coordinate;

public class CoordinateLoader {

    public static Coordinate getCoordinate(String coordStr) {
        try {
            int x = Integer.parseInt(coordStr.split(",")[0]);
            int y = Integer.parseInt(coordStr.split(",")[1]);
            return new Coordinate(x, y);
        } catch (NumberFormatException _) {}
        return new Coordinate(0, 0);
    }

}
