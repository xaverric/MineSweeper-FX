package core.control;

import core.Game;
import core.Graphics;
import core.components.Type;
import core.settings.Configuration;
import core.settings.Initialization;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;

import java.util.Optional;

/**
 * Created by user on 12.2.2017.
 */
public class Control {

    public static boolean isCountOfBombsOverlimit() {
        if (Configuration.countBombs > (Configuration.size * Configuration.size) / 2) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Number of bombs: " + Configuration.countBombs);
            alert.setContentText("Number of bombs exceeds size of board. " +
                    "Set board to bigger size or set less bombs. With this size: " +
                    Configuration.size + " * " + Configuration.size + " it is allowed to place maximum of " +
                    (Configuration.size * Configuration.size) / 2 + " bombs to the board");
            alert.showAndWait();
            return true;
        }
        return false;
    }

    public static boolean isBoardSizeBiggerThanScreenSize() {
        if ((Configuration.size * Configuration.tileSize) + 200 > Screen.getPrimary().getVisualBounds().getHeight()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Warning");
            alert.setHeaderText("Reduce size");
            alert.setContentText("Board will not fit to the screen with this configuration");
            alert.showAndWait();
            return true;
        }
        return false;
    }

    public static boolean isTileSet(double x, double y, Type type, Game game) {
        for (int i = 0; i < Configuration.size; i++) {
            for (int j = 0; j < Configuration.size; j++) {
                if (game.getTiles()[i][j] != null && game.getTiles()[i][j].getCoordX() == x && game.getTiles()[i][j].getCoordY() == y && game.getTiles()[i][j].getType() == type) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isInBounds(int index, Game game) {
        return index >= 0 && index < game.getTiles().length;
    }

    public static boolean isAllUncovered() {
        for (int i = 0; i < Configuration.size; i++) {
            for (int j = 0; j < Configuration.size; j++) {
                if (Configuration.game.getTiles()[i][j].getDrawn() == false && Configuration.game.getTiles()[i][j].getType() != Type.BOMB) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void endGameOnSuccess(Pane board, Label clickCount) {
        Configuration.game.setGameOn(false);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Congratulations!");
        alert.setHeaderText("You won!");
        alert.setContentText("Do you wish to start new game?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK && result != null) {
            resetGame(board, clickCount);
        } else {
            Platform.exit();
        }
    }

    public static void endGameOnLoss(Pane board, Label clickCount) {
        Configuration.game.setGameOn(false);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Oops :(");
        alert.setHeaderText("You lost.");
        alert.setContentText("Do you wish to start new game?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result != null && result.get() == ButtonType.OK) {
            resetGame(board, clickCount);
        } else {
            Platform.exit();
        }
    }

    public static void resetGame(Pane board, Label clickCount){
        Initialization.initGame(Configuration.game);
        board.getChildren().clear();
        Configuration.game.resetClickCount();
        clickCount.setText("0");
    }

    public static void initNewWindow(Pane board, AnchorPane window, Label labelClick, Pane scoreBoard){
        Graphics.drawBoard(board, window, labelClick, scoreBoard);
    }
}
