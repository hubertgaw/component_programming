package pl.comp;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private ResourceBundle bundle = ResourceBundle.getBundle("Language");

    @Override
    public void start(Stage stage) throws IOException {
       // Locale.setDefault(new Locale("pl"));
        StageAndSceneSetup.setup(stage, "/mainWindow",bundle.getString("title"),false, bundle);

    }

    public static void main(String[] args) {
        launch();
    }

}