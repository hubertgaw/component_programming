package pl.comp.model;

import org.junit.jupiter.api.Test;
import pl.comp.model.exceptions.WriteToDatabaseException;
import pl.comp.model.exceptions.WriteToFileException;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardDaoFactoryTest {

    @Test
    void getFileDaoTest() {
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        assertNotNull(factory.getFileDao("board.txt"));
    }

    @Test
    void factoryWithFileSudokuBoardDaoTest() throws IOException {
        SudokuBoard board = new SudokuBoard();
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        solver.solve(board);
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        // assertNotNull(factory.getFileDao("board.txt"));
        Dao<SudokuBoard> dao = factory.getFileDao("board.txt");
        try {
            dao.write(board);
        } catch (WriteToFileException | WriteToDatabaseException e) {
            e.printStackTrace();
        }
        SudokuBoard board2 = null;
        try {
            board2 = dao.read();
        } catch (pl.comp.model.exceptions.NoSuchFileException e) {
            e.printStackTrace();
        } catch (pl.comp.model.exceptions.ReadFromFileException e) {
            e.printStackTrace();
        }
        SudokuBoard board3 = new SudokuBoard();
        assertEquals(board, board2);
        assertNotEquals(board2, board3);
    }
}