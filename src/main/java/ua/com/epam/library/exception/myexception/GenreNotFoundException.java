package ua.com.epam.library.exception.myexception;

public class GenreNotFoundException extends RuntimeException {

    public GenreNotFoundException(String message) {
        super(message);
    }
}
