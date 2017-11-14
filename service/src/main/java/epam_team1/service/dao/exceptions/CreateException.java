package epam_team1.service.dao.exceptions;

public class CreateException extends RuntimeException {
    public CreateException(String message) {
        super(message);
    }

    public CreateException(Throwable cause) {
        super(cause);
    }
}
