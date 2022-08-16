package ua.com.epam.library.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import ua.com.epam.library.dao.DaoFactory;
import ua.com.epam.library.dao.RoleDao;
import ua.com.epam.library.dao.UserDao;
import ua.com.epam.library.entity.Role;
import ua.com.epam.library.entity.Status;
import ua.com.epam.library.entity.User;
import ua.com.epam.library.exception.dao.DaoException;
import ua.com.epam.library.exception.myexception.UserAlreadyExistException;
import ua.com.epam.library.exception.myexception.UserNotFoundException;
import ua.com.epam.library.exception.service.ServiceException;
import ua.com.epam.library.service.UserService;
import ua.com.epam.library.service.validation.Validator;
import ua.com.epam.library.service.validation.ValidatorFactory;
import ua.com.epam.library.servlet.admin.request.CreateLibrarianRequest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static ua.com.epam.library.util.Constant.LIBRARIAN;
import static ua.com.epam.library.util.Constant.USER;

public class UserServiceImpl implements UserService {

    private static final Logger log = (Logger) LogManager.getLogger(UserServiceImpl.class.getName());

    private final UserDao userDao;
    private final RoleDao roleDao;
    private final Validator emailValidator;
    private final Validator nameValidator;
    private final Validator phoneValidator;

    public UserServiceImpl() {
        this(
                DaoFactory.getInstance().getUserDao(),
                DaoFactory.getInstance().getRoleDao(),
                ValidatorFactory.getInstance().getEmailValidator(),
                ValidatorFactory.getInstance().getNameValidator(),
                ValidatorFactory.getInstance().getPhoneValidator()
        );
    }

    public UserServiceImpl(UserDao userDao, RoleDao roleDao, Validator emailValidator, Validator nameValidator, Validator phoneValidator) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.emailValidator = emailValidator;
        this.nameValidator = nameValidator;
        this.phoneValidator = phoneValidator;
    }

    @Override
    public Optional<User> login(String email, String password) throws ServiceException, DaoException {
        log.info("Checking email and password for null and email for validity");
        return parametersAreNull(email, password) || !chekUserEmailForValidity(email) ? Optional.empty() : getByEmailAndPassword(email, password);
    }

    @Override
    public Optional<User> findUserByIdAndPassword(long id, String password) {
        log.info("Searching user by id: {} and password", id);
        return Optional.ofNullable(userDao.findUserByIdAndPassword(id, password)
                .orElseThrow(() -> new UserNotFoundException("User with id: %d and input password does`t found".formatted(id))));
    }

    @Override
    public boolean updateProfile(long id, String name, String email, String phone) {
        checkNameAndEmailAndPhoneForValidity(name, email, phone);
        User user = buildUserToUpdate(id, name, email, phone);
        log.info("User`s profile has been updated successfully");
        return userDao.updateProfile(user);
    }

    @Override
    public List<User> findAllActiveUsers() throws ServiceException {
        log.info("Searching for all users with status - 'ACTIVE'");
        return userDao.findAllActiveUsers();
    }

    @Override
    public Optional<User> findUserByEmail(String email) throws ServiceException {
        log.info("Searching for user by email: {} ", email);
        return userDao.findUserByEmail(email);
    }

    @Override
    public Optional<User> findUserByPhone(String phone) {
        log.info("Searching for user by phone number: {} ", phone);
        return userDao.findUserByMobilePhone(phone);
    }

    @Override
    public List<User> findAllBlockedUsers() throws ServiceException {
        log.info("Searching for all users with status - 'BLOCKED'");
        return userDao.findAllBlockedUsers();
    }

    @Override
    public List<User> findAllLibrarians() throws ServiceException {
        log.info("Searching for all users with role - 'Librarian'");
        return userDao.findAllLibrarians();
    }

    @Override
    public Optional<User> getById(long id) throws DaoException, UserNotFoundException {
        log.info("Searching User with id: {}", id);
        return Optional.ofNullable(userDao.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id: %d does not found".formatted(id))));
    }

    @Override
    public boolean remove(long id) throws ServiceException, DaoException {
        Optional<User> user = getById(id);
        log.info("Removing User with id: {}", id);
        if (user.isEmpty()) throw new ServiceException("User with id: %d does not found".formatted(id));
        log.info("User with id: {} successfully delete", id);
        return userDao.remove(id);
    }

    @Override
    public boolean blockUser(Long id) throws DaoException, ServiceException {
        Optional<User> user = getById(id);
        log.info("Blocking User with id: {}", id);
        if (user.isEmpty()) throw new ServiceException("User with id: {}" + id + " does`t found");
        log.info("User with id: {} successfully blocked", id);
        return userDao.blockUser(id);
    }

    @Override
    public boolean unblockUser(Long id) throws DaoException, ServiceException {
        Optional<User> user = getById(id);
        log.info("Unblocking User with id: {}", id);
        if (user.isEmpty()) throw new ServiceException("User with id: {}" + id + " does`t found");
        log.info("User with id: {} successfully unblocked", id);
        return userDao.unblockUser(id);
    }

    @Override
    public boolean createLibrarian(CreateLibrarianRequest createLibrarianRequest) {
        log.info("Creating new librarian");
        Optional<Role> role = getRole(LIBRARIAN, "Finding role by name - 'librarian'", "Role with name: {} does not exists");
        long roleId = role.orElseThrow().getId();
        User user = buildingUser(createLibrarianRequest, roleId);
        log.info("Librarian is created successfully");
        return userDao.createLibrarian(user);
    }

    private User buildingUser(CreateLibrarianRequest createLibrarianRequest, long roleId) {
        User user = new User();
        user.setName(createLibrarianRequest.getName());
        user.setEmail(createLibrarianRequest.getEmail());
        user.setPhone(createLibrarianRequest.getPhone());
        user.setPassword(createLibrarianRequest.getPassword());
        user.setRoleId(roleId);
        user.setCreated(LocalDateTime.now());
        user.setUpdated(LocalDateTime.now());
        user.setStatus(Status.ACTIVE);
        return user;
    }

    @Override
    public boolean register(String name, String email, String phone, String password) throws ServiceException {
        checkEmailAndMobilePhoneForDuplicate(email, phone);
        checkNameAndEmailAndPhoneForValidity(name, email, phone);

        Optional<Role> role = getRole(USER, "Finding role by name - 'user'", "Role with name: {} does not exist");

        User user = buildingUser(name, email, phone, password, role.get().getId());
        userDao.registration(user);
        log.info("User with name: {} email: {} phone: {} register successfully", name, email, phone);
        return true;
    }

    private Optional<User> getByEmailAndPassword(String email, String password) throws DaoException, ServiceException {
        log.info("Searching user with email: {} and password", email);
        return Optional.ofNullable(userDao.findUserByEmailAndPassword(email, password)
                .orElseThrow(() -> new UserNotFoundException("User with email: %s and input password does not found".formatted(email))));
    }

    private boolean parametersAreNull(Object... parameters) {
        log.info("Checking entered parameters for null");
        return Arrays.stream(parameters).anyMatch(Objects::isNull);
    }

    private void checkEmailForDuplicate(String email) throws UserAlreadyExistException {
        log.info("Checking user email: {} for duplicate", email);
        if (userDao.findUserByEmail(email).isEmpty()) {
            log.info("User with email: {} does`t exists", email);
        } else {
            log.warn("User with email: {} has already been registered", email);
            throw new IllegalStateException("User with entered email: %s already exists".formatted(email));
        }
    }

    private void checkMobilePhoneForDuplicate(String phoneNumber) throws UserAlreadyExistException {
        log.info("Checking user mobile phone number: {} for duplicate", phoneNumber);
        if (userDao.findUserByMobilePhone(phoneNumber).isEmpty()) {
            log.info("User with mobile phone number: {} does not exist", phoneNumber);
        } else {
            log.warn("User with mobile phone number: {} has already been registered", phoneNumber);
            throw new IllegalStateException("User with entered mobile phone number: %s already exist".formatted(phoneNumber));
        }
    }

    private void checkUserNameForValidity(String userName) {
        if (isUserNameValid(userName)) {
            log.info("User name: {} is valid", userName);
        } else {
            log.warn("User does not register, because name: {} is not valid", userName);
            throw new IllegalStateException("Entered user name: %s is not valid".formatted(userName));
        }
    }

    private boolean chekUserEmailForValidity(String email) {
        if (isEmailValid(email)) {
            log.info("User email: {} is valid", email);
            return true;
        } else {
            log.warn("User does not register, because email: {} is not valid", email);
            throw new IllegalStateException("Entered user email: %s is not valid".formatted(email));
        }
    }

    private void chekUserMobileUkrainePhoneForValidity(String mobileUkrainePhoneNumber) {
        if (isMobilePhoneValid(mobileUkrainePhoneNumber)) {
            log.info("User mobile Ukraine phone number: {} is valid", mobileUkrainePhoneNumber);
        } else {
            log.warn("User does not register, because mobile Ukraine phone number: {} is not valid", mobileUkrainePhoneNumber);
            throw new IllegalStateException("Entered user mobile Ukraine phone number: %s is not valid".formatted(mobileUkrainePhoneNumber));
        }
    }

    private boolean isUserNameValid(String userName) {
        log.info("Checking entered user name: {} for validity", userName);
        return nameValidator.isValid(userName);
    }

    private boolean isEmailValid(String email) {
        log.info("Checking email: {} for validity", email);
        return emailValidator.isValid(email);
    }

    private boolean isMobilePhoneValid(String mobileUkrainePhoneNumber) {
        log.info("Checking entered Ukraine mobile  phone number: {} for validity", mobileUkrainePhoneNumber);
        return phoneValidator.isValid(mobileUkrainePhoneNumber);
    }

    private void checkNameAndEmailAndPhoneForValidity(String name, String email, String phone) {
        checkUserNameForValidity(name);
        chekUserEmailForValidity(email);
        chekUserMobileUkrainePhoneForValidity(phone);
    }

    private void checkEmailAndMobilePhoneForDuplicate(String email, String phone) {
        checkEmailForDuplicate(email);
        checkMobilePhoneForDuplicate(phone);
    }

    private Optional<Role> getRole(String createdUser, String findRoleMessage, String roleNotExistMessage) {
        Optional<Role> role = roleDao.findByName(createdUser);
        log.info(findRoleMessage);
        if (role.isEmpty()) {
            log.warn(roleNotExistMessage, createdUser);
            throw new IllegalStateException("Role with name: " + createdUser + " does not exist");
        }
        return role;
    }

    private User buildingUser(String name, String email, String phone, String password, long roleId) {
        User customer = new User();
        customer.setName(name);
        customer.setEmail(email);
        customer.setPhone(phone);
        customer.setPassword(password);
        customer.setRoleId(roleId);
        customer.setCreated(LocalDateTime.now());
        customer.setUpdated(LocalDateTime.now());
        customer.setStatus(Status.ACTIVE);
        return customer;
    }

    private User buildUserToUpdate(long id, String name, String email, String phone) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);
        return user;
    }
}
