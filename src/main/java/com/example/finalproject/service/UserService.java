package com.example.finalproject.service;

import com.example.finalproject.model.entity.UserEntity;
import com.example.finalproject.model.service.UserRegisterServiceModel;
import com.example.finalproject.model.view.UserViewModel;

import java.util.List;

public interface UserService {

    boolean loginAndRegisterUser(UserRegisterServiceModel userSeviceModel);
    UserEntity findUserByUsername(String username);

    List<UserViewModel> getAllUsers();

    void deleteUser(Long id);
}
