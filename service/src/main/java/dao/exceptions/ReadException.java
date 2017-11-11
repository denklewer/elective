package dao.exceptions;

public class ReadException extends  RuntimeException {
    public ReadException(String message) {
        super(message);
    }

    public ReadException(Throwable cause) {
        super(cause);
    }
}
