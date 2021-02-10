package pl.comp.model.exceptions;

public class WriteToDatabaseException extends ModelException {
    public WriteToDatabaseException() {
    }

    public WriteToDatabaseException(String message) {
        super(message);
    }

    public WriteToDatabaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
