package pl.comp;

import java.io.IOException;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.comp.model.*;
import pl.comp.model.exceptions.WriteToDatabaseException;
import pl.comp.model.exceptions.WriteToFileException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BoardWindowController {



    private SudokuBoard board = new SudokuBoard();
    private SudokuSolver solver = new BacktrackingSudokuSolver();
    Difficulty easy = new Easy();
    Difficulty medium = new Medium();
    Difficulty hard = new Hard();
    @FXML
    private Button backButton;
    @FXML
    private GridPane gridPane;
    @FXML
    private Button ReadFileButton;
    @FXML
    private Button WriteFileButton;
    @FXML
    private Button checkButton;
    @FXML
    public Button WriteDBFileButton;

    private TextField textField = new TextField();
    private FileSudokuBoardDao file;
    private Window result = new Window(); //to inform about result
    private Window wrongInput = new Window();
    private Window fileError = new Window(); //to inform that there is no file
    private Window dbError = new Window(); // to inform about problem with writing to db
    StringConverter<Number> converter = new NumberStringConverter();
    private ResourceBundle bundle = ResourceBundle.getBundle("Language");
    // private MainWindowController controller = new MainWindowController();
    private static final Logger logger =
            LogManager.getLogger(BoardWindowController.class.getName());


    @FXML
    private void initialize() {

        if (MainWindowController.getFlag()) {
            board = MainWindowController.getBoardRead();
        } else {
            solver.solve(board);
        }
        fillGridPaneWithSudokuBoard();
        //StringConverter<Number> converter = new NumberStringConverter();
    }

    @FXML
    private void switchToMainWindow() throws IOException {
        StageAndSceneSetup.setup("/mainWindow",false,bundle);

    }

    @FXML
    private void onActionWriteFileButton() throws IOException {
        file = new FileSudokuBoardDao("boardTest.txt");
        try {
            file.write(board);
        } catch (WriteToFileException e) {
            logger.error(bundle.getObject("writeFileErrorMessage").toString());
            fileError.printMessage(Alert.AlertType.ERROR, bundle.getObject("errorFileTitle").toString(),bundle.getObject("writeFileErrorMessage").toString());
            //e.printStackTrace();
        }

    }

    private void fillGridPaneWithSudokuBoard() {
        if (!MainWindowController.getFlag()) {
            if (MainWindowController.getComboBoxDifficultyString() == null) {
                easy.eraseFields(board); //default
            } else if (MainWindowController.getComboBoxDifficultyString().equals(bundle.getString("easy"))) {
                easy.eraseFields(board);
            } else if (MainWindowController.getComboBoxDifficultyString().equals(bundle.getString("medium"))) {
                medium.eraseFields(board);
            } else if (MainWindowController.getComboBoxDifficultyString().equals(bundle.getString("hard"))) {
                hard.eraseFields(board);
            }
        }
        for (int i = 0; i < SudokuBoard.N; i++) {
            for (int j = 0; j < SudokuBoard.N; j++) {
                TextField textField[][] = new TextField[9][9];
                for (int k = 0; k < 9; k++) {
                    for (int l = 0; l < 9; l++) {
                        textField[k][l] = new TextField();
                    }
                }
                if (board.getNumber(i,j) == 0) {
                    textField[i][j].setText("");
                    Bindings.bindBidirectional(textField[i][j].textProperty(), board.getNumberProperty(i,j),converter);
                } else {
                    textField[i][j].setText(String.valueOf(board.getNumber(i, j)));
                    textField[i][j].setDisable(true);
                }
                textField[i][j].setAlignment(Pos.CENTER);
                textField[i][j].setPrefHeight(48);
                gridPane.add(textField[i][j],j,i);
            }
        }
    }

    public boolean areValuesValid() {
        for (int i = 0; i < SudokuBoard.N; i++) {
            for (int j = 0; j < SudokuBoard.N; j++) {
                if (!(board.getNumber(i,j) >= 1 && board.getNumber(i,j) <= 9)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void onActionCheckButton(ActionEvent actionEvent) {
        if (!areValuesValid()) {
            wrongInput.printMessage(Alert.AlertType.WARNING, bundle.getString("wrongInputTitle"), bundle.getString("wrongInputMessage"));
        } else if (areValuesValid()) {
            if (board.checkBoard()) {
                result.printMessage(Alert.AlertType.INFORMATION, bundle.getString("result"), bundle.getString("resultMessageWon"));
            } else if (!board.checkBoard()) {
                result.printMessage(Alert.AlertType.INFORMATION, bundle.getString("result"), bundle.getString("resultMessageLose"));
            }
        }
    }

}