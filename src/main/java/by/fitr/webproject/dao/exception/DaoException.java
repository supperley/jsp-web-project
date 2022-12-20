package by.fitr.webproject.dao.exception;

public class DaoException extends Exception {

    public DaoException() {
        super();
    }

    public DaoException(Throwable cause) {
        super(cause);
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
