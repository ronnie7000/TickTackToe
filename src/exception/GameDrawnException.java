package src.exception;

public class GameDrawnException extends RuntimeException{
    
    public GameDrawnException() {

    }

    public GameDrawnException(String message) {
        super(message);
    }
}
