package core;

import core.components.Tile;
import core.components.Type;
import core.control.Control;
import core.control.Statistics;
import core.settings.Configuration;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by user on 11.2.2017.
 */
public class Game {

    private Statistics statistics;
    private Tile[][] tiles;

    public Game(int boardSize){
        this.tiles = new Tile[boardSize][boardSize];
        this.statistics = new Statistics();
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public Type getTypeOfTile(double coordX, double coordY) {
        return tiles[(int) coordX / Configuration.tileSize][(int) coordY / Configuration.tileSize].getType();
    }

    public Collection<Tile> getFreeTilesOrOnesAround(Tile tile) {
        Collection<Tile> listOfTiles = new ArrayList<>();
        for (int i = 0; i < Configuration.size; i++) {
            for (int j = 0; j < Configuration.size; j++) {
                if ((Control.isInBounds(i - 1, this) &&
                        Control.isInBounds(j, this) && tiles[i - 1][j] != null &&
                        tiles[i][j].equals(tile)) && (tiles[i - 1][j].getType() == Type.FREE || tiles[i - 1][j].getType() == Type.NUMBER)) {
                    listOfTiles.add(tiles[i - 1][j]);
                }
                if ((Control.isInBounds(i, this) && Control.isInBounds(j - 1,this) && tiles[i][j - 1] != null && tiles[i][j].equals(tile))&& (tiles[i][j-1].getType() == Type.FREE || tiles[i][j-1].getType() == Type.NUMBER)) {
                    listOfTiles.add(tiles[i][j - 1]);
                }
                if ((Control.isInBounds(i + 1, this) && Control.isInBounds(j,this) && tiles[i + 1][j] != null && tiles[i][j].equals(tile))&& (tiles[i + 1][j].getType() == Type.FREE || tiles[i + 1][j].getType() == Type.NUMBER)) {
                    listOfTiles.add(tiles[i + 1][j]);
                }
                if ((Control.isInBounds(i, this) && Control.isInBounds(j + 1, this) && tiles[i][j + 1] != null && tiles[i][j].equals(tile))&& (tiles[i][j+1].getType() == Type.FREE || tiles[i][j+1].getType() == Type.NUMBER)) {
                    listOfTiles.add(tiles[i][j + 1]);
                }
            }
        }
        return listOfTiles;
    }

    public void increaseClickCount(){
        statistics.increaseClickCount();
    }

    public void setGameOn(boolean gameOn) {
        statistics.setGameOn(gameOn);
    }

    public boolean isGameOn() {
        return statistics.isGameOn();
    }

    public int getClickCount() {
        return statistics.getClickCount();
    }

    public void resetClickCount(){
        statistics.resetClickCount();
    }
}
