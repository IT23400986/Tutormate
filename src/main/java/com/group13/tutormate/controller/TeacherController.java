package com.group13.tutormate.controller;

import com.group13.tutormate.entity.Course;
import com.group13.tutormate.service.CourseService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teacher/")
public class TeacherController {

    @Autowired
    private CourseService courseService;

    // Redirecting from /teacher to /teacher/dashboard
    @GetMapping("")
    public String redirectToDashboard() {
        return "redirect:/teacher/dashboard";
    }

    // Handling dashboard view
    @GetMapping("/dashboard")
    public String teacherDashboard() {
        return "teacherDashboard"; // This should return Teacher.html in templates
    }

    @GetMapping("/classes")
    public String courseUpdateForm() {
        return "classes"; // This should return courseUpdate.html in templates
    }

    // Handling course update (courseUpdate.html)
    @GetMapping("/course-update")
    public String courseUpdateForm(@NotNull Model model) {
        model.addAttribute("course", new Course());
        return "courseUpdate"; // This should return courseUpdate.html in templates
    }

    // Handling course update POST
    @PostMapping("/course-update/update-course")
    public String updateCourse(@ModelAttribute Course course) {
        // Save or update the course without worrying about financeApproved
        courseService.updateCourse(course);

        return "redirect:/teacher/dashboard"; // After update, redirect to the dashboard
    }
}