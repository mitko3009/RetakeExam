package com.example.finalproject.service.impl;

import com.example.finalproject.model.view.UserViewModel;
import com.example.finalproject.repository.UserRepository;
import com.example.finalproject.repository.UserRoleRepository;
import com.example.finalproject.model.entity.RoleEntity;
import com.example.finalproject.model.entity.UserEntity;
import com.example.finalproject.model.enums.Roles;
import com.example.finalproject.model.service.UserRegisterServiceModel;
import com.example.finalproject.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;
    private ProjectService projectService;
    private PasswordEncoder passwordEncoder;
    private ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository,
                           UserRoleRepository userRoleRepository,
                           ProjectService projectService,
                           PasswordEncoder passwordEncoder,
                           ModelMapper modelMapper) {

        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.projectService = projectService;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean loginAndRegisterUser(UserRegisterServiceModel userSeviceModel) {

        //The first registered user is admin and regular user. Every next register user is just simple user.

        List<RoleEntity> roles = new ArrayList<>();

        if (userRepository.findAll().isEmpty()){
            RoleEntity adminRole = userRoleRepository.findByRole(Roles.ADMIN);

            roles.add(adminRole);
        }

        RoleEntity userRole = userRoleRepository.findByRole(Roles.USER);

        roles.add(userRole);

        if (!userRepository.findByUsername(userSeviceModel.getUsername()).isEmpty()){
           return false;
        }

        UserEntity newUser = new UserEntity();

        newUser.setUsername(userSeviceModel.getUsername());
        newUser.setPassword(passwordEncoder.encode(userSeviceModel.getPassword()));
        newUser.setAge(userSeviceModel.getAge());
        newUser.setName(userSeviceModel.getName());
        newUser.setRoles(roles);

        userRepository.save(newUser);

        //After successful registration the user will be automatically logged in.

        UserDetails principal = projectService.loadUserByUsername(newUser.getUsername());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal,
                newUser.getPassword(),
                principal.getAuthorities()
        );

        SecurityContextHolder.
                getContext().
                setAuthentication(authentication);

        return true;
    }

    @Override
    public UserEntity findUserByUsername(String username) {
       return userRepository.findUserByUsername(username).get();
    }

    @Override
    public List<UserViewModel> getAllUsers() {
        List<UserEntity> all = userRepository.findAll();

        List<UserViewModel> userViewModels = new ArrayList<>();

        for (UserEntity user : all) {
            UserViewModel viewModel = modelMapper.map(user, UserViewModel.class);
            viewModel.setRoles(user.getRoles());

            userViewModels.add(viewModel);
        }

        return userViewModels;
    }

    @Override
    public void deleteUser(Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (userRepository.findAll().size() > 1 && !auth.getPrincipal().equals(userRepository.findById(id).get().getUsername())) {
            userRepository.deleteById(id);
        }
    }
}
