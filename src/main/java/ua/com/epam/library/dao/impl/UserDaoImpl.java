package ua.com.epam.library.dao.impl;

import ua.com.epam.library.dao.AbstractDao;
import ua.com.epam.library.dao.DatabaseTableTitle;
import ua.com.epam.library.dao.UserDao;
import ua.com.epam.library.dao.mapper.RowMapperFactory;
import ua.com.epam.library.entity.User;
import ua.com.epam.library.exception.dao.DaoException;
import ua.com.epam.library.util.DataBaseQueriesConstants;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static ua.com.epam.library.entity.Status.ACTIVE;
import static ua.com.epam.library.util.Constant.ROLE_LIBRARIAN;
import static ua.com.epam.library.util.Constant.ROLE_USER;
import static ua.com.epam.library.util.DataBaseQueriesConstants.*;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    public UserDaoImpl() {
        super(RowMapperFactory.getInstance().getUserRowMapper(), DatabaseTableTitle.USER);
    }

    @Override
    public boolean registration(User user) throws DaoException {
        return executeInsertQuery(USER_REGISTRATION, user.getName(), user.getEmail(), user.getPhone(),
                user.getPassword(), ROLE_USER, LocalDateTime.now(), LocalDateTime.now(), ACTIVE.toString());
    }

    @Override
    public boolean updateProfile(User user) throws DaoException {
        return executeInsertQuery(UPDATE_USER, user.getName(), user.getEmail(), user.getPhone(),
                LocalDateTime.now(), user.getId());
    }

    @Override
    public boolean createLibrarian(User user) throws DaoException {
        return executeInsertQuery(CREATE_USER, user.getName(), user.getEmail(), user.getPhone(),
                user.getPassword(), ROLE_LIBRARIAN, LocalDateTime.now(), LocalDateTime.now(), ACTIVE.toString());
    }

    @Override
    public List<User> findAllLibrarians() throws DaoException {
        return executeQuery(FIND_ALL_LIBRARIANS);
    }

    @Override
    public boolean remove(Long id) throws DaoException {
        return executeToUpdateQuery(DELETE_USER, id);
    }

    @Override
    public Optional<User> findById(long userId) throws DaoException {
        return executeSingleResult(DataBaseQueriesConstants.FIND_USER_BY_ID, userId);
    }

    @Override
    public Optional<User> findUserByIdAndPassword(Long id, String password) throws DaoException {
        return executeSingleResult(FIND_USER_BY_PASSWORD, id, password);
    }

    @Override
    public List<User> findAllActiveUsers() throws DaoException {
        return executeQuery(FIND_ALL_ACTIVE_USERS);
    }

    @Override
    public List<User> findAllBlockedUsers() throws DaoException {
        return executeQuery(FIND_ALL_BLOCKED_USERS);
    }

    @Override
    public boolean blockUser(Long id) throws DaoException {
        return executeToUpdateQuery(CHANGE_USER_STATUS_TO_BLOCKED, id);
    }

    @Override
    public boolean unblockUser(Long id) throws DaoException {
        return executeToUpdateQuery(CHANGE_USER_STATUS_TO_UNBLOCK, id);
    }

    @Override
    public Optional<User> findUserByEmail(String email) throws DaoException {
        return executeSingleResult(FIND_USER_BY_EMAIL, email);
    }

    @Override
    public Optional<User> findUserByMobilePhone(String phoneNumber) {
        return executeSingleResult(FIND_USER_BY_MOBILE_PHONE, phoneNumber);
    }

    @Override
    public Optional<User> findUserByEmailAndPassword(String email, String password) throws DaoException {
        return executeSingleResult(DataBaseQueriesConstants.FIND_USER_BY_EMAIL_AND_PASSWORD, email, password);
    }
}
