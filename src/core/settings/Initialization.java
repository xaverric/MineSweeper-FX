package core.settings;

import core.control.Control;
import core.Game;
import core.components.Tile;
import core.components.Type;

/**
 * Created by user on 12.2.2017.
 */
public class Initialization {

    public static void initGame(Game game){
        game.setGameOn(true);
        initToFree(game);
        placeBombs(game);
        initNumbers(game);
    }

    private static void initToFree(Game game) {
        for (int i = 0; i < Configuration.size; i++) {
            for (int j = 0; j < Configuration.size; j++) {
                game.getTiles()[i][j] = new Tile(Type.FREE, i * Configuration.tileSize, j * Configuration.tileSize);
            }
        }
    }

    private static void initNumbers(Game game) {
        for (int i = 0; i < Configuration.size; i++) {
            for (int j = 0; j < Configuration.size; j++) {
                int count = 0;
                if (game.getTiles()[i][j].getType() != Type.BOMB) {
                    if (Control.isInBounds(i - 1, game) && Control.isInBounds(j - 1, game) && game.getTiles()[i - 1][j - 1] != null && game.getTiles()[i - 1][j - 1].getType() == Type.BOMB) {
                        count++;
                    }
                    if (Control.isInBounds(i - 1, game) && Control.isInBounds(j, game) && game.getTiles()[i - 1][j] != null && game.getTiles()[i - 1][j].getType() == Type.BOMB) {
                        count++;
                    }
                    if (Control.isInBounds(i, game) && Control.isInBounds(j - 1,game) && game.getTiles()[i][j - 1] != null && game.getTiles()[i][j - 1].getType() == Type.BOMB) {
                        count++;
                    }
                    if (Control.isInBounds(i + 1, game) && Control.isInBounds(j + 1,game) && game.getTiles()[i + 1][j + 1] != null && game.getTiles()[i + 1][j + 1].getType() == Type.BOMB) {
                        count++;
                    }
                    if (Control.isInBounds(i + 1, game) && Control.isInBounds(j,game) && game.getTiles()[i + 1][j] != null && game.getTiles()[i + 1][j].getType() == Type.BOMB) {
                        count++;
                    }
                    if (Control.isInBounds(i,game) && Control.isInBounds(j + 1,game) && game.getTiles()[i][j + 1] != null && game.getTiles()[i][j + 1].getType() == Type.BOMB) {
                        count++;
                    }
                    if (Control.isInBounds(i + 1,game) && Control.isInBounds(j - 1,game) && game.getTiles()[i + 1][j - 1] != null && game.getTiles()[i + 1][j - 1].getType() == Type.BOMB) {
                        count++;
                    }
                    if (Control.isInBounds(i - 1,game) && Control.isInBounds(j + 1,game) && game.getTiles()[i - 1][j + 1] != null && game.getTiles()[i - 1][j + 1].getType() == Type.BOMB) {
                        count++;
                    }
                }
                if (count != 0) {
                    game.getTiles()[i][j] = new Tile(Type.NUMBER, count, game.getTiles()[i][j].getCoordX(), game.getTiles()[i][j].getCoordY());
                }
            }
        }
    }

    private static void placeBombs(Game game) {
        for (int i = 0; i < Configuration.countBombs; i++) {
            double x = Math.floor((Math.random() * Configuration.size * Configuration.tileSize) / Configuration.tileSize) * Configuration.tileSize;
            double y = Math.floor((Math.random() * Configuration.size * Configuration.tileSize) / Configuration.tileSize) * Configuration.tileSize;
            if (Control.isTileSet(x, y, Type.BOMB, game)) {
                --i;
            } else {
                game.getTiles()[(int) x / Configuration.tileSize][(int) y / Configuration.tileSize] = new Tile(Type.BOMB, x, y);
            }
        }
    }
}
