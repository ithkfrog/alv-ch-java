package ch.alv.components.iam.endpoint.dto;

import ch.alv.components.web.dto.DtoImpl;
import org.springframework.hateoas.Link;

import java.util.Date;
import java.util.List;

/**
 * WebApi dto for the {@link ch.alv.components.iam.model.User} entity.
 *
 * @since 1.0.0
 */
public class CurrentUserDto extends DtoImpl {

    private String userName;

    private String firstName;

    private String lastName;

    private String email;

    private Date lastLogin;

    private int numberOfFailedLogins;

    private Link createUser;

    private Date createDate;

    private Link updateUser;

    private Date updateDate;

    private List<String> roles;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public int getNumberOfFailedLogins() {
        return numberOfFailedLogins;
    }

    public void setNumberOfFailedLogins(int numberOfFailedLogins) {
        this.numberOfFailedLogins = numberOfFailedLogins;
    }

    public Link getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Link createUser) {
        this.createUser = createUser;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Link getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Link updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
