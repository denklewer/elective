package epam_team1.service.dao.exceptions;

public class DeleteException extends  RuntimeException {
    public DeleteException(String message) {
        super(message);
    }

    public DeleteException(Throwable cause) {
        super(cause);
    }
}
