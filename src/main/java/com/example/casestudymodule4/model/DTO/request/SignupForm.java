package com.example.casestudymodule4.model.DTO.request;

import java.util.Set;

public class SignupForm {
    private Long id;
    private String gmail;
    private String password;
    private Set<String> roles;

    public SignupForm() {
    }

    public SignupForm(Long id, String gmail, String password, Set<String> roles) {
        this.id = id;
        this.gmail = gmail;
        this.password = password;
        this.roles = roles;
    }

    public SignupForm(String gmail, String password, Set<String> roles) {
        this.gmail = gmail;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
