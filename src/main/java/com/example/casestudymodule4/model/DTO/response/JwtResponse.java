package com.example.casestudymodule4.model.DTO.response;

import com.example.casestudymodule4.model.Role;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public class JwtResponse {
    private Long id;
    private String token;
    private String type = "Bearer";
    private String gmail;
    private List<Role> role;
    private final Collection<? extends GrantedAuthority> roles;

    public JwtResponse(Long id, String token, String gmail, List<Role> roleName, Collection<? extends GrantedAuthority> roles) {
        this.id = id;
        this.token = token;
        this.gmail = gmail;
        this.role = roleName;
        this.roles = roles;
    }

    public List<Role> getRole() {
        return role;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public Collection<? extends GrantedAuthority> getRoles() {
        return roles;
    }
}
