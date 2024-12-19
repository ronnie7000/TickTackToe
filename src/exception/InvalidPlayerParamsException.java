package src.exception;

public class InvalidPlayerParamsException extends RuntimeException{
    
    public InvalidPlayerParamsException() {}

    public InvalidPlayerParamsException(String message) {
        super(message);
    }

}
