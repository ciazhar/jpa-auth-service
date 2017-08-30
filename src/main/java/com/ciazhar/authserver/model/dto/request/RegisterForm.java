package com.ciazhar.authserver.model.dto.request;

/**
 * Created by ciazhar on 6/23/17.
 */


public class RegisterForm {
    private String email;
    private String password;


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
}
