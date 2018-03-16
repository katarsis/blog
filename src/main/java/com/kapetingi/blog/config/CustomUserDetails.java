package com.kapetingi.blog.config;

import com.kapetingi.blog.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {

    private String userName;
    private String password;
    Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(User byUserName) {
        this.userName = byUserName.getUserName();
        this.password = byUserName.getPassword();

        List<GrantedAuthority> auths = byUserName.getRoles()
                        .stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName().toUpperCase()))
                        .collect(Collectors.toList());
        this.authorities = auths;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
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
