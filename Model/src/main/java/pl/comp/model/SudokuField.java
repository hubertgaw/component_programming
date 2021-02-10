package pl.comp.model;

import java.io.Serializable;
import java.util.ResourceBundle;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.comp.model.exceptions.WrongFieldValueException;


@Entity(name = "SudokuField")
//@Table(name = "sudoku_field")
public class SudokuField implements Serializable, Cloneable, Comparable<SudokuField> {
    //private int value;
    @Id
    @GeneratedValue
    private Long id;
    private transient IntegerProperty value = new SimpleIntegerProperty();
    private transient ResourceBundle bundle = ResourceBundle.getBundle("Language");
    private static final Logger logger =
            LogManager.getLogger(SudokuField.class.getName());

    @ManyToOne
    @JoinColumn(name = "sudokuBoard_id")
    private SudokuBoard sudokuBoard;


    public SudokuField() {
    }

    public IntegerProperty getValueProperty() {
        return value;
    }

    public int getValue() {
        return value.get();
    }

    public void setValue(int value) {
        this.value.set(value);
    }

    public IntegerProperty valueProperty() {
        return value;
    }

    public SudokuField(int value) {
        if (value < 0 || value > 9) {
            logger.error(bundle.getObject("wrongValueMessage").toString());
            throw new WrongFieldValueException(bundle.getObject("wrongValueMessage").toString());
        }
        setValue(value);
    }

    public int getFieldValue() {
        return getValue();
    }

    public void setFieldValue(int value) {
        if (value < 0 || value > 9) {
            logger.error(bundle.getObject("wrongValueMessage").toString());
            throw new WrongFieldValueException(bundle.getObject("wrongValueMessage").toString());
            //0 poniewaz algorytm backtracking w miejsce w ktore nie
            //moze akurat wpisac liczby wpisuje 0, aby potem to 0 podmienic na odpowiednia liczbe
        }
        //this.value = value;
        setValue(value);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append(bundle.getObject("value").toString(),value).toString();
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
        SudokuField rhs = (SudokuField) obj;
        return new EqualsBuilder()
                .append(getValue(), rhs.getValue())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getValue())
                .toHashCode();
    }

    public SudokuField clone() throws CloneNotSupportedException {
        SudokuField clone = new SudokuField();
        clone.setFieldValue(getValue());
        return clone;
    }

    @Override
    public int compareTo(SudokuField sudokuField) {
        if (getValue() > sudokuField.getValue()) {
            return 1;
        } else if (getValue() < sudokuField.getValue()) {
            return -1;
        } else {
            return 0;
        }

        //return this.value.compareTo(sudokuField.value);

    }
}
