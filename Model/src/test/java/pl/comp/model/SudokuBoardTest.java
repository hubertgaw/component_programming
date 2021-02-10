package pl.comp.model;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) //Czy aby we wszystkich testach operować na nowo zainicjalizowanym obiekcie SudokuBoard example
//uzycie TestInstance.Lifecycle.PER_CLASS i następnie @BeforeEach jest dobrym podejściem?
//Czy lepiej byłoby po prostu zrobić BeforeAll i użyć dwóch statycznych obiektów (gdy Lifecycle jest
//ustawiony na PER_METHOD obiekty musza być statyczne).
public class SudokuBoardTest {
    private SudokuBoard example;
    private static final Logger logger =
            LogManager.getLogger(SudokuBoardTest.class.getName());

    //private SudokuBoard exampleToChange;
    //Tworze druga SudokuBoard, aby nie operowac na statycznej SudokuBoard example, dla ktorej sprawdzam
    //czy poprawnie została zainicjalizowana 0 w tescie getNumberTest
    //w dalszej czesci testow bede podmienial zera na inne liczby (do tego uzyje exampleToChange).


    @BeforeEach
    public void setUp() {
        example = new SudokuBoard();
    }

    @Test
    public void getNumberTest() {
        for(int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                assertEquals(0,example.getNumber(i,j));
            }
        }
    }

    @Test
    public void setNumberTest() {
        example.setNumber(3,4,8);
        assertEquals(8,example.getNumber(3,4));
    }

    @Test
    public void checkBoardTest() {
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        solver.solve(example);
        assertTrue(example.checkBoard());
    }

    @Test
    void getRowTest() {
        for(int i = 0; i < 9; i++) {
            assertNotNull(example.getRow(i));
        }
        //Ustawiamy na okreslonych pozycjach rzędu określone numery i sprawdzamy czy getRow zwróci te numery:
        example.setNumber(2,3,5);
        example.setNumber(2,4,6);
        example.setNumber(2,5,7);
        List<SudokuField> check = example.getRow(2).getFields();
        assertEquals(5,check.get(3).getFieldValue());
        assertEquals(6,check.get(4).getFieldValue());
        assertEquals(7,check.get(5).getFieldValue());

    }


    @Test
    void getColumnTest() {
        for(int i = 0; i < 9; i++) {
            assertNotNull(example.getColumn(i));
        }
        //Ustawiamy na okreslonych pozycjach kolumny określone numery i sprawdzamy czy getRow zwróci te numery:
        example.setNumber(1,3,5);
        example.setNumber(2,3,6);
        example.setNumber(3,3,7);
        List<SudokuField> check = example.getColumn(3).getFields();
        assertEquals(5,check.get(1).getFieldValue());
        assertEquals(6,check.get(2).getFieldValue());
        assertEquals(7,check.get(3).getFieldValue());
    }

    @Test
    void getBoxTest() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertNotNull(example.getBox(i,j));
            }
        }
        //Ustawiamy na okreslonych pozycjach boxu określone numery i sprawdzamy czy getRow zwróci te numery:
        example.setNumber(3,6,5);
        example.setNumber(4,7,6);
        example.setNumber(5,8,7);
        List<SudokuField> check = example.getBox(1,2).getFields();
        assertEquals(5,check.get(0).getFieldValue());
        assertEquals(6,check.get(4).getFieldValue());
        assertEquals(7,check.get(8).getFieldValue());
    }

    @Test
    public void toStringTest() {
        assertNotNull(example.toString());
        //BasicConfigurator.configure();
        logger.info(example.toString());
    }

    @Test
    public void equalsTest() {
        SudokuBoard example2 = new SudokuBoard();
        example.setNumber(1,1,2);
        example.setNumber(1,2,3);
        example.setNumber(1,3,4);

        example2.setNumber(1,1,2);
        example2.setNumber(1,2,3);
        example2.setNumber(1,3,4);

        assertTrue(example.equals(example2) && example2.equals(example));
    }

    @Test
    public void hashCodeTest() {
        SudokuBoard example2 = new SudokuBoard();
        assertEquals(example.hashCode(),example2.hashCode());
    }
}

