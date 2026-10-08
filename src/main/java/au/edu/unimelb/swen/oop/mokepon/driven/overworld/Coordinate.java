package au.edu.unimelb.swen.oop.mokepon.driven.overworld;

public class Coordinate {

    private int x, y;


    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(x, y);
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int nx) {
        x = nx;
    }
    public void setY(int ny) {
        y = ny;
    }

    public void setXY(int nx, int ny) {
        this.setX(nx);
        this.setY(ny);
    }

    public void setXY(Coordinate coordinate) {
        this.setXY(coordinate.getX(), coordinate.getY());
    }

}


