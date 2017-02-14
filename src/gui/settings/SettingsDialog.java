package gui.settings;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class SettingsDialog extends Stage {

    public static Stage stage;
    public static Pane board;
    public static Label clickCount;

    public SettingsDialog(Pane board, Label clickCount) throws IOException {
        this.board = board;
        this.clickCount = clickCount;
        setTitle("Settings");
        setScene(createScene());
    }

    private Scene createScene() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("settings.fxml"));
        return new Scene(root);
    }
}
