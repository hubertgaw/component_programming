package pl.comp.model.exceptions;

public class WrongFieldValueException extends IllegalArgumentException {
    public WrongFieldValueException() {
    }

    public WrongFieldValueException(String message) {
        super(message);
    }

    public WrongFieldValueException(String message, Throwable cause) {
        super(message, cause);
    }
}
