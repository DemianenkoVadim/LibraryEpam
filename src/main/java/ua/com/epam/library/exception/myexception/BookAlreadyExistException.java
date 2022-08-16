package ua.com.epam.library.exception.myexception;

public class BookAlreadyExistException extends RuntimeException {

    public BookAlreadyExistException(String message) {
        super(message);
    }
}
