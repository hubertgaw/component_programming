package pl.comp.model.exceptions;

public class WrongElementSizeException extends IllegalArgumentException {
    public WrongElementSizeException() {
    }

    public WrongElementSizeException(String message) {
        super(message);
    }

    public WrongElementSizeException(String message, Throwable cause) {
        super(message, cause);
    }
}
