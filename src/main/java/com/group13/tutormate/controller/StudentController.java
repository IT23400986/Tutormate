package com.group13.tutormate.controller;

import com.group13.tutormate.entity.Classes;
import com.group13.tutormate.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private ClassService classService; // Service to interact with the database

    @GetMapping("/classes")
    public String getClasses(Model model) {
        List<Classes> classes = classService.getAllClasses(); // Fetch classes from the database
        model.addAttribute("classes", classes); // Add classes to the model
        return "classes"; // Name of the HTML file
    }

    @GetMapping("/studentDashboard")
    public String getstudentDashboard() {
        return "studentDashboard";
    }

    @GetMapping("/deposits")
    public String showHelpPage() {
        // This will return the 'help.html' template
        return "bankInfo";}
    
}
