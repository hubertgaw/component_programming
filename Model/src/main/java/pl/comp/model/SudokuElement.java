package pl.comp.model;

import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.comp.model.exceptions.WrongElementSizeException;

public abstract class SudokuElement {
    private List<SudokuField> fields;
    private static final int N = 9;
    private ResourceBundle bundle = ResourceBundle.getBundle("Language");
    private static final Logger logger =
            LogManager.getLogger(SudokuElement.class.getName());

    /* public SudokuElement() {
    }*/

    public SudokuElement(List<SudokuField> fields) {
        if (fields.size() != 9) {
            logger.error(bundle.getObject("wrongSizeMessage").toString());
            throw new WrongElementSizeException(bundle.getObject("wrongSizeMessage").toString());
        }
        this.fields = fields;
    }


    public boolean verify() {
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (fields.get(i).getFieldValue() == fields.get(j).getFieldValue()) {
                    return false;
                }
            }
        }
        return true;
    }

    public List<SudokuField> getFields() {
        return Collections.unmodifiableList(fields);
    }

    /* public boolean setFields (SudokuField value, int place) {
        fields.get(place).setFieldValue(value.getFieldValue());
        return true;
    }
    */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append(bundle.getObject("fields").toString(),fields).toString();

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
        SudokuElement rhs = (SudokuElement) obj;
        return new EqualsBuilder()
                .append(fields, rhs.fields)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(fields)
                .toHashCode();
    }
}
