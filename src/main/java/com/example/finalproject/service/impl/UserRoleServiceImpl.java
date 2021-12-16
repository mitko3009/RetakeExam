package com.example.finalproject.service.impl;

import com.example.finalproject.repository.UserRoleRepository;
import com.example.finalproject.model.entity.RoleEntity;
import com.example.finalproject.model.enums.Roles;
import com.example.finalproject.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private UserRoleRepository userRoleRepository;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {

        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void initRoles() {
        if (userRoleRepository.findAll().isEmpty()) {

            RoleEntity user = new RoleEntity();
            user.setRole(Roles.USER);

            RoleEntity admin = new RoleEntity();
            admin.setRole(Roles.ADMIN);

            userRoleRepository.saveAll(List.of(user, admin));
        }

    }
}
