package pl.comp.model;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

class FileSudokuBoardDaoTest {
    private FileSudokuBoardDao file;
    private ResourceBundle bundle = ResourceBundle.getBundle("Language");


    @Test
    void writeReadTest() {
        file = new FileSudokuBoardDao("board.txt");
        SudokuBoard board = new SudokuBoard();
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        solver.solve(board);
        try {
            assertTrue(file.write(board));
        } catch (pl.comp.model.exceptions.WriteToFileException e) {
            e.printStackTrace();
        }
        SudokuBoard board2 = null;
        try {
            board2 = file.read();
        } catch (pl.comp.model.exceptions.NoSuchFileException e) {
            e.printStackTrace();
        } catch (pl.comp.model.exceptions.ReadFromFileException e) {
            e.printStackTrace();
        }
        assertTrue(board.equals(board2));
    }

    @Test
    void ExceptionTest() {
        file = new FileSudokuBoardDao("nieznany.txt");
        Exception exception = assertThrows(pl.comp.model.exceptions.NoSuchFileException.class, () -> file.read());
        assertEquals(bundle.getObject("fileNotFoundMessage").toString(), exception.getMessage());
    }


}