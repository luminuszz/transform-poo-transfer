package org.cms.entity;

import org.cms.enums.Role;

public class User extends BaseEntity {
    private  String email;
    private  String password;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    private Role role;



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


    public User(String email, String password, Role role) {
        super();
        this.email = email;
        this.password = password;
        this.role = role;
    }


}
