package pl.comp.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ComparableTest {
    @Test
    public void compareTest() {
        SudokuField field1 = new SudokuField(2);
        SudokuField field2 = new SudokuField(3);
        SudokuField field3 = new SudokuField(2);
        SudokuField field4 = new SudokuField(6);
        assertEquals(1,field2.compareTo(field1));
        assertEquals(0,field3.compareTo(field1));
        assertEquals(-1,field1.compareTo(field2));
        SudokuField[] tablica = new SudokuField[]{field1,field2,field3,field4};
        Arrays.sort(tablica);
        assertEquals(tablica[0],field1);
        assertEquals(tablica[1],field3);
        assertEquals(tablica[2],field2);
        assertEquals(tablica[3],field4);
    }
}
