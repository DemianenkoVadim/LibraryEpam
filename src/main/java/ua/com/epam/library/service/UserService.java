package ua.com.epam.library.service;

import ua.com.epam.library.entity.User;
import ua.com.epam.library.exception.myexception.UserAlreadyExistException;
import ua.com.epam.library.exception.service.ServiceException;
import ua.com.epam.library.servlet.admin.request.CreateLibrarianRequest;

import java.util.List;
import java.util.Optional;

public interface UserService {

    /**
     * Method authenticate user
     *
     * @param email    user's email
     * @param password user's password
     * @return Optional of User
     * @throws ServiceException Service Exception
     */
    Optional<User> login(String email, String password) throws ServiceException;

    /**
     * Method to register new user
     *
     * @param name     user's name
     * @param email    user's email
     * @param phone    user's phone string type
     * @param password user's password
     * @return result of registration
     * @throws ServiceException Service Exception
     */
    boolean register(String name, String email, String phone, String password) throws ServiceException;

    /**
     * Method to retrieve user by ID
     *
     * @param userId ID of user to retrieve
     * @return optional of User
     * @throws ServiceException Service Exception
     */
    Optional<User> getById(long userId) throws ServiceException;

    /**
     * Method remove user by id
     *
     * @param id user id;
     * @return {@code true} if user was successfully deleted, {@code false} otherwise
     * @throws ServiceException Service Exception
     */
    boolean remove(long id) throws ServiceException;

    /**
     * The method assigns the user the status blocked
     *
     * @param id user id;
     * @return {@code true} if user status successfully changed, {@code false} otherwise
     * @throws ServiceException Service Exception
     */
    boolean blockUser(Long id) throws ServiceException;

    /**
     * The method assigns the user the status active
     *
     * @param id user id;
     * @return {@code true} if user status successfully changed, {@code false} otherwise
     * @throws ServiceException Service Exception
     */
    boolean unblockUser(Long id) throws ServiceException;

    /**
     * Method creates new Librarian
     *
     * @param createLibrarianRequest users parameters
     * @return true if user with role librarian is created otherwise false
     * @throws ServiceException          Exception
     * @throws UserAlreadyExistException Exception user already exist
     */
    boolean createLibrarian(CreateLibrarianRequest createLibrarianRequest) throws ServiceException, UserAlreadyExistException;

    /**
     * Method find user by user ID and password
     *
     * @param id       user ID
     * @param password user password
     * @return Optional of user
     * @throws ServiceException Service Exception
     */
    Optional<User> findUserByIdAndPassword(long id, String password) throws ServiceException;

    /**
     * Method update user`s profile
     *
     * @param id    user ID
     * @param email user email
     * @param phone user phone
     * @return true if user`s profile is updated
     * @throws ServiceException Service Exception
     */
    boolean updateProfile(long id, String name, String email, String phone) throws ServiceException;

    /**
     * Method finds all blocked users
     *
     * @return List of unblocked users
     * @throws ServiceException Service Exception
     */
    List<User> findAllBlockedUsers() throws ServiceException;

    /**
     * Method finds all librarians
     *
     * @return List of librarians
     * @throws ServiceException Service Exception
     */
    List<User> findAllLibrarians() throws ServiceException;

    /**
     * Method finds all active users
     *
     * @return List of active users
     * @throws ServiceException Service Exception
     */
    List<User> findAllActiveUsers() throws ServiceException;

    /**
     * Method finds user by email
     *
     * @param email user email
     * @return Optional of user
     * @throws ServiceException Exception
     */
    Optional<User> findUserByEmail(String email) throws ServiceException;

    /**
     * Method finds user by phone
     *
     * @param phone user phone number
     * @return Optional of user
     */
    Optional<User> findUserByPhone(String phone);
}
