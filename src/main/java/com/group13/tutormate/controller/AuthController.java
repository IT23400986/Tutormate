package com.group13.tutormate.controller;

import com.group13.tutormate.dto.UserDTO;
import com.group13.tutormate.entity.User;
import com.group13.tutormate.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("register")
    public String showRegistrationForm(Model model){
        UserDTO user = new UserDTO();
        model.addAttribute("user", user);
        return "register";
    }

    @SuppressWarnings("null")
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDTO user,
                               BindingResult result,
                               Model model) {
        User existing = userService.findByEmail(user.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }
        userService.saveUser(user);
        return "redirect:/register?success";
    }

    @GetMapping("/student")
    public String studentDashboard() {
        return "studentDashboard";
    }

    @GetMapping("/admin")
    public String adminDashboard() {
        return "adminDashboard";
    }

    @GetMapping("/teacher")
    public String teacherDashboard() {
        return "teacherDashboard";
    }

    @GetMapping("/finance")
    public String financeDashboard() {
        return "financeDashboard";
    }

    @GetMapping("/help")
    public String showHelpPage() {
        // This will return the 'help.html' template
        return "help";}
}
