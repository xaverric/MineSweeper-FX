package core.components;

/**
 * Created by jilek on 09.02.2017.
 */
public class Tile {
    private Type type;
    private int value;
    private double coordX;
    private double coordY;
    private boolean drawn;

    public Tile(Type type, int value, double coordX, double coordY) {
        this.type = type;
        this.value = value;
        this.coordX = coordX;
        this.coordY = coordY;
        this.drawn = false;
    }

    public Tile(Type type, double coordX, double coordY) {
        this.type = type;
        this.coordX = coordX;
        this.coordY = coordY;
    }

    public Type getType() {
        return type;
    }

    public int getValue() {
        return value;
    }

    public double getCoordX() {
        return coordX;
    }

    public double getCoordY() {
        return coordY;
    }

    public boolean getDrawn() {
        return drawn;
    }

    public void setDrawn(boolean drawn) {
        this.drawn = drawn;
    }
}
