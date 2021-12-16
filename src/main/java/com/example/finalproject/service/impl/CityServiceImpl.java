package com.example.finalproject.service.impl;

import com.example.finalproject.model.entity.CityEntity;
import com.example.finalproject.model.view.CityViewModel;
import com.example.finalproject.repository.CityRepository;
import com.example.finalproject.service.CityService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    private CityRepository cityRepository;
    private ModelMapper modelMapper;

    public CityServiceImpl(CityRepository cityRepository, ModelMapper modelMapper) {

        this.cityRepository = cityRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void initCities() {

        if (cityRepository.findAll().isEmpty()) {
            CityEntity sofia = new CityEntity();
            sofia.setName("Sofia");
            sofia.setRegion("Sofia");

            CityEntity plovdiv = new CityEntity();
            plovdiv.setName("Plovdiv");
            plovdiv.setRegion("Plovdiv");

            CityEntity kazanlak = new CityEntity();
            kazanlak.setName("Kazanlak");
            kazanlak.setRegion("Stara Zagora");

            CityEntity burgas = new CityEntity();
            burgas.setName("Burgas");
            burgas.setRegion("Burgas");

            CityEntity ruse = new CityEntity();
            ruse.setName("Ruse");
            ruse.setRegion("Ruse");

            cityRepository.saveAll(List.of(sofia, plovdiv, kazanlak, burgas, ruse));
        }
    }

    @Override
    public List<CityViewModel> getAllCities() {
        List<CityEntity> all = cityRepository.findAll();
        List<CityViewModel> cityViewModels = new ArrayList<>();

        for (CityEntity city : all) {
            cityViewModels.add(modelMapper.map(city,CityViewModel.class));
        }

        return cityViewModels;
    }
}
