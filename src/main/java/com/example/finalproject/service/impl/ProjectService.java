package com.example.finalproject.service.impl;

import com.example.finalproject.repository.UserRepository;
import com.example.finalproject.model.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService implements UserDetailsService {


    private UserRepository userRepository;

    public ProjectService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity entity = userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User with username " + username + " do not exists."));

        return mapUserToUserDetails(entity);
    }

    private UserDetails mapUserToUserDetails(UserEntity userEntity){

        List<GrantedAuthority> auhtorities =
                userEntity.
                        getRoles().
                        stream().
                        map(r -> new SimpleGrantedAuthority("ROLE_" + r.getRole().name())).
                        collect(Collectors.toList());

        // User is the spring implementation of UserDetails interface.
        return new ProjectUser(
                userEntity.getUsername(),
                userEntity.getPassword(),
                auhtorities
        );

    }
}
