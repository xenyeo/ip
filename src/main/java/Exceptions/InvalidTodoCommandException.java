package Exceptions;

public class InvalidTodoCommandException extends KajiException {
    public InvalidTodoCommandException(String msg) {
        super(msg);
    }
}
