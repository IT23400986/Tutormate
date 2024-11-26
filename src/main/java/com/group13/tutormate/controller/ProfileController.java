package com.group13.tutormate.controller;

import com.group13.tutormate.entity.User;
import com.group13.tutormate.security.CustomUserDetailsService;
import com.group13.tutormate.service.UserService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    private final UserService userService;
    @SuppressWarnings("unused")
    private final CustomUserDetailsService customUserDetailsService;

    public ProfileController(UserService userService, CustomUserDetailsService customUserDetailsService) {
        this.userService = userService;
        this.customUserDetailsService = customUserDetailsService;
    }

    // Handler method for showing the profile page
    @GetMapping("/student/profile")
    @PreAuthorize("hasRole('STUDENT')")
    public String viewProfileStudent(Model model) {
        // Get the current authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName(); // get the email of the logged-in user

        // Retrieve the user details from the database
        User user = userService.findByEmail(email);

        if (user != null) {
            // Add the user object to the model
            model.addAttribute("user", user);
        } else {
            model.addAttribute("error", "User not found!");
        }

        return "studentProfile"; // the name of the profile view (studentProfile.html)
    }

    @GetMapping("/teacher/profile")
    @PreAuthorize("hasRole('TEACHER')")
    public String viewProfileTeacher(Model model) {
        // Get the current authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName(); // get the email of the logged-in user

        // Retrieve the user details from the database
        User user = userService.findByEmail(email);

        if (user != null) {
            // Add the user object to the model
            model.addAttribute("user", user);
        } else {
            model.addAttribute("error", "User not found!");
        }

        return "teacherProfile"; // the name of the profile view (studentProfile.html)
    }

    @GetMapping("/admin/profile")
    @PreAuthorize("hasRole('ADMIN')")
    public String viewProfileAdmin(Model model) {
        // Get the current authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName(); // get the email of the logged-in user

        // Retrieve the user details from the database
        User user = userService.findByEmail(email);

        if (user != null) {
            // Add the user object to the model
            model.addAttribute("user", user);
        } else {
            model.addAttribute("error", "User not found!");
        }

        return "adminProfile"; // the name of the profile view (studentProfile.html)
    }

    @GetMapping("/finance/profile")
    @PreAuthorize("hasRole('FINANCE')")
    public String viewProfileFinance(Model model) {
        // Get the current authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName(); // get the email of the logged-in user

        // Retrieve the user details from the database
        User user = userService.findByEmail(email);

        if (user != null) {
            // Add the user object to the model
            model.addAttribute("user", user);
        } else {
            model.addAttribute("error", "User not found!");
        }

        return "financeProfile"; // the name of the profile view (studentProfile.html)
    }
}
