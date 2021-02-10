package pl.comp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class StageAndSceneSetup {

    private static Stage stage;


    private static void setStage(Stage stage) {
        StageAndSceneSetup.stage = stage;
    }

    private static Parent loadFXML(String fxml, ResourceBundle bundle) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StageAndSceneSetup.class.getResource(fxml + ".fxml"),bundle);
        return fxmlLoader.load();
    }

    public static void setup(Stage stage, String fxmlName, String title, boolean resizable, ResourceBundle bundle) throws IOException {
        setStage(stage);
        stage.setScene(new Scene(loadFXML(fxmlName, bundle)));
        stage.setTitle(title);
        stage.sizeToScene();
        stage.setResizable(resizable);
        stage.show();

    }

    public static void setup(String fxmlName, boolean resizable, ResourceBundle bundle) throws IOException {
        stage.setScene(new Scene(loadFXML(fxmlName, bundle)));
        stage.sizeToScene();
        stage.setResizable(resizable);
        stage.show();

    }

    //potrzebny do budowania sceny przy zmianie jezyka (aby tytul sie zmienial)
    public static void setup(String fxmlName, String title, boolean resizable, ResourceBundle bundle) throws IOException {
        stage.setScene(new Scene(loadFXML(fxmlName, bundle)));
        stage.setTitle(title);
        stage.sizeToScene();
        stage.setResizable(resizable);
        stage.show();

    }
}
