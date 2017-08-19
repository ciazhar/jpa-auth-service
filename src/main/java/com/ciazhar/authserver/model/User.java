package com.ciazhar.authserver.model;

import com.ciazhar.authserver.dto.request.ProfileForm;
import com.ciazhar.authserver.dto.request.RegisterForm;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by ciazhar on 5/27/17.
 */

/**
 * Model untuk user
 */
@Entity
public class User {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "uuid2")
    @Column(name = "id_user")
    private String id;

    @Column(unique = true)
    private String username;

    @NotNull
    private String password;

    @NotNull
    @Column(columnDefinition = "boolean DEFAULT 1")
    private boolean enabled;

    @Email
    @NotNull
    @Column(unique = true)
    private String email;

    private String phoneNumber;

    private String avatar;

    private String firstName;

    private String lastName;

    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date createdDate;

//    @NotNull
    private String androidDeviceId;

    @ManyToOne
    @JoinColumn(name = "id_role", nullable = false)
    @NotNull
    private Role role;

    public User() {
    }

    public User(String username, String password, boolean enabled, String email, String phoneNumber, String avatar, String firstName, String lastName, Date birthDate, Date createdDate, String androidDeviceId, Role role) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.avatar = avatar;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.createdDate = createdDate;
        this.androidDeviceId = androidDeviceId;
        this.role = role;
    }

    public User(RegisterForm form, Role role, Date date) {
        this.password = form.getPassword();
        this.email = form.getEmail();
        this.createdDate = date;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getAndroidDeviceId() {
        return androidDeviceId;
    }

    public void setAndroidDeviceId(String androidDeviceId) {
        this.androidDeviceId = androidDeviceId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setProfileForm(ProfileForm profileForm){
        this.id = profileForm.getId();
        this.username = profileForm.getUsername();
        this.firstName = profileForm.getFirstname();
        this.lastName = profileForm.getLastname();
        this.avatar = profileForm.getAvatar();
        this.birthDate = profileForm.getBirthDate();
        this.androidDeviceId = profileForm.getAndroidDeviceId();
        this.email = profileForm.getEmail();
        this.phoneNumber = profileForm.getPhone();
        this.password = profileForm.getPassword();
    }

}
