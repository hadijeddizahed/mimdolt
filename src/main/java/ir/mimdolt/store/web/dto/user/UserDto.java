package ir.mimdolt.store.web.dto.user;

import ir.mimdolt.store.persist.entity.role.Role;
import ir.mimdolt.store.persist.entity.user.User;
import ir.mimdolt.store.web.dto.BaseDTO;

import java.util.HashSet;
import java.util.stream.Collectors;

/**
 * Created by Hadi Jeddi Zahed on 1/4/2017.
 */
public class UserDto extends BaseDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String mobile;
    private String password;
    private String gRecaptchaResponse;
    private HashSet<String> roles = new HashSet<>();

    public UserDto() {
    }

    public UserDto(String firstName, String lastName, String email, String username, String password, HashSet<String> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getgRecaptchaResponse() {
        return gRecaptchaResponse;
    }

    public void setgRecaptchaResponse(String gRecaptchaResponse) {
        this.gRecaptchaResponse = gRecaptchaResponse;
    }

    public HashSet<String> getRoles() {
        return roles;
    }

    public void setRoles(HashSet<String> roles) {
        this.roles = roles;
    }

    public UserDto map(User user) throws Exception {
        if (user == null)
            return new UserDto();
        this.setId(user.getId());
        this.setFirstName(user.getFirstName());
        this.setLastName(user.getLastName());
        this.setEmail(user.getEmail());
        this.setUsername(user.getUsername());
        roles.addAll(user.getRoles().stream().map(Role::getName).collect(Collectors.toList()));
        return this;
    }
}
