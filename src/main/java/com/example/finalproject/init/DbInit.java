package com.example.finalproject.init;

import com.example.finalproject.service.CityService;
import com.example.finalproject.service.UserRoleService;
import com.example.finalproject.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DbInit implements CommandLineRunner {


    private UserRoleService userRoleService;
    private CityService cityService;


    public DbInit(UserRoleService userRoleService,CityService cityService) {


        this.userRoleService = userRoleService;
        this.cityService = cityService;
    }

    @Override
    public void run(String... args) throws Exception {

        userRoleService.initRoles();
        cityService.initCities();
    }
}
