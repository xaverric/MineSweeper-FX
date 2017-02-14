package core;

import core.components.Tile;
import core.components.Type;
import core.settings.Configuration;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

/**
 * Created by user on 12.2.2017.
 */
public class Graphics {

    public static void drawBoard(Pane board, AnchorPane window, Label clickCount, Pane scoreBoard){
        clickCount.setAlignment(Pos.CENTER);
        clickCount.setStyle("-fx-text-fill: white;");
        scoreBoard.setStyle("-fx-background-color: darkgray;");
        board.setStyle("-fx-background-image: url(/img/background/background.jpg);-fx-background-size: cover;");
        window.setStyle("-fx-background-color: darkslategray");
        window.setMaxHeight(Configuration.size * Configuration.tileSize);
        window.setMaxWidth(Configuration.size * Configuration.tileSize);
        board.setPrefHeight((Configuration.size * Configuration.tileSize)-11);
        board.setPrefWidth((Configuration.size * Configuration.tileSize)-11);
        Graphics.drawGrid(board);
    }

    public static void drawBombs(Pane board) {
        for (int i = 0; i < Configuration.size; i++) {
            for (int j = 0; j < Configuration.size; j++) {
                if (Configuration.game.getTiles()[i][j].getType() == Type.BOMB) {
                    drawTile(Configuration.game.getTiles()[i][j], board);
                    Label l = new Label();
                    l.setTranslateX(Configuration.game.getTiles()[i][j].getCoordX());
                    l.setTranslateY(Configuration.game.getTiles()[i][j].getCoordY());
                    l.setMinWidth(Configuration.tileSize);
                    l.setMinHeight(Configuration.tileSize);
                    l.setAlignment(Pos.CENTER);
                    ImageView img = new ImageView(Graphics.class.getResource(Configuration.picture).toExternalForm());
                    img.setFitHeight(Configuration.tileSize - Configuration.offset);
                    img.setFitWidth(Configuration.tileSize - Configuration.offset);
                    l.setGraphic(img);
                    board.getChildren().add(l);
                }
            }
        }
        Graphics.drawGrid(board);
    }

    public static void drawNumber(Tile tile, Pane board) {
        drawTile(tile, board);
        Label l = new Label(Integer.toString(tile.getValue()));
        if (tile.getValue() < 2) {
            l.setStyle("-fx-text-fill: green;-fx-font-weight: bold;-fx-text-alignment: center;");
        } else if (tile.getValue() < 3) {
            l.setStyle("-fx-text-fill: orange;-fx-font-weight: bold;-fx-text-alignment: center;");
        } else {
            l.setStyle("-fx-text-fill: red;-fx-font-weight: bold;-fx-text-alignment: center;");
        }
        l.setTranslateX(tile.getCoordX());
        l.setTranslateY(tile.getCoordY());
        l.setMinWidth(Configuration.tileSize);
        l.setMinHeight(Configuration.tileSize);
        l.setAlignment(Pos.CENTER);
        board.getChildren().add(l);
        Graphics.drawGrid(board);
    }

    public static void uncoverFreeSpace(Tile tile, Pane board) {
        drawTile(tile, board);
        for (Tile t : Configuration.game.getFreeTilesOrOnesAround(tile)) {
            if (t.getType() == Type.FREE && t.getDrawn() == false) {
                uncoverFreeSpace(t, board);
            } else if(t.getType() == Type.NUMBER){
                drawNumber(t, board);
            }
        }
        Graphics.drawGrid(board);
    }

    private static void drawTile(Tile tile, Pane board) {
        if (tile.getDrawn() == false){
            tile.setDrawn(true);
            Rectangle r = new Rectangle(Configuration.tileSize, Configuration.tileSize);
            r.setTranslateX(tile.getCoordX());
            r.setTranslateY(tile.getCoordY());
            r.setFill(Color.LIGHTGRAY);
            board.getChildren().add(r);
        }
    }

    public static void drawGrid(Pane board) {
        for (int i = 0; i <= Configuration.size * Configuration.tileSize; i += Configuration.tileSize) {
            Line lineX = new Line(0, i, Configuration.size * Configuration.tileSize, i);
            Line lineY = new Line(i, 0, i, Configuration.size * Configuration.tileSize);
            lineX.setStroke(Color.BLACK);
            lineY.setStroke(Color.BLACK);
            lineX.toFront();
            lineY.toFront();
            board.getChildren().add(lineX);
            board.getChildren().add(lineY);
        }
    }
}
