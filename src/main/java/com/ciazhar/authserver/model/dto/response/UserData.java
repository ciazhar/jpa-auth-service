package com.ciazhar.authserver.model.dto.response;

import com.ciazhar.authserver.config.AppConfig;
import com.ciazhar.authserver.model.jpa.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class UserData {

    private String id;
    private String email;
    private String phoneNumber;
    private String username;
    private String avatar;
    private Date birthDate;
    private Date joinedDate;
    private String firstname;
    private String lastname;

    private DateFormat dateFormat;

    public UserData() {
        dateFormat = new SimpleDateFormat(AppConfig.DATE_TIME_FORMAT, Locale.US);

    }

    public UserData(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.username = user.getUsername();
        this.avatar = user.getAvatar();
        this.birthDate = user.getBirthDate();
        this.joinedDate = user.getCreatedDate();
        this.firstname = user.getFirstName();
        this.lastname = user.getLastName();
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DateFormat getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getBirthDate() {
        return birthDate;
    }


    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(Date joinedDate) {
        this.joinedDate = joinedDate;
    }
}