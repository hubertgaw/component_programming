package pl.comp.model;

import java.util.ArrayList;
import java.util.List;

public class SudokuColumn extends SudokuElement {
    public SudokuColumn(List<SudokuField> fields) {
        super(fields);
    }

    /*public SudokuColumn() {
        for (int i = 0; i < 9; i++) {
            setFields(new SudokuField(0),i);
        }

    }
*/

    public SudokuColumn clone() throws CloneNotSupportedException {
        List<SudokuField> fields = new ArrayList<>(getFields());
        //SudokuColumn clone = new SudokuColumn();
        //for (int i = 0; i < 9; i++) {
        //    clone.setFields(fields.get(i).clone(),i);
        //}
        return new SudokuColumn(fields);
    }
}
