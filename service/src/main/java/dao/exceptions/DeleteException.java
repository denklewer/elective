package dao.exceptions;

public class DeleteException extends  RuntimeException {
    public DeleteException(String message) {
        super(message);
    }

    public DeleteException(Throwable cause) {
        super(cause);
    }
}
