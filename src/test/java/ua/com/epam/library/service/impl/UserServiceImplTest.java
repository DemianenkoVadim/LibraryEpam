package ua.com.epam.library.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import ua.com.epam.library.dao.RoleDao;
import ua.com.epam.library.dao.UserDao;
import ua.com.epam.library.entity.Role;
import ua.com.epam.library.entity.Status;
import ua.com.epam.library.entity.User;
import ua.com.epam.library.exception.myexception.UserNotFoundException;
import ua.com.epam.library.service.UserService;
import ua.com.epam.library.service.validation.Validator;
import ua.com.epam.library.service.validation.ValidatorFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;
import static ua.com.epam.library.entity.Status.ACTIVE;
import static ua.com.epam.library.entity.Status.BLOCKED;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class UserServiceImplTest {

    private UserService userService;

    @Mock
    private UserDao userDao;
    @Mock
    private RoleDao roleDao;

    private final Validator emailValidator = ValidatorFactory.getInstance().getEmailValidator();
    private final Validator nameValidator = ValidatorFactory.getInstance().getNameValidator();
    private final Validator phoneValidator = ValidatorFactory.getInstance().getPhoneValidator();

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(userDao, roleDao, emailValidator, nameValidator, phoneValidator);
    }

    @Test
    void testLogin_ShouldReturnSuccessfullLogin() {
        //given
        String email = "user@gmail.com";
        String password = "user";
        given(userDao.findUserByEmailAndPassword(email, password)).willReturn(Optional.of(new User()));

        //when
        when(userDao.findUserByEmailAndPassword(email, password)).thenReturn(Optional.of(new User()));
        Optional<User> userOptional = userService.login(email, password);

        //then
        assertTrue(userOptional.isPresent(), "User should be present");
        verify(userDao).findUserByEmailAndPassword(email, password);
    }

    @Test
    void testLogin_ShouldReturnEmptyOptionalIfEmailNull() {
        //given
        String password = "pa$$word";
        //when
        Optional<User> userOptional = userService.login(null, password);
        //then
        assertFalse(userOptional.isPresent(), "User should be present");
//        verifyNoInteractions(userDao);
    }

    @Test
    void testFindUserByIdAndPassword_ShouldReturnUser() {
        //given
        long id = 3;
        String userName = "Tommy";
        String userEmail = "tommy@gmail.com";
        String userPhone = "0987654321";
        String userPassword = "pa$$word";
        long userRoleId = 3;

        User testUser = createTestUser(id, userName, userEmail, userPhone, userPassword, userRoleId);

        given(userDao.findUserByIdAndPassword(id, userPassword)).willReturn(Optional.of(testUser));

        //when
        Optional<User> optionalUser = userService.findUserByIdAndPassword(id, userPassword);

        //then
        assertThat(optionalUser.isPresent()).isTrue();
        User user = optionalUser.get();

        assertUserFields(user);

        verify(userDao, times(1)).findUserByIdAndPassword(id, userPassword);
    }

    @Test
    void testGetById_ShouldReturnUser() {
        //given
        long id = 23;
        String userName = "Tommy";
        String userEmail = "tommy@gmail.com";
        String userPhone = "0987654321";
        String userPassword = "pa$$word";
        long userRoleId = 3;

        User testUser = createTestUser(id, userName, userEmail, userPhone, userPassword, userRoleId);

        given(userDao.findById(id)).willReturn(Optional.of(testUser));

        //when
        Optional<User> optionalUser = userService.getById(id);

        //then
        assertThat(optionalUser.isPresent()).isTrue();
        User user = optionalUser.get();

        assertUserFields(user);

        verify(userDao, times(1)).findById(id);
    }

    @Test
    void testGetById_ShouldThrown() {
        //give
        long id = -1;

        given(userDao.findById(id))
                .willThrow(new UserNotFoundException("User with id %d is not found".formatted(id)));
        //when
        //then
        assertThrows(UserNotFoundException.class, () -> Optional
                .ofNullable(userService.getById(id))
                .orElseThrow(() -> new UserNotFoundException("User with id" + id + " is not found")));
        verify(userDao).findById(id);
    }

    @Test
    void testUpdateProfile_ShouldReturnUpdatedProfile() {
        //given
        long id = 23L;
        String userName = "Tommy";
        String userEmail = "tommy@gmail.com";
        String userPhone = "0987654321";
        String userPassword = "pa$$word";
        long userRoleId = 3;

        String userNameUpdate = "TommyLee";

        User testUser = createTestUser(id, userName, userEmail, userPhone, userPassword, userRoleId);
        User updateUser = createTestUser(id, userNameUpdate, userEmail, userPhone, userPassword, userRoleId);

        given(userDao.findById(id)).willReturn(Optional.empty());
        given(userDao.updateProfile(testUser)).willReturn(true);

        //when
        when(userDao.findById(id)).thenReturn(Optional.empty());
        boolean updateProfile = userService.updateProfile(id, userNameUpdate, userEmail, userPhone);

        //then
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userDao).updateProfile(captor.capture());
        User user = captor.getValue();

        assertThat(user.getName()).isEqualTo("TommyLee");
        assertTrue(updateProfile);

        verify(userDao).updateProfile(updateUser);
    }

    @Test
    void testFindAllActiveUsers_ShouldReturnListOfActiveUsers() {
        //given
        List<User> clients = getListOfUsers();
        List<User> allActiveUsers = clients.stream()
                .filter(active -> active.getStatus().equals(ACTIVE))
                .collect(Collectors.toList());

        given(userDao.findAllActiveUsers()).willReturn(allActiveUsers);

        //when
        List<User> activeUsers = userService.findAllActiveUsers();

        //then
        assertThat(activeUsers).isNotNull();
        assertThat(activeUsers).isEqualTo(allActiveUsers);

        verify(userDao).findAllActiveUsers();
    }

    @Test
    void testFindAllActiveUsers_ShouldReturnSizeFindsListOfFoundActivesUsers() {
        //given
        List<User> clients = getListOfUsers();
        long sizeListOfActiveUsers = clients.stream()
                .filter(active -> active.getStatus().equals(ACTIVE))
                .count();

        given(userDao.findAllActiveUsers()).willReturn(clients);

        //when
        long listSize = userService
                .findAllActiveUsers()
                .stream()
                .filter(active -> active.getStatus().equals(ACTIVE))
                .count();

        //then
        assertThat(listSize).isNotNull();
        assertThat(listSize).isEqualTo(sizeListOfActiveUsers);

        verify(userDao).findAllActiveUsers();
    }

    @Test
    void testFindAllActiveUsers_EmptyListUsers_ReturnEmptyList() {
        //given
        List<User> clients = new ArrayList<>();
        given(userDao.findAllActiveUsers()).willReturn(clients);

        //when
        boolean isListUsersEmpty = userService.findAllActiveUsers().isEmpty();

        //then
        assertThat(isListUsersEmpty).isNotNull();
        assertThat(isListUsersEmpty).isEqualTo(clients.isEmpty());

        verify(userDao).findAllActiveUsers();
    }

    @Test
    void testFindAllBlockedUsers_ShouldReturnListOfBlockedUsers() {
        //given
        List<User> clients = getListOfUsers();
        List<User> allBlockedUsers = clients.stream()
                .filter(blocked -> blocked.getStatus().equals(BLOCKED))
                .collect(Collectors.toList());

        given(userDao.findAllBlockedUsers()).willReturn(allBlockedUsers);

        //when
        List<User> blockedUsers = userService.findAllBlockedUsers();

        //then
        assertThat(blockedUsers).isNotNull();
        assertThat(blockedUsers).isEqualTo(allBlockedUsers);

        verify(userDao).findAllBlockedUsers();
    }

    @Test
    void testFindAllBlockedUsers_ShouldReturnSizeFindsListOfFoundBlockedUsers() {
        //given
        List<User> clients = getListOfUsers();
        int sizeListOfBlockedUsers = (int) clients.stream()
                .filter(blocked -> blocked.getStatus().equals(BLOCKED))
                .count();

        given(userDao.findAllBlockedUsers()).willReturn(clients);

        //when
        int listSize = (int) userService
                .findAllBlockedUsers()
                .stream()
                .filter(blocked -> blocked.getStatus().equals(BLOCKED))
                .count();

        //then
        assertThat(listSize).isNotNull();
        assertThat(listSize).isEqualTo(sizeListOfBlockedUsers);

        verify(userDao).findAllBlockedUsers();
    }

    @Test
    void testFindAllBlockedUsers_EmptyListUsers_ReturnEmptyList() {
        //given
        List<User> clients = new ArrayList<>();
        given(userDao.findAllBlockedUsers()).willReturn(clients);

        //when
        boolean isListUsersEmpty = userService.findAllBlockedUsers().isEmpty();

        //then
        assertThat(isListUsersEmpty).isNotNull();
        assertThat(isListUsersEmpty).isEqualTo(clients.isEmpty());

        verify(userDao).findAllBlockedUsers();
    }

    @Test
    void testFindAllLibrarians_ShouldReturnListOfLibrarians() {
        //given
        List<User> librarians = getListOfUsers();

        List<User> allLibrarians = librarians.stream()
                .filter(librarian -> librarian.getRole().equals("librarian"))
                .collect(Collectors.toList());

        given(userDao.findAllLibrarians()).willReturn(allLibrarians);

        //when
        List<User> serviceLibrarian = userService.findAllLibrarians();

        //then
        assertThat(serviceLibrarian).isNotNull();
        assertThat(serviceLibrarian).isEqualTo(allLibrarians);

        verify(userDao).findAllLibrarians();
    }

    @Test
    void testFindAllLibrarians_ShouldReturnSizeFindsListOfrFoundLibrarians() {
        //given
        List<User> librarians = getListOfUsers();
        long sizeListOfAllLibrarians = librarians
                .stream()
                .filter(librarian -> librarian.getRole().equals("librarian"))
                .count();
        given(userDao.findAllLibrarians()).willReturn(librarians);

        //when
        long listSize = userService
                .findAllLibrarians()
                .stream()
                .filter(librarian -> librarian.getRole().equals("librarian"))
                .count();

        //then
        assertThat(listSize).isNotNull();
        assertThat(listSize).isEqualTo(sizeListOfAllLibrarians);

        verify(userDao).findAllLibrarians();
    }

    @Test
    void testFindAllLibrarians_EmptyListLibrarians_ReturnEmptyList() {
        //given
        List<User> librarians = new ArrayList<>();
        given(userDao.findAllLibrarians()).willReturn(librarians);

        //when
        boolean isListOfLibrariansEmpty = userService.findAllLibrarians().isEmpty();

        //then
        assertThat(isListOfLibrariansEmpty).isNotNull();
        assertThat(isListOfLibrariansEmpty).isEqualTo(librarians.isEmpty());

        verify(userDao).findAllLibrarians();
    }

    @Test
    void testRemove_ShouldReturnTrue() {
        //given
        long id = 23;
        String userName = "Tommy";
        String userEmail = "tommy@gmail.com";
        String userPhone = "0987654321";
        String userPassword = "pa$$word";
        long userRoleId = 3;

        User testUser = createTestUser(id, userName, userEmail, userPhone, userPassword, userRoleId);

        given(userDao.findById(id)).willReturn(Optional.of(testUser));
        given(userDao.remove(id)).willReturn(true);

        //when
        when(userDao.findById(testUser.getId())).thenReturn(Optional.of(testUser));
        when(userDao.remove(id)).thenReturn(true);

        boolean userIsRemoved = userService.remove(id);

        //then
        assertTrue(userIsRemoved);

        verify(userDao, times(1)).findById(id);
        verify(userDao, times(1)).remove(id);
    }

    @Test
    void testRemove_WrongUserId_ShouldThrownException() {
        //given
        given(userDao.remove(anyLong())).willThrow(new UserNotFoundException("User with id does not found"));

        //when
        //then
        assertThatThrownBy(() -> userService.remove(2))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("User with id: %d does not found".formatted(2));

        then(userDao).should(never()).remove(anyLong());
    }

    @Test
    void testBlockUser_ShouldChangeStatusAndReturnTrue() {
        //given
        long id = 23L;
        String userName = "Tommy";
        String userEmail = "tommy@gmail.com";
        String userPhone = "0987654321";
        String userPassword = "pa$$word";
        long userRoleId = 3;

        User testUser = createTestUser(id, userName, userEmail, userPhone, userPassword, userRoleId);

        given(userDao.findById(id)).willReturn(Optional.empty());
        given(userDao.blockUser(id)).willReturn(true);

        //when
        when(userDao.findById(testUser.getId())).thenReturn(Optional.of(testUser));
        when(userDao.blockUser(id)).thenReturn(true);

        boolean isUserBlocked = userService.blockUser(id);

        //then
        assertTrue(isUserBlocked);

        verify(userDao, times(1)).findById(id);
        verify(userDao, times(1)).blockUser(id);
    }

    @Test
    void testUnblockUser_ShouldChangeStatusAndReturnTrue() {
        //given
        long id = 23L;
        String userName = "Tommy";
        String userEmail = "tommy@gmail.com";
        String userPhone = "0987654321";
        String userPassword = "pa$$word";
        long userRoleId = 3;

        User testUser = createTestUser(id, userName, userEmail, userPhone, userPassword, userRoleId);

        given(userDao.findById(id)).willReturn(Optional.empty());
        given(userDao.unblockUser(id)).willReturn(true);

        //when
        when(userDao.findById(testUser.getId())).thenReturn(Optional.of(testUser));
        when(userDao.unblockUser(id)).thenReturn(true);

        boolean isUserBlocked = userService.unblockUser(id);

        //then
        assertTrue(isUserBlocked);

        verify(userDao, times(1)).findById(id);
        verify(userDao, times(1)).unblockUser(id);
    }

    @Test
    void testRegister_ShouldReturnTrue() {
        //given
        User user = getUser("Mouse", "mouse@gmail.com", "0987654321", "mouse", ACTIVE, 2, "user");

        given(roleDao.findByName(user.getRole())).willReturn(Optional.of(new Role(user.getRoleId(), LocalDateTime.now(), LocalDateTime.now(), user.getRole())));
        given(userDao.findUserByEmail(user.getEmail())).willReturn(Optional.empty());
        given(userDao.findUserByMobilePhone(user.getPhone())).willReturn(Optional.empty());
        given(userDao.registration(user)).willReturn(true);

        //when
        when(roleDao.findByName(user.getRole())).thenReturn(Optional.of(new Role(user.getName())));
        when(userDao.findUserByEmail(user.getEmail())).thenReturn(Optional.empty());
        when(userDao.findUserByMobilePhone(user.getPhone())).thenReturn(Optional.empty());
        when(userDao.registration(user)).thenReturn(true);

        boolean userIsRegistered = userService.register("Mouse", "mouse@gmail.com", "0987654321", "mouse");

        //then
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userDao).registration(userArgumentCaptor.capture());

        User capturedUser = userArgumentCaptor.getValue();

        assertThat(capturedUser).isEqualTo(user);
        assertTrue(userIsRegistered);

        verify(userDao, times(1)).registration(user);
        verify(userDao, times(1)).findUserByEmail(user.getEmail());
        verify(userDao, times(1)).findUserByMobilePhone(user.getPhone());
        verify(roleDao, times(1)).findByName(user.getRole());
    }

    @Test
    void testRegister_EmailAlreadyExists_ShouldThrownException() {
        //given
        List<User> users = getListOfUsers();
        User user = getUser("Mouse", "first@gmail.com", "0987654321", "mouse", ACTIVE, 2, "user");

        given(roleDao.findByName(user.getRole())).willReturn(Optional.of(new Role(user.getRoleId(), LocalDateTime.now(), LocalDateTime.now(), user.getRole())));
        given(userDao.findUserByEmail(user.getEmail())).willReturn(Optional.of(users.get(0)));
        given(userDao.registration(user)).willThrow(new IllegalStateException("User with entered email: %s already exists"));

        //when
        //then
        assertThatThrownBy(() -> userService.register(user.getName(), user.getEmail(), user.getPhone(), user.getPassword()))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("User with entered email: %s already exists".formatted(users.get(0).getEmail()));

        then(userDao).should(never()).registration(user);
    }

    @Test
    void testRegister_MobilePhoneAlreadyExist_ShouldThrownException() {
        //given
        List<User> users = getListOfUsers();
        User user = getUser("Mouse", "first@gmail.com", "0987654321", "mouse", ACTIVE, 2, "user");

        given(roleDao.findByName(user.getRole())).willReturn(Optional.of(new Role(user.getRoleId(), LocalDateTime.now(), LocalDateTime.now(), user.getRole())));
        given(userDao.findUserByEmail(user.getEmail())).willReturn(Optional.empty());
        given(userDao.findUserByMobilePhone(user.getPhone())).willReturn(Optional.of(users.get(0)));
        given(userDao.registration(user)).willThrow(new IllegalStateException("User with entered phone: %s already exist"));

        //when
        //then
        assertThatThrownBy(() -> userService.register(user.getName(), user.getEmail(), user.getPhone(), user.getPassword()))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("User with entered mobile phone number: %s already exist".formatted(users.get(0).getPhone()));

        then(userDao).should(never()).registration(user);
    }

    private void assertUserFields(User user) {
        assertThat(user.getName()).isEqualTo("Tommy");
        assertThat(user.getEmail()).isEqualTo("tommy@gmail.com");
        assertThat(user.getPhone()).isEqualTo("0987654321");
        assertThat(user.getPassword()).isEqualTo("pa$$word");
        assertThat(user.getStatus()).isEqualTo(ACTIVE);
        assertThat(user.getRoleId()).isEqualTo(3);
        assertThat(user.getId()).isNotNull();
    }

    private User createTestUser(
            long id,
            String userName,
            String userEmail,
            String userPhone,
            String userPassword,
            long userRoleId
    ) {
        User testUser = new User();
        testUser.setId(id);
        testUser.setName(userName);
        testUser.setEmail(userEmail);
        testUser.setPhone(userPhone);
        testUser.setPassword(userPassword);
        testUser.setStatus(Status.ACTIVE);
        testUser.setRoleId(userRoleId);
        return testUser;
    }

    private List<User> getListOfUsers() {
        List<User> users = new ArrayList<>();
        users.add(getUser("first user", "first@gmail.com", "0987654321", "firstUser", ACTIVE, 3, "user"));
        users.add(getUser("second user", "second@gmail.com", "0987654322", "secondUser", ACTIVE, 3, "user"));
        users.add(getUser("third user", "third@gmail.com", "0987654323", "thirdUser", BLOCKED, 3, "user"));
        users.add(getUser("fourth user", "fourth@gmail.com", "0987654324", "fourthUser", ACTIVE, 2, "librarian"));
        users.add(getUser("fifth user", "fifth@gmail.com", "0987654325", "fifthUser", ACTIVE, 2, "librarian"));
        users.add(getUser("sixth user", "sixth@gmail.com", "0987654326", "sixthUser", BLOCKED, 3, "user"));
        return users;
    }

    private User getUser(String name, String email, String phoneNumber, String password, Status status, long roleId, String role) {
        return new User(name, email, phoneNumber, password, status, roleId, role);
    }
}
