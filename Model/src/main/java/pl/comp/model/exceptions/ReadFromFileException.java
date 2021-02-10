package pl.comp.model.exceptions;

public class ReadFromFileException extends ModelException {
    public ReadFromFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReadFromFileException() {
        super();
    }

    public ReadFromFileException(String message) {
        super(message);
    }
}
