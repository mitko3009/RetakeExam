package com.example.finalproject.service;

import com.example.finalproject.model.service.LaptopServiceModel;
import com.example.finalproject.model.service.LaptopUpdateServiceModel;
import com.example.finalproject.model.view.LaptopDetailsViewModel;
import com.example.finalproject.model.view.LaptopOfferView;

import java.util.List;

public interface LaptopService {

    void addLaptop(LaptopServiceModel laptopServiceModel,String username);
    List<LaptopOfferView> getAllLaptops();

    LaptopDetailsViewModel findLaptopById(Long id);
    void update(LaptopUpdateServiceModel laptopUpdateServiceModel);

    void delete(Long id);
    boolean isOwner(String userName, Long id);
}
