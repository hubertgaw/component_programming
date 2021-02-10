package pl.comp.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.IntegerProperty;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


@Entity
@Table(name = "SudokuBoard")
public class SudokuBoard implements Serializable, Cloneable {

    @Id
    @GeneratedValue
    private Long id;
    public static final Integer N = 9;
    private transient ResourceBundle bundle = ResourceBundle.getBundle("Language");
    private transient List<List<SudokuField>> board;

    @OneToMany(mappedBy = "sudokuBoard", cascade = CascadeType.ALL)
    private List<SudokuField> boardDB;

    public List<SudokuField> getBoardDB() {
        return boardDB;
    }

    public SudokuBoard() {
        //Tworzenie listy:
        //I wymiar
        this.board = Arrays.asList(new List[N]);

        //II wymiar:
        for (int i = 0; i < N; i++) {
            this.board.set(i, Arrays.asList(new SudokuField[N]));
        }

        //Wypelnianie listy SudokuFieldami:
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                this.board.get(i).set(j, new SudokuField());
            }
        }


    }

    public Integer getNumber(int x, int y) {
        return board.get(x).get(y).getFieldValue();
    }

    public IntegerProperty getNumberProperty(int x, int y) {
        return board.get(x).get(y).getValueProperty();
    }

    public void setNumber(int x, int y, int value) {
        this.board.get(x).get(y).setFieldValue(value);
    }

    public SudokuRow getRow(int y) {
        List<SudokuField> fields = Arrays.asList(new SudokuField[N]);
        //SudokuField[] fields = new SudokuField[N];
        for (int i = 0; i < N; i++) {
            fields.set(i,board.get(y).get(i));
        }
        return new SudokuRow(fields);
    }

    public SudokuColumn getColumn(int x) {
        List<SudokuField> fields = Arrays.asList(new SudokuField[N]);
        for (int i = 0; i < N; i++) {
            fields.set(i,board.get(i).get(x));
        }
        return new SudokuColumn(fields);
    }

    public SudokuBox getBox(int x, int y) {
        List<SudokuField> fields = Arrays.asList(new SudokuField[N]);
        int index = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                fields.set(index, board.get(x * 3 + i).get(y * 3 + j));
                index++;
            }
        }
        return new SudokuBox(fields);
    }

    public boolean checkBoard() {

        boolean flagElementR;
        boolean flagElementC;
        boolean flagElementB;

        for (int i = 0; i < N; i++) {
            flagElementR = getRow(i).verify();
            flagElementC = getColumn(i).verify();
            if (flagElementR == false || flagElementC == false) {
                return false;
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                flagElementB = getBox(i, j).verify();
                if (flagElementB == false) {
                    return false;
                }
            }
        }

        return true;

    }

    public void convertTwoDimListIntoSingleDimList() {
        this.boardDB = Arrays.asList(new SudokuField[N * N]);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                boardDB.set(i * N + j, this.board.get(i).get(j));
            }
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id",id)
                .append(bundle.getObject("board").toString(),board).toString();

    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        SudokuBoard rhs = (SudokuBoard) obj;
        return new EqualsBuilder()
                .append(board, rhs.board)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(board)
                .toHashCode();
    }

    public SudokuBoard clone() throws CloneNotSupportedException {
        SudokuBoard cloneBoard = new SudokuBoard();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                cloneBoard.setNumber(i,j,getNumber(i,j));
            }
        }
        return cloneBoard;
    }
}
