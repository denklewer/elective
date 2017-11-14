package dao.exceptions;

public class UpdateException extends  RuntimeException {
    public UpdateException(String message) {
        super(message);
    }

    public UpdateException(Throwable cause) {
        super(cause);
    }
}
