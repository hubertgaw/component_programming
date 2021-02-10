package pl.comp.model.exceptions;

public class NoSuchFileException extends ModelException {
    public NoSuchFileException() {
        super();
    }

    public NoSuchFileException(String message) {
        super(message);
    }

    public NoSuchFileException(String message, Throwable cause) {
        super(message);
    }
}
