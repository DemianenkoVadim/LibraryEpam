package ua.com.epam.library.exception.myexception;

public class RoleNoFoundException extends RuntimeException {

    public RoleNoFoundException(String message) {
        super(message);
    }
}
