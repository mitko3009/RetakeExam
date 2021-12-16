package com.example.finalproject.service;

import com.example.finalproject.model.view.CityViewModel;

import java.util.List;

public interface CityService {

    void initCities();
    List<CityViewModel> getAllCities();
}
