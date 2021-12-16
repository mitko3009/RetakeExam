package com.example.finalproject.service.impl;

import com.example.finalproject.model.entity.RoleEntity;
import com.example.finalproject.model.entity.UserEntity;
import com.example.finalproject.model.enums.Roles;
import com.example.finalproject.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.management.relation.Role;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceTest {

    private ProjectService testService;
    private UserEntity testUser;

    private RoleEntity adminRole,userRole;

    @Mock
    private UserRepository userRepository;


    @BeforeEach
    void init() {

        testService = new ProjectService(userRepository);

        testUser = new UserEntity();

        adminRole = new RoleEntity();
        adminRole.setRole(Roles.ADMIN);

        userRole = new RoleEntity();
        userRole.setRole(Roles.USER);

        testUser.setUsername("mitko");
        testUser.setName("Mitko");
        testUser.setPassword("12345");
        testUser.setRoles(List.of(adminRole,userRole));
    }

    @Test
    void testUserNotFound(){
        Assertions.assertThrows(
                UsernameNotFoundException.class,
                () -> testService.loadUserByUsername("invalid_username_iok")
        );
    }

    @Test
    void testUserFound(){

        Mockito.when(userRepository.findByUsername(testUser.getUsername())).
                thenReturn(Optional.of(testUser));
        System.out.println();
        var actual = testService.loadUserByUsername(testUser.getUsername());

        String expectedRoles = "ROLE_ADMIN, ROLE_USER";
        String actualRoles = actual.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(
                Collectors.joining(", "));

        Assertions.assertEquals(actual.getUsername(), testUser.getUsername());
        Assertions.assertEquals(expectedRoles, actualRoles);
    }




}
