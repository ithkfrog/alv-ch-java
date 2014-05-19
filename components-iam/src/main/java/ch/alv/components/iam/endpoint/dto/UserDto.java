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
public class UserDto extends DtoImpl {

    private String userName;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private List<Link> roles;

    private Date updateDate;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Link> getRoles() {
        return roles;
    }

    public void setRoles(List<Link> roles) {
        this.roles = roles;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
