package pl.comp.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CloneableTest {

    @Test
    public void sudokuFieldTest() throws CloneNotSupportedException {
        SudokuField fieldOrginal = new SudokuField(7);
        SudokuField fieldClone = fieldOrginal.clone();
        assertEquals(fieldOrginal,fieldClone);
        fieldOrginal.setFieldValue(2);
        assertNotEquals(fieldOrginal,fieldClone);
        assertEquals(5,fieldClone.getFieldValue()-fieldOrginal.getFieldValue());
    }

    @Test
    public void sudokuColumnBoxRowTest() throws CloneNotSupportedException {
        //  List<SudokuField> correct;

        List<SudokuField> correct = Arrays.asList(new SudokuField(1),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
                new SudokuField(8),
                new SudokuField(9));

        SudokuColumn columnOrginal = new SudokuColumn(correct);
        SudokuColumn columnClone = columnOrginal.clone();
        assertTrue(columnClone.equals(columnOrginal));//sprawdzamy kolumne przy przypisaniu "recznym"
        SudokuBoard board = new SudokuBoard();
        SudokuSolver solver = new BacktrackingSudokuSolver();
        solver.solve(board);
        SudokuRow rowOrginal = board.getRow(3);
        SudokuRow rowClone = rowOrginal.clone();
        SudokuBox boxOrginal = board.getBox(1,2);
        SudokuBox boxClone = boxOrginal.clone();
        assertTrue(rowOrginal.equals(rowClone));//row i box sprawdzamy po pobraniu z boarda
        assertTrue(boxClone.equals(boxOrginal));

    }

    @Test
    public void sudokuBoardTest() throws CloneNotSupportedException {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard boardOrginal = new SudokuBoard();
        solver.solve(boardOrginal);
        SudokuBoard boardClone = boardOrginal.clone();
        assertTrue(boardOrginal.equals(boardClone));

    }
}
