package pl.comp.model.exceptions;

public class ModelException extends Exception {
    public ModelException() {
    }

    public ModelException(String message) {
        super(message);
    }

    public ModelException(String message, Throwable cause) {
        super(message, cause);
    }

}
