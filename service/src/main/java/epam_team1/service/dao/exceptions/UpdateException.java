package epam_team1.service.dao.exceptions;

public class UpdateException extends  RuntimeException {
    public UpdateException(String message) {
        super(message);
    }

    public UpdateException(Throwable cause) {
        super(cause);
    }
}
