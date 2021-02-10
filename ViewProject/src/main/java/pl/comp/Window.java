package pl.comp;

import javafx.scene.control.Alert;

public class Window {
    public void printMessage(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
        alert.setResizable(true);
    }
}
