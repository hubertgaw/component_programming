package pl.comp.model;

//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

class SudokuFieldTest {
    private static final Logger logger =
            LogManager.getLogger(SudokuFieldTest.class.getName());
    private ResourceBundle bundle = ResourceBundle.getBundle("Language");

    @Test
    public void WrongArgumentInConstructorTest() {
        Exception exception = assertThrows(pl.comp.model.exceptions.WrongFieldValueException.class, () -> new SudokuField(16));
        assertEquals(bundle.getObject("wrongValueMessage").toString(), exception.getMessage());
    }

    @Test
    public void getFieldValueTest() {
        SudokuField field = new SudokuField(5);//jednoczesnie sprawdzamy czy konstruktor dziala
        assertEquals(5, field.getFieldValue());
    }

    @Test()
    public void setFieldValueTest() {
        SudokuField field = new SudokuField();
        field.setFieldValue(7);
        assertEquals(7,field.getFieldValue());
        Exception exception = assertThrows(pl.comp.model.exceptions.WrongFieldValueException.class, () -> field.setFieldValue(10));
        assertEquals(bundle.getObject("wrongValueMessage").toString(), exception.getMessage());

    }

    @Test
    public void toStringTest() {
        SudokuField field1 = new SudokuField();
        assertNotNull(field1.toString());
        //System.out.println(field1.toString());
        logger.info(field1.toString());
    }

    @Test
    public void equalsTest() {
        SudokuField field1 = new SudokuField();
        SudokuField field2 = new SudokuField();
        field1.setFieldValue(4);
        field2.setFieldValue(4);
        assertTrue(field1.equals(field2) && field2.equals(field1));
    }

    @Test
    public void hashCodeTest() {
        SudokuField field1 = new SudokuField();
        SudokuField field2 = new SudokuField();
        field2.setFieldValue(2);
        field1.setFieldValue(2);
        assertEquals(field1.hashCode(),field2.hashCode());
    }
}