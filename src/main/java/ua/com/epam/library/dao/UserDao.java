package ua.com.epam.library.dao;

import ua.com.epam.library.entity.User;
import ua.com.epam.library.exception.dao.DaoException;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    /**
     * Method registered user
     *
     * @param user object to add
     * @return true if user registered otherwise false
     * @throws DaoException DAO Exception
     */
    boolean registration(User user) throws DaoException;

    /**
     * Method create librarian
     *
     * @param user object to add
     * @return true if user created
     * @throws DaoException DAO Exception
     */
    boolean createLibrarian(User user) throws DaoException;

    /**
     * Method update profile of user
     *
     * @param user object to update
     * @return true if user profile is updated otherwise false
     * @throws DaoException DAO Exception
     */
    boolean updateProfile(User user) throws DaoException;

    /**
     * Method remove user
     *
     * @param id user id
     * @return true if user removed
     * @throws DaoException DAO Exception
     */
    boolean remove(Long id) throws DaoException;

    /**
     * Method finds all user with role - librarians
     *
     * @return List of users with role - librarians
     * @throws DaoException DAO Exception
     */
    List<User> findAllLibrarians() throws DaoException;

    /**
     * Method finds user by email and password
     *
     * @param email    user email
     * @param password user password
     * @return Optional of user
     * @throws DaoException DAO Exception
     */
    Optional<User> findUserByEmailAndPassword(String email, String password) throws DaoException;

    /**
     * Method finds user by user ID
     *
     * @param userId user ID
     * @return Optional of user
     * @throws DaoException DAO Exception
     */
    Optional<User> findById(long userId) throws DaoException;

    /**
     * Method finds user by user ID and Password
     *
     * @param id       user ID
     * @param password user Password
     * @return Optional of user
     * @throws DaoException DAO Exception
     */
    Optional<User> findUserByIdAndPassword(Long id, String password) throws DaoException;

    /**
     * Method finds all users with status - Active
     *
     * @return List of users
     * @throws DaoException DAO Exception
     */
    List<User> findAllActiveUsers() throws DaoException;

    /**
     * Method finds all users with status - Blocked
     *
     * @return List of users
     * @throws DaoException DAO Exception
     */
    List<User> findAllBlockedUsers() throws DaoException;

    /**
     * Method blocked user
     *
     * @param id user ID
     * @return true if user blocked
     * @throws DaoException DAO Exception
     */
    boolean blockUser(Long id) throws DaoException;

    /**
     * Method unblocked user
     *
     * @param id user ID
     * @return true if user unblocked otherwise
     * @throws DaoException DAO Exception
     */
    boolean unblockUser(Long id) throws DaoException;

    /**
     * Method finds user by email
     *
     * @param email user email
     * @return Optional of user
     * @throws DaoException DAO Exception
     */
    Optional<User> findUserByEmail(String email) throws DaoException;

    /**
     * Method finds user by mobile phone number
     *
     * @param phoneNumber user mobile phone number
     * @return Optional of user
     * @throws DaoException DAO Exception
     */
    Optional<User> findUserByMobilePhone(String phoneNumber);
}
