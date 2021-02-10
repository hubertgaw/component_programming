package pl.comp.model;

//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

class SudokuElementTest {

    private static List<SudokuField> correct;
    private static List<SudokuField> incorrect;
    private static final Logger logger =
            LogManager.getLogger(SudokuElementTest.class.getName());
    private ResourceBundle bundle = ResourceBundle.getBundle("Language");


    @BeforeAll
    static public void setUp(){
        correct = Arrays.asList(new SudokuField(1),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
                new SudokuField(8),
                new SudokuField(9));

        incorrect = Arrays.asList( new SudokuField(1),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(5),
                new SudokuField(7),
                new SudokuField(8),
                new SudokuField(9));

    }

    @Test
    public void verifyRowTest() {
        SudokuRow rowC = new SudokuRow(correct);
        assertTrue(rowC.verify());

        SudokuRow rowI = new SudokuRow(incorrect);
        assertFalse(rowI.verify());
    }

    @Test
    public void verifyColumnTest() {
        SudokuColumn columnC = new SudokuColumn(correct);
        SudokuColumn columnI = new SudokuColumn(incorrect);
        assertTrue(columnC.verify());
        assertFalse(columnI.verify());
    }

    @Test
    public void verifyBoxTest() {
        SudokuBox boxC = new SudokuBox(correct);
        SudokuBox boxI = new SudokuBox(incorrect);
        assertTrue(boxC.verify());
        assertFalse(boxI.verify());
    }

    @Test
    public void wrongSizeTest() {
        Exception exception = assertThrows(pl.comp.model.exceptions.WrongElementSizeException.class,() -> new SudokuRow(Arrays.asList(new SudokuField(1),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(5),
                new SudokuField(7),
                new SudokuField(8),
                new SudokuField(9),
                new SudokuField(9))));
        //  Exception exception = assertThrows(IllegalArgumentException.class, () -> row.verify());
        assertEquals(bundle.getObject("wrongSizeMessage").toString(), exception.getMessage());
    }

    @Test
    public void toStringTest() {
        assertNotNull(correct.toString());
        //System.out.println(correct.toString());
        logger.info(correct.toString());
    }

    @Test
    public void equalsTest() {
        SudokuColumn col1 = new SudokuColumn(correct);
        SudokuColumn col2 = new SudokuColumn(correct);
        assertTrue(col1.equals(col2) && col2.equals(col1));
    }

    @Test
    public void hashCodeTest() {
        SudokuColumn col1 = new SudokuColumn(incorrect);
        SudokuColumn col2 = new SudokuColumn(incorrect);

        assertEquals(col1.hashCode(),col2.hashCode());
    }
}



