package com.example.finalproject.web;

import com.example.finalproject.model.binding.AddLaptopBindingModel;
import com.example.finalproject.model.binding.ComputerUpdateBindingModel;
import com.example.finalproject.model.binding.LaptopUpdateBindingModel;
import com.example.finalproject.model.service.ComputerUpdateServiceModel;
import com.example.finalproject.model.service.LaptopServiceModel;
import com.example.finalproject.model.service.LaptopUpdateServiceModel;
import com.example.finalproject.model.view.ComputerDetailsViewModel;
import com.example.finalproject.model.view.LaptopDetailsViewModel;
import com.example.finalproject.service.CityService;
import com.example.finalproject.service.LaptopService;
import com.example.finalproject.service.impl.ProjectUser;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class LaptopController {

    private LaptopService laptopService;
    private CityService cityService;
    private ModelMapper modelMapper;

    public LaptopController(ModelMapper modelMapper, LaptopService laptopService,
                            CityService cityService) {
        this.modelMapper = modelMapper;
        this.laptopService = laptopService;

        this.cityService = cityService;
    }

    @GetMapping("/offers/add/laptop")
    public String addLaptop(Model model){

        if (!model.containsAttribute("offerAddBindModel")) {
            model.addAttribute("laptopAddBindingModel", new AddLaptopBindingModel());
            model.addAttribute("cities",cityService.getAllCities());
        }

        return "add-laptop";
    }

    @PostMapping("/laptops/add")
    public String addLaptop(@Valid AddLaptopBindingModel addLaptopBindingModel,
                            BindingResult bindingResult, RedirectAttributes redirectAttributes,
                            @AuthenticationPrincipal ProjectUser user){

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("addLaptopBindingModel",addLaptopBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addLaptopBindingModel",bindingResult);

            return "redirect:/offers/add/laptop";
        }

        laptopService.addLaptop(modelMapper.map(addLaptopBindingModel, LaptopServiceModel.class),user.getUserIdentifier());

        return "redirect:/offers/allLaptops";
    }

    @GetMapping("/offers/allLaptops")
    public String allLaptops(Model model){
        model.addAttribute("laptops",laptopService.getAllLaptops());
        return "list-laptops-all";
    }

    @GetMapping("/offers/laptops/{id}/details")
    public String laptopsDetails(@PathVariable Long id, Model model){
        LaptopDetailsViewModel laptopById = laptopService.findLaptopById(id);
        model.addAttribute("laptopDetails",laptopById);
        return "laptops-details";
    }

    @GetMapping("/offers/laptops/{id}/edit")
    public String editOffer(@PathVariable Long id, Model model) {

        LaptopDetailsViewModel laptop = laptopService.findLaptopById(id);
        model.addAttribute("laptopModel", modelMapper.map(laptop, LaptopUpdateBindingModel.class));

        return "laptop-update";
    }

    @PatchMapping("/offers/laptops/{id}/edit")
    public String editOffer(
            @PathVariable Long id,
            @Valid LaptopUpdateBindingModel laptopUpdateBindingModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("offerModel", laptopUpdateBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offerModel", bindingResult);

            return "redirect:/offers/allLaptops";
        }

        LaptopUpdateServiceModel serviceModel = modelMapper.map(laptopUpdateBindingModel, LaptopUpdateServiceModel.class);
        serviceModel.setId(id);

        laptopService.update(serviceModel);

        return "redirect:/offers/laptops/" + id + "/details";
    }

    // DELETE
    @PreAuthorize("isOwner(#id)")
    //@PreAuthorize("@offerServiceImpl.isOwner(#principal.name, #id)")
    @GetMapping("/offers/laptops/{id}/delete")
    public String delete(@PathVariable Long id, Principal principal){

        if (!laptopService.isOwner(principal.getName(), id)) {
            throw new RuntimeException();
        }
        laptopService.delete(id);

        return "redirect:/offers/allLaptops";

    }
}
