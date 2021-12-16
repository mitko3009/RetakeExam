package com.example.finalproject.web;

import com.example.finalproject.model.binding.UserLoginBindingModel;
import com.example.finalproject.model.binding.UserRegistrationBindingModel;
import com.example.finalproject.model.service.UserRegisterServiceModel;
import com.example.finalproject.model.view.UserViewModel;
import com.example.finalproject.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private ModelMapper modelMapper;
    private UserService userService;

    public UserController(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String registration() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid UserRegistrationBindingModel bindingModel,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !bindingModel.getPassword().equals(bindingModel.getConfirmPassword())
        || !userService.loginAndRegisterUser(modelMapper.map(bindingModel,UserRegisterServiceModel.class))) {
            redirectAttributes.addFlashAttribute("bindingModel", bindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userModel", bindingResult);

            return "redirect:/users/register";
        }

        UserRegisterServiceModel model = modelMapper.map(bindingModel, UserRegisterServiceModel.class);

        userService.loginAndRegisterUser(model);

        return "redirect:/home";

    }

    @PostMapping("/login-error")
    public String failedLogin(
            @ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                    String userName,
            RedirectAttributes attributes
    ) {

        attributes.addFlashAttribute("bad_credentials", true);
        attributes.addFlashAttribute("username", userName);

        return "redirect:/users/login";
    }

    @GetMapping("/allUsers")
    public String allUsers(Model model) {
        List<UserViewModel> allUsers = userService.getAllUsers();

        model.addAttribute("allUsers", allUsers);

        return "allUsers";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){

        userService.deleteUser(id);

        return "redirect:/users/allUsers";

    }

    @ModelAttribute("bindingModel")
    public UserRegistrationBindingModel userModel() {
        return new UserRegistrationBindingModel();
    }

    @ModelAttribute("loginBindingModel")
    public UserLoginBindingModel userLoginModel() {
        return new UserLoginBindingModel();
    }
}
