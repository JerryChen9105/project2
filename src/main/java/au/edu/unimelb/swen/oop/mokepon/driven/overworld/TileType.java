package au.edu.unimelb.swen.oop.mokepon.driven.overworld;

public enum TileType {

    BARRIER('B'), FOREST('F'), GRASS('G'), OCEAN('O'), PATH('P'),
    ROCKS('R'), WATER('W'), EMPTY('\0'), UNKNOWN('?');

    private final char tileChar;

    TileType(char tileChar) {
        this.tileChar = tileChar;
    }

    public char getTileChar() {
        return tileChar;
    }

    public static TileType getTileType(char tileChar) {
        for (TileType tileType : values()) {
            if (tileType.tileChar == tileChar) return tileType;
        }
        return UNKNOWN;
    }

    public boolean isWalkable() {
        return this.tileChar != 'B' && this.tileChar != 'O' && this.tileChar != '\0'
                && this.tileChar != '?';
    }
}


