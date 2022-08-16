package ua.com.epam.library.exception.myexception;

import ua.com.epam.library.exception.dao.DaoException;

public class UserAlreadyExistException extends RuntimeException {

    public UserAlreadyExistException(String message) {
        super(message);
    }

    public UserAlreadyExistException(String message, DaoException exception) {

    }
}
