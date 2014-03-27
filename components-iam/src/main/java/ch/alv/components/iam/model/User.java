package ch.alv.components.iam.model;

import ch.alv.components.persistence.model.BaseJpaModelItem;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * The User entity (works with table 'am_iam_user')
 *
 * @since 1.0.0
 */
@Entity
@Table(name = "am_iam_user")
public class User extends BaseJpaModelItem implements UserDetails {

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
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

    @JoinColumn(name = "create_user_id", nullable = true)
    @ManyToOne
    private User createUser;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true)
    private Date createDate;

    @JoinColumn(name = "update_user_id", nullable = true)
    @ManyToOne
    private User updateUser;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true)
    private Date updateDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "sm_sec_role_to_user",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;

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

    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public User getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(User updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths = new ArrayList<>();
        for (Role role : roles) {
            GrantedAuthority auth = new SimpleGrantedAuthority(role.getName());
            auths.add(auth);
        }
        return auths;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
