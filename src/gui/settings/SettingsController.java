package gui.settings;

import core.Graphics;
import core.control.Control;
import core.settings.Configuration;
import gui.main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {

    @FXML
    private Slider boardSizeSlider;

    @FXML
    private Slider bombsCountSlider;

    @FXML
    private Slider tileSizeSlider;

    @FXML
    private Label boardSizeLabel;

    @FXML
    private Label bombsCountLabel;

    @FXML
    private Label tileSizeLabel;

    @FXML
    private ImageView bomb1;

    @FXML
    private ImageView bomb2;

    @FXML
    private ImageView bomb3;

    private String pathOfPickedImage;

    public void initialize(URL url, ResourceBundle rb) {
        initConfigurationValues();
        loadImages();
        chooseImage(1);
        boardSizeSlider.valueProperty().addListener(t -> boardSizeLabel.setText("Board size: " + Integer.toString((int)boardSizeSlider.getValue())));
        bombsCountSlider.valueProperty().addListener(t-> bombsCountLabel.setText("Bombs: " + Integer.toString((int)bombsCountSlider.getValue())));
        tileSizeSlider.valueProperty().addListener(t->tileSizeLabel.setText("Tile size: " + Integer.toString((int)tileSizeSlider.getValue())));
        bomb1.setOnMouseClicked(t -> {pathOfPickedImage = "/img/bombs/bomb1.png";chooseImage(1);});
        bomb2.setOnMouseClicked(t -> {pathOfPickedImage = "/img/bombs/bomb2.png";chooseImage(2);});
        bomb3.setOnMouseClicked(t -> {pathOfPickedImage = "/img/bombs/bomb3.png";chooseImage(3);});
    }

    @FXML
    public void applySettings(ActionEvent event) throws IOException {
        Configuration.loadUserConfiguration((int)boardSizeSlider.getValue(), (int)bombsCountSlider.getValue(),(int)tileSizeSlider.getValue(), pathOfPickedImage);
        if(!(Control.isCountOfBombsOverlimit() || Control.isBoardSizeBiggerThanScreenSize())){
            ((Stage)SettingsDialog.board.getScene().getWindow()).close();
            ((Stage)boardSizeSlider.getScene().getWindow()).close();
            Parent  root = FXMLLoader.load(getClass().getResource("/gui/main/minesweeper.fxml"));
            Scene scene = new Scene(root);
            Main.stage.setTitle("Mine Sweeper");
            Main.stage.setScene(scene);
            Main.stage.show();
        }
    }

    @FXML
    public void resetToDefault(ActionEvent event) throws IOException {
        Configuration.loadDefaultConfiguration();
        ((Stage)SettingsDialog.board.getScene().getWindow()).close();
        ((Stage)boardSizeSlider.getScene().getWindow()).close();
        Parent  root = FXMLLoader.load(getClass().getResource("/gui/main/minesweeper.fxml"));
        Scene scene = new Scene(root);
        Main.stage.setTitle("Mine Sweeper");
        Main.stage.setScene(scene);
        Main.stage.show();
    }

    @FXML
    public void close(ActionEvent event){
        ((Stage)boardSizeSlider.getScene().getWindow()).close();
    }

    private void initConfigurationValues(){
        boardSizeLabel.setText("Board size: " + Configuration.size);
        bombsCountLabel.setText("Bombs: " + Configuration.countBombs);
        tileSizeLabel.setText("Tile size: " + Configuration.tileSize);
        boardSizeSlider.setValue(Configuration.size);
        bombsCountSlider.setValue(Configuration.countBombs);
        tileSizeSlider.setValue(Configuration.tileSize);
        chooseImage(1);
        pathOfPickedImage = Configuration.picture;
    }

    private void loadImages() {
        bomb1.setImage(new Image(Graphics.class.getResource("/img/bombs/bomb1.png").toExternalForm()));
        bomb1.setFitHeight(70);
        bomb1.setFitWidth(70);
        bomb2.setImage(new Image(Graphics.class.getResource("/img/bombs/bomb2.png").toExternalForm()));
        bomb2.setFitHeight(70);
        bomb2.setFitWidth(70);
        bomb3.setImage(new Image(Graphics.class.getResource("/img/bombs/bomb3.png").toExternalForm()));
        bomb3.setFitHeight(70);
        bomb3.setFitWidth(70);
    }

    private void chooseImage(int number){
        if (number == 1) {
            bomb1.setFitWidth(90);
            bomb1.setFitHeight(90);
            bomb2.setFitHeight(70);
            bomb2.setFitWidth(70);
            bomb3.setFitHeight(70);
            bomb3.setFitWidth(70);
        }else if (number == 2){
            bomb1.setFitWidth(70);
            bomb1.setFitHeight(70);
            bomb2.setFitHeight(90);
            bomb2.setFitWidth(90);
            bomb3.setFitHeight(70);
            bomb3.setFitWidth(70);
        } else if (number == 3) {
            bomb1.setFitWidth(70);
            bomb1.setFitHeight(70);
            bomb2.setFitHeight(70);
            bomb2.setFitWidth(70);
            bomb3.setFitHeight(90);
            bomb3.setFitWidth(90);
        }
    }
}

