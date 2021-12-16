package com.example.finalproject.web;

import com.example.finalproject.model.binding.AddComputerBindingModel;
import com.example.finalproject.model.view.ComputerOfferView;
import com.example.finalproject.model.view.LaptopOfferView;
import com.example.finalproject.service.ComputerService;
import com.example.finalproject.service.LaptopService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/offers")
public class OffersController {

    private ComputerService computerService;
    private LaptopService laptopService;

    public OffersController(ComputerService computerService, LaptopService laptopService) {

        this.computerService = computerService;
        this.laptopService = laptopService;
    }

    @GetMapping("/all")
    public String allOffers(Model model){
        List<ComputerOfferView> allComputers = computerService.getAllComputers();
        List<LaptopOfferView> allLaptops = laptopService.getAllLaptops();

        model.addAttribute("allComputers",allComputers);
        model.addAttribute("allLaptops",allLaptops);

        return "list-offers";
    }



}
