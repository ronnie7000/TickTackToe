package src.exception;

public class InvalidGameParamsException extends RuntimeException{

        public InvalidGameParamsException() {

        }

        public InvalidGameParamsException(String message) {
            super(message);
        }

    
}
