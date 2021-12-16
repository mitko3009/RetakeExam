package com.example.finalproject.service.impl;

import com.example.finalproject.model.entity.ComputerEntity;
import com.example.finalproject.model.entity.RoleEntity;
import com.example.finalproject.model.entity.UserEntity;
import com.example.finalproject.model.enums.Roles;
import com.example.finalproject.model.service.ComputerServiceModel;
import com.example.finalproject.model.service.ComputerUpdateServiceModel;
import com.example.finalproject.model.view.ComputerDetailsViewModel;
import com.example.finalproject.model.view.ComputerOfferView;
import com.example.finalproject.repository.ComputerRepository;
import com.example.finalproject.repository.UserRepository;
import com.example.finalproject.service.ComputerService;
import com.example.finalproject.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ComputerServiceImpl implements ComputerService {

    private ComputerRepository computerRepository;
    private ModelMapper modelMapper;
    private UserService userService;
    private UserRepository userRepository;

    public ComputerServiceImpl(ComputerRepository computerRepository, ModelMapper modelMapper,
                               UserService userService, UserRepository userRepository) {

        this.computerRepository = computerRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Override
    public void addComputer(ComputerServiceModel serviceModel,String username) {
        UserEntity user = userService.findUserByUsername(username);
        ComputerEntity entity = modelMapper.map(serviceModel, ComputerEntity.class);
        entity.setSeller(user);
        computerRepository.save(entity);
    }

    @Override
    public List<ComputerOfferView> getAllComputers() {

        List<ComputerEntity> all = computerRepository.findAll();
        List<ComputerOfferView> computerOfferViews = new ArrayList<>();

        for (ComputerEntity entity : all) {
            ComputerOfferView view = modelMapper.map(entity, ComputerOfferView.class);
            view.setSellerName(entity.getSeller().getName());
            computerOfferViews.add(view);
        }

        return computerOfferViews;
    }

    @Override
    public ComputerDetailsViewModel findComputerById(Long id) {
      return  modelMapper.map(computerRepository.findById(id).get(),ComputerDetailsViewModel.class);
    }

    @Override
    public void update(ComputerUpdateServiceModel serviceModel) {

        ComputerEntity entity = computerRepository.findById(serviceModel.getId()).get();

        entity.setCpu(serviceModel.getCpu());
        entity.setGpu(serviceModel.getGpu());
        entity.setRam(serviceModel.getRam());
        entity.setDiskSpace(serviceModel.getDiskSpace());
        entity.setPrice(serviceModel.getPrice());
        entity.setPowerSupply(serviceModel.getPowerSupply());
        entity.setMotherboard(serviceModel.getMotherboard());

        computerRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        computerRepository.delete(computerRepository.findById(id).get());
    }

    public boolean isOwner(String userName, Long id) {
        Optional<ComputerEntity> offerOpt = computerRepository.
                findById(id);
        Optional<UserEntity> caller = userRepository.
                findByUsername(userName);

        if (offerOpt.isEmpty() || caller.isEmpty()) {
            return false;
        } else {
            ComputerEntity offerEntity = offerOpt.get();

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
