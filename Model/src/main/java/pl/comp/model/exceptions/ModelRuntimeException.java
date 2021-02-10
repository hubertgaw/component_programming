package pl.comp.model.exceptions;

public class ModelRuntimeException extends RuntimeException {
    public ModelRuntimeException() {
    }

    public ModelRuntimeException(String message) {
        super(message);
    }

    public ModelRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
