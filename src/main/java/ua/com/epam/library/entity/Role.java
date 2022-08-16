package ua.com.epam.library.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Role extends Entity<Role> implements Identifiable {

    private String role;
    private List<User> users;

    public Role() {
    }

    public Role(String role) {
        this.role = role;
    }

    public Role(long id, LocalDateTime created, LocalDateTime updated, String role) {
        super(id, created, updated);
        this.role = role;
    }

    public String getName() {
        return role;
    }

    public void setName(String role) {
        this.role = role;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role roleName)) return false;

        if (!Objects.equals(role, roleName.role)) return false;
        return Objects.equals(users, roleName.users);
    }

    @Override
    public int hashCode() {
        int result = role != null ? role.hashCode() : 0;
        result = 31 * result + (users != null ? users.hashCode() : 0);
        return result;
    }
}
