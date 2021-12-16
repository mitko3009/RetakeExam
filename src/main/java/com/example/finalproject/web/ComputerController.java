package com.example.finalproject.web;

import com.example.finalproject.model.binding.AddComputerBindingModel;
import com.example.finalproject.model.binding.ComputerUpdateBindingModel;
import com.example.finalproject.model.service.ComputerServiceModel;
import com.example.finalproject.model.service.ComputerUpdateServiceModel;
import com.example.finalproject.model.view.ComputerDetailsViewModel;
import com.example.finalproject.service.CityService;
import com.example.finalproject.service.ComputerService;
import com.example.finalproject.service.impl.ProjectUser;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class ComputerController {

    private ModelMapper modelMapper;
    private ComputerService computerService;
    private CityService cityService;

    public ComputerController(ModelMapper modelMapper, ComputerService computerService,
                              CityService cityService) {

        this.modelMapper = modelMapper;
        this.computerService = computerService;
        this.cityService = cityService;
    }

    @GetMapping("/offers/add/computer")
    public String addComputer(Model model) {
        if (!model.containsAttribute("offerAddBindModel")) {
            model.addAttribute("computerAddBindingModel", new AddComputerBindingModel());
            model.addAttribute("cities",cityService.getAllCities());
        }


        return "add-computer";
    }

    @PostMapping("/computer/add")
    public String addComputer(@Valid AddComputerBindingModel addComputerBindingModel,
                              BindingResult bindingResult, RedirectAttributes redirectAttributes,
                              @AuthenticationPrincipal ProjectUser user) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addComputerBindingModel", addComputerBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addComputerBindingModel", bindingResult);

            return "redirect:/offers/add/computer";
        }

        computerService.addComputer(modelMapper.map(addComputerBindingModel, ComputerServiceModel.class), user.getUserIdentifier());

        return "redirect:/offers/allComputers";

    }

    @GetMapping("/offers/allComputers")
    public String allComputers(Model model) {
        model.addAttribute("computers", computerService.getAllComputers());
        return "list-computers-all";
    }

    @GetMapping("/offers/computers/{id}/details")
    public String computerDetails(@PathVariable Long id, Model model) {
        ComputerDetailsViewModel userById = computerService.findComputerById(id);
        model.addAttribute("computerDetails", userById);
        return "computer-details";
    }

    @GetMapping("/offers/computers/{id}/edit")
    public String editOffer(@PathVariable Long id, Model model) {

        ComputerDetailsViewModel computerById = computerService.findComputerById(id);
        model.addAttribute("computerModel", modelMapper.map(computerById, ComputerUpdateBindingModel.class));

        return "computer-update";
    }

    @PatchMapping("/offers/computers/{id}/edit")
    public String editOffer(
            @PathVariable Long id,
            @Valid ComputerUpdateBindingModel computerUpdateBindingModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("offerModel", computerUpdateBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offerModel", bindingResult);

            return "redirect:/offers/allComputers";
        }

        ComputerUpdateServiceModel serviceModel = modelMapper.map(computerUpdateBindingModel, ComputerUpdateServiceModel.class);
        serviceModel.setId(id);

        computerService.update(serviceModel);

        return "redirect:/offers/computers/" + id + "/details";
    }

    // DELETE
    @PreAuthorize("isOwner(#id)")
    //@PreAuthorize("@offerServiceImpl.isOwner(#principal.name, #id)")
    @GetMapping("/offers/computers/{id}")
    public String delete(@PathVariable Long id, Principal principal){

        if (!computerService.isOwner(principal.getName(), id)) {
           throw new RuntimeException();
        }
        computerService.delete(id);

        return "redirect:/offers/allComputers";

    }







}
