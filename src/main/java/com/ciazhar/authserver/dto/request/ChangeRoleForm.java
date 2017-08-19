package com.ciazhar.authserver.dto.request;

/**
 * Created by ciazhar on 6/23/17.
 */

public class ChangeRoleForm {
    private String id;
    private String role;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
