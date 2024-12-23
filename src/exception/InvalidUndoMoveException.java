package src.exception;

public class InvalidUndoMoveException extends RuntimeException {
    public InvalidUndoMoveException() {

    }

    public InvalidUndoMoveException(String message) {
        super(message);
    }
}
