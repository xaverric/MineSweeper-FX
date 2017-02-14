package gui.main;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import core.*;
import core.components.Type;
import core.control.Control;
import core.settings.Configuration;
import core.settings.Initialization;
import gui.settings.SettingsDialog;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class GameController implements Initializable {

    @FXML
    private Pane board;

    @FXML
    private Pane scoreBoard;

    @FXML
    private AnchorPane window;

    @FXML
    private Label labelClick;

    public void initialize(URL url, ResourceBundle rb) {
        Initialization.initGame(Configuration.game);
        if (!Control.isCountOfBombsOverlimit() && !Control.isBoardSizeBiggerThanScreenSize()) {
            Graphics.drawBoard(board, window, labelClick, scoreBoard);
        } else {
            Platform.exit();
        }
        Main.stage.setResizable(false);
    }

    @FXML
    public void uncoverTile(MouseEvent event) {
        if (Configuration.game.isGameOn()){
            Configuration.game.increaseClickCount();
            labelClick.setText(Integer.toString(Configuration.game.getClickCount()));
            double x = Math.floor(event.getX() / Configuration.tileSize) * Configuration.tileSize;
            double y = Math.floor(event.getY() / Configuration.tileSize) * Configuration.tileSize;
            if (Configuration.game.getTypeOfTile(x, y) == Type.NUMBER) {
                Graphics.drawNumber(Configuration.game.getTiles()[(int) x / Configuration.tileSize][(int) y / Configuration.tileSize], board);
                if (Control.isAllUncovered()) {
                    Control.endGameOnSuccess(board, labelClick);
                }
            } else if (Configuration.game.getTypeOfTile(x, y) == Type.BOMB) {
                Graphics.drawBombs(board);
                Control.endGameOnLoss(board, labelClick);
            } else if (Configuration.game.getTypeOfTile(x, y) == Type.FREE) {
                Graphics.uncoverFreeSpace(Configuration.game.getTiles()[(int) x / Configuration.tileSize][(int) y / Configuration.tileSize], board);

                if (Control.isAllUncovered()) {
                    Control.endGameOnSuccess(board, labelClick);
                }
            }
        }
        Graphics.drawGrid(board);
    }

    @FXML
    public void loadNewGame(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warning");
        alert.setHeaderText("Are you sure?");
        alert.setContentText("Current game progress will be lost.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Initialization.initGame(Configuration.game);
            board.getChildren().clear();
            Configuration.game.resetClickCount();
            labelClick.setText("0");
            Graphics.drawGrid(board);
        }
    }

    @FXML
    public void exit(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warning");
        alert.setHeaderText("Are you sure?");
        alert.setContentText("Current game progress will be lost.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Platform.exit();
        }
    }

    @FXML
    public void loadEasyConfiguration(ActionEvent event) throws IOException {
        Configuration.loadEasyConfiguration();
        Main.stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/main/minesweeper.fxml"));
        Scene scene = new Scene(root);
        Main.stage.setTitle("Mine Sweeper");
        Main.stage.setScene(scene);
        Main.stage.show();
    }

    @FXML
    public void loadHardConfiguration(ActionEvent event) throws IOException {
        Configuration.loadDifficultConfiguration();
        Main.stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/main/minesweeper.fxml"));
        Scene scene = new Scene(root);
        Main.stage.setTitle("Mine Sweeper");
        Main.stage.setScene(scene);
        Main.stage.show();
    }

    @FXML
    public void loadExpertConfiguration(ActionEvent event) throws IOException {
        Configuration.loadExpertConfiguration();
        Main.stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/main/minesweeper.fxml"));
        Scene scene = new Scene(root);
        Main.stage.setTitle("Mine Sweeper");
        Main.stage.setScene(scene);
        Main.stage.show();
    }

    @FXML
    public void openSettings(ActionEvent event) throws IOException {
        SettingsDialog settingsDialog = new SettingsDialog(board,labelClick);
        settingsDialog.showAndWait();
    }

    @FXML
    public void about(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        ImageView img = new ImageView(Graphics.class.getResource("/img/bombs/bomb1.png").toExternalForm());
        alert.setGraphic(img);
        alert.setTitle("Mine Sweeper FX");
        alert.setHeaderText("Mine Sweeper FX");
        alert.setContentText("Version 1.0 \nAuthor: Daniel Jílek    ");
        alert.showAndWait();
    }
}

