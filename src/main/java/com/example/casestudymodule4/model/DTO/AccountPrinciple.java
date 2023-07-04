package com.example.casestudymodule4.model.DTO;

import com.example.casestudymodule4.model.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AccountPrinciple implements UserDetails {
    @Serial
    private static final long serialVersionUID = 1L;
    private Long id;
    private String gmail;
    private String password;
    private Collection<? extends GrantedAuthority> roles;

    public AccountPrinciple(Long id, String gmail, String password, Collection<? extends GrantedAuthority> roles) {
        this.id = id;
        this.gmail = gmail;
        this.password = password;
        this.roles = roles;
    }
    public static AccountPrinciple build(Account account){
        List<GrantedAuthority> authorityList = account.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getName())
        ).collect(Collectors.toList());

        return new AccountPrinciple(
                account.getId(),
                account.getGmail(),
                account.getPassword(),
                authorityList
        );
    }

    public Long getId() {
        return id;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return gmail;
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
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountPrinciple account = (AccountPrinciple) o;
        return Objects.equals(id, account.id);
    }
    @Override
    public int hashCode(){
        return super.hashCode();
    }
}
