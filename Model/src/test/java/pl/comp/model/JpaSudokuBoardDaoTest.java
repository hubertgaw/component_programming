package pl.comp.model;

import org.junit.jupiter.api.Test;
import pl.comp.model.exceptions.NoSuchFileException;
import pl.comp.model.exceptions.ReadFromFileException;
import pl.comp.model.exceptions.WriteToDatabaseException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.File;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


public class JpaSudokuBoardDaoTest {
    @Test
    void convertTwoDimListIntoSingleDimListTest() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard();
        solver.solve(board);
        board.convertTwoDimListIntoSingleDimList();
        //System.out.println("Two dim: " + board.toString());
        //System.out.println("One dim: " + board.getBoardDB().toString());
        assertEquals(board.getBoardDB().get(1).getFieldValue(), board.getNumber(0,1));
    }

    @Test
    void JPADaoTest() {
        //usuwamy plik, aby zaczynać test od "pustej bazy"
        new File("./test.mv.db").delete();

        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        JpaSudokuBoardDao jpaSudokuBoardDao = (JpaSudokuBoardDao) factory.getDatabaseDao();

        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard();
        SudokuBoard sudokuBoard1 = new SudokuBoard();
        solver.solve(sudokuBoard);
        solver.solve(sudokuBoard1);

        try {
            jpaSudokuBoardDao.write(sudokuBoard);
        } catch (WriteToDatabaseException e) {
            e.printStackTrace();
        }
        try {
            jpaSudokuBoardDao.write(sudokuBoard1);
        } catch (WriteToDatabaseException e) {
            e.printStackTrace();
        }

        //findAllTest:
        List<SudokuBoard> boards = jpaSudokuBoardDao.findAll();
        boards.forEach(System.out::println);

        assertEquals(boards.get(0),sudokuBoard);
        assertEquals(boards.get(1),sudokuBoard1);

        //readFirstElementTest:
        SudokuBoard boardFirstFromDB = new SudokuBoard();
        boardFirstFromDB = jpaSudokuBoardDao.read();
        assertEquals(sudokuBoard, boardFirstFromDB);

        //readChosenElementTest:
        SudokuBoard boardSecondFromDB = new SudokuBoard();
        boardSecondFromDB = jpaSudokuBoardDao.read(2L);
        assertEquals(sudokuBoard1, boardSecondFromDB);
    }
}
