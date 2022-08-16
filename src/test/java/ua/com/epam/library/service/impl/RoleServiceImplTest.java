package ua.com.epam.library.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.com.epam.library.dao.RoleDao;
import ua.com.epam.library.entity.Role;
import ua.com.epam.library.exception.myexception.RoleNoFoundException;
import ua.com.epam.library.service.RoleService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RoleServiceImplTest {

    private RoleService roleService;

    @Mock
    private RoleDao roleDao;

    @BeforeEach
    void setUp() {
        roleService = new RoleServiceImpl(roleDao);
    }

    @Test
    void testRetrieveRoleById_ShouldReturnRole() {
        //Given
        long id = 32;
        when(roleDao.findById(id)).thenReturn(Optional.of(new Role()));

        //When
        Optional<Role> roleOptional = roleService.retrieveRoleById(id);

        //Then
        assertTrue(roleOptional.isPresent(), "Role should be present");
        verify(roleDao).findById(id);
    }

    @Test
    void testRetrieveRoleById_ShouldReturnThrownWhenRoleIdIsZero() {
        //Given
        given(roleDao.findById(0)).willThrow(new RoleNoFoundException("Role with id %d is not found".formatted(0)));
        //When
        //Then
        assertThrows(RoleNoFoundException.class, () -> Optional
                .ofNullable(roleService.retrieveRoleById(0))
                .orElseThrow(() -> new RoleNoFoundException("Role with id %d is not found".formatted(0))));
    }

    @Test
    void testRetrieveRoleByName_UserRole_ShouldReturnUserRole() {
        //Given
        String role = "user";
        given(roleDao.findByName(role)).willReturn(Optional.of(new Role(role)));

        //When
        when(roleDao.findByName(role)).thenReturn(Optional.of(new Role(role)));
        Optional<Role> roleOptional = roleService.retrieveRoleByName(role);

        //Then
        assertTrue(roleOptional.isPresent(), "Role should be present");
        Role userRole = roleOptional.get();

        assertThat(userRole.getName()).isEqualTo(role);
        verify(roleDao).findByName(role);
    }
}
