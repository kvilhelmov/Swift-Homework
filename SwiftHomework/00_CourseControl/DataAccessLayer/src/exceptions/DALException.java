package exceptions;

public class DALException extends Exception {

    public DALException(String message) {
        super(message);
    }

    public DALException(String message, Throwable ex) {
        super(message, ex);
    }
}
