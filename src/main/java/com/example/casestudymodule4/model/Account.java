package com.example.casestudymodule4.model;

import com.example.casestudymodule4.model.DTO.RoleName;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String gmail;
    @Column(nullable = false)
    private String password;
    private Boolean status = false;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "account_role",
               joinColumns = {@JoinColumn(name = "account_id")},
               inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne
    @JoinColumn(name = "company_id")
    private Company company;

    public Account(Long id, String gmail, String password, Boolean status, Set<Role> roles, User user, Company company) {
        this.id = id;
        this.gmail = gmail;
        this.password = password;
        this.status = status;
        this.roles = roles;
        this.user = user;
        this.company = company;
    }

    public Account(String gmail, String password) {
        this.gmail = gmail;
        this.password = password;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }


    public List<Role> getRoleName(){
        List<Role> roleList = new ArrayList<>();
        for (Role role : roles) {
            role.getName();
            roleList.add(role);
        }
        return roleList;
    }
    public Account() {
    }
}
