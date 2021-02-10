package pl.comp.model.exceptions;

public class WriteToFileException extends ModelException {
    public WriteToFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public WriteToFileException() {
        super();
    }

    public WriteToFileException(String message) {
        super(message);
    }
}
