package com.example.finalproject.service.impl;

import com.example.finalproject.model.entity.ComputerEntity;
import com.example.finalproject.model.entity.LaptopEnity;
import com.example.finalproject.model.entity.RoleEntity;
import com.example.finalproject.model.entity.UserEntity;
import com.example.finalproject.model.enums.Roles;
import com.example.finalproject.model.service.LaptopServiceModel;
import com.example.finalproject.model.service.LaptopUpdateServiceModel;
import com.example.finalproject.model.view.LaptopDetailsViewModel;
import com.example.finalproject.model.view.LaptopOfferView;
import com.example.finalproject.repository.LaptopRepository;
import com.example.finalproject.repository.UserRepository;
import com.example.finalproject.service.LaptopService;
import com.example.finalproject.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LaptopServiceImpl implements LaptopService {

    private ModelMapper modelMapper;
    private LaptopRepository laptopRepository;
    private UserService userService;
    private UserRepository userRepository;

    public LaptopServiceImpl(ModelMapper modelMapper, LaptopRepository laptopRepository, UserService userService,
                             UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.laptopRepository = laptopRepository;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Override
    public void addLaptop(LaptopServiceModel laptopServiceModel,String username) {
        LaptopEnity entity = modelMapper.map(laptopServiceModel, LaptopEnity.class);
        entity.setSeller(userService.findUserByUsername(username));
        laptopRepository.save(entity);
    }

    @Override
    public List<LaptopOfferView> getAllLaptops() {
        List<LaptopEnity> laptopEnities = laptopRepository.findAll();
        List<LaptopOfferView> laptopOfferViews = new ArrayList<>();

        for (LaptopEnity enity : laptopEnities) {
            LaptopOfferView view = modelMapper.map(enity, LaptopOfferView.class);
            view.setSellerName(enity.getSeller().getName());
            laptopOfferViews.add(view);
        }

        return laptopOfferViews;


    }

    @Override
    public LaptopDetailsViewModel findLaptopById(Long id) {

        return modelMapper.map(laptopRepository.findById(id).get(),LaptopDetailsViewModel.class);
    }

    @Override
    public void update(LaptopUpdateServiceModel laptopUpdateServiceModel) {

        LaptopEnity enity = laptopRepository.findById(laptopUpdateServiceModel.getId()).get();

        enity.setCpu(laptopUpdateServiceModel.getCpu());
        enity.setGpu(laptopUpdateServiceModel.getGpu());
        enity.setRam(laptopUpdateServiceModel.getRam());
        enity.setDiskSpace(laptopUpdateServiceModel.getDiskSpace());
        enity.setPrice(laptopUpdateServiceModel.getPrice());

        laptopRepository.save(enity);
    }

    @Override
    public void delete(Long id) {
        laptopRepository.deleteById(id);
    }

    @Override
    public boolean isOwner(String userName, Long id) {
        Optional<LaptopEnity> offerOpt = laptopRepository.
                findById(id);
        Optional<UserEntity> caller = userRepository.
                findByUsername(userName);

        if (offerOpt.isEmpty() || caller.isEmpty()) {
            return false;
        } else {
            LaptopEnity offerEntity = offerOpt.get();

            return isAdmin(caller.get()) ||
                    offerEntity.getSeller().getUsername().equals(userName);
        }
    }

    private boolean isAdmin(UserEntity user) {
        return user.
                getRoles().
                stream().
                map(RoleEntity::getRole).
                anyMatch(r -> r == Roles.ADMIN);
    }
}
