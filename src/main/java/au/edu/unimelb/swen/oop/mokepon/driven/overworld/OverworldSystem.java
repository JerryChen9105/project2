package au.edu.unimelb.swen.oop.mokepon.driven.overworld;

import au.edu.unimelb.swen.oop.mokepon.driven.model.Mokepon;
import au.edu.unimelb.swen.oop.mokepon.driven.model.skill.MokeponIntensity;
import au.edu.unimelb.swen.oop.mokepon.state.ItemType;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class OverworldSystem {

    private WorldMap worldMap;

    private Coordinate player;

    private LinkedHashMap<Coordinate, MokeponIntensity> coordinateEnemyMap;

    private LinkedHashMap<Coordinate, ItemType> itemTypeMap;



    public OverworldSystem() {

    }

}
