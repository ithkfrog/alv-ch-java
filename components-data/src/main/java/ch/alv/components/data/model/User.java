package ch.alv.components.data.model;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * The User entity (works with table 'module_iam_user')
 *
 * @since 1.0.0
 */
@Entity
@Table(name = "iam_user")
@Document(indexName = "iam", type = "users", shards = 1, replicas = 0)
public class User extends BaseAuditableItem {

    private static final long serialVersionUID = -2143829548175561682L;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = true)
    private String firstName;

    @Column(nullable = true)
    private String lastName;

    @Column(nullable = true)
    private String password;

    @Column(nullable = true)
    private String email;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true)
    private Date lastLogin;

    @Column(nullable = true)
    private int numberOfFailedLogins;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
