package pl.comp;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.comp.model.FileSudokuBoardDao;
import pl.comp.model.SudokuBoard;

public class MainWindowController {

    private static Scene scene;
    private static Stage stage;
    private static String comboBoxDifficultyString;
    private static boolean flag;
    private static SudokuBoard boardRead;
    private FileSudokuBoardDao file;
    private String comboBoxLanguageString;
    private SudokuBoard sudokuBoard = new SudokuBoard();
    private ResourceBundle bundle = ResourceBundle.getBundle("Language");
    private Window alert = new Window();
    private Window authorsWindow = new Window();
    private Window noFile = new Window(); //to inform that there is no file
    private Window fileError = new Window(); //to inform about error with a file
    private Authors authors = new Authors();
    private static final Logger logger =
            LogManager.getLogger(MainWindowController.class.getName());

    @FXML
    public Button authorsButton;
    @FXML
    private Button startButton;
    @FXML
    private ComboBox difficultyComboBox;
    @FXML
    private CheckBox readSudokuFromFileCheckBox;
    @FXML
    private ComboBox languageComboBox;


    public static String getComboBoxDifficultyString() {
        return comboBoxDifficultyString;
    }

    @FXML
    void initialize(){
        difficultyComboBox.getItems().addAll(bundle.getString("easy"),bundle.getString("medium"),bundle.getString("hard"));
        languageComboBox.getItems().addAll(bundle.getString("langEng"),bundle.getString("langPol"));
        if (!readSudokuFromFileCheckBox.isSelected()) { //za kazdym powrotem do menu sprawdzamy czy checkBox jest zaznaczony
            flag = false;
        }
        comboBoxDifficultyString = (String) this.difficultyComboBox.getSelectionModel().getSelectedItem(); // za kazdym powrotem do menu pobieramy wartosc
                                                                                                           //wartosc comboBoxa (czyli null co skutkuje poziomem easy jesli nie
                                                                                                           //zmienimy wartosci w nim)
    }

    @FXML
    private void startGame(ActionEvent e) throws IOException {
        StageAndSceneSetup.setup("/boardWindow", true, bundle);
    }


    public void onActionDifficultyComboBox() {
        comboBoxDifficultyString = (String) this.difficultyComboBox.getSelectionModel().getSelectedItem();
    }

    public void onActionReadSudokuFromFileCheckBox(ActionEvent actionEvent) throws IOException {
        if (readSudokuFromFileCheckBox.isSelected()) {
            file = new FileSudokuBoardDao("boardTest.txt");
            try {
                boardRead = file.read();
            } catch (pl.comp.model.exceptions.NoSuchFileException e) {
                logger.error(bundle.getObject("fileNotFoundMessage").toString());
                //e.printStackTrace();
                noFile.printMessage(Alert.AlertType.ERROR,bundle.getObject("errorFileTitle").toString(),bundle.getObject("fileNotFoundMessage").toString());
            } catch (pl.comp.model.exceptions.ReadFromFileException e) {
                logger.error(bundle.getObject("readFileErrorMessage").toString());
                fileError.printMessage(Alert.AlertType.ERROR,bundle.getObject("errorFileTitle").toString(),bundle.getObject("readFileErrorMessage").toString());
                //e.printStackTrace();
            }
            flag = true;
        }
        else {
            flag = false;
        }
    }

    public static SudokuBoard getBoardRead() {
        return boardRead;
    }

    public static boolean getFlag() {
        return flag;
    }

    public String getComboBoxLanguageString() {
        return comboBoxLanguageString;
    }

    public void onActionConfirmLanguageButton(ActionEvent actionEvent) throws IOException {
        try {
            comboBoxLanguageString = (String) this.languageComboBox.getSelectionModel().getSelectedItem();
            if (comboBoxLanguageString.equals(bundle.getString("langPol"))) {
                Locale locale = new Locale("pl");
                Locale.setDefault(locale);
                bundle = ResourceBundle.getBundle("Language");
            } else if (comboBoxLanguageString.equals(bundle.getString("langEng"))) {
                Locale.setDefault(new Locale("en"));
                bundle = ResourceBundle.getBundle("Language");
            }
            StageAndSceneSetup.setup("/mainWindow", bundle.getString("title"), false, bundle);
        } catch (NullPointerException e) {
            alert.printMessage(Alert.AlertType.ERROR, bundle.getString("error"), bundle.getString("languageNotChooseMessage"));
        }
    }

    public void onActionLanguageComboBox(ActionEvent actionEvent) {
    }

    public void onActionAuthorsButton(ActionEvent actionEvent) {
        authorsWindow.printMessage(Alert.AlertType.INFORMATION,bundle.getString("authors"),authors.getObject("1.") + "\n" +
                authors.getObject("2."));
    }
}
