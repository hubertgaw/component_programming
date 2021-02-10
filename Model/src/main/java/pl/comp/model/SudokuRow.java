package pl.comp.model;

import java.util.ArrayList;
import java.util.List;

public class SudokuRow extends SudokuElement {
    public SudokuRow(List<SudokuField> fields) {
        super(fields);
    }


    public SudokuRow clone() throws CloneNotSupportedException {
        List<SudokuField> fields = new ArrayList<>(getFields());
        //SudokuColumn clone = new SudokuColumn();
        //for (int i = 0; i < 9; i++) {
        //    clone.setFields(fields.get(i).clone(),i);
        //}
        return new SudokuRow(fields);


    }
}