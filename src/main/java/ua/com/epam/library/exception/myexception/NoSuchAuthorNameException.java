package ua.com.epam.library.exception.myexception;

public class NoSuchAuthorNameException extends RuntimeException {

    public NoSuchAuthorNameException(String message) {
        super(message);
    }
}
