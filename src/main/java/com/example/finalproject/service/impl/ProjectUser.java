package com.example.finalproject.service.impl;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class ProjectUser extends User {

    public ProjectUser(String username, String password,
                        Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public ProjectUser(String username, String password, boolean enabled, boolean accountNonExpired,
                        boolean credentialsNonExpired, boolean accountNonLocked,
                        Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked,
                authorities);
    }

    public String getUserIdentifier() {
        return getUsername();
    }
}

