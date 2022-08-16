package ua.com.epam.library.entity;

import java.util.Objects;

public class User extends Entity<User> implements Identifiable {

    private String name;
    private String email;
    private String phone;
    private String password;
    private Status status;

    private long roleId;
    private String role;

    public User() {
    }

    public User(String name,
                String email,
                String phone,
                String password,
                Status status,
                long roleId,
                String role) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.status = status;
        this.roleId = roleId;
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;

        if (!Objects.equals(email, user.email)) return false;
        return Objects.equals(phone, user.phone);
    }

    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        return result;
    }
}
