package com.group13.tutormate.controller;

import com.group13.tutormate.entity.Teacher;
import com.group13.tutormate.entity.TransferredTeacher;
import com.group13.tutormate.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AdminController {

    private final TeacherService teacherService;

    public AdminController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/dashboard")
    public String adminDashboard() {
        return "adminDashboard";
    }

    @GetMapping("/admin/list")
    public String getTeacherList(Model model) {
        List<Teacher> teachers = teacherService.getAllTeachers();
        List<TransferredTeacher> transferredTeacherList = teacherService.getAllTQTeachers();
        model.addAttribute("teachers", teachers);
        model.addAttribute("tqList", transferredTeacherList);
        return "teacherList"; // Mapping to the new HTML page
    }

    @PostMapping("/admin/list/{id}")
    public String transferTeacher(@PathVariable Integer id) {
        teacherService.transferTeacherToTQ(id);
        return "redirect:/teacherList"; // Redirect to prevent form resubmission
    }

    @PostMapping("/admin/list/deleteTeacher/{id}")
    public String deleteTeacher(@PathVariable Integer id) {
        teacherService.deleteTeacher(id);
        return "redirect:/teacherList"; // Redirect to refresh the page after delete
    }

    @PostMapping("/admin/list/undoTransfer/{id}")
    public String undoTransfer(@PathVariable Integer id) {
        teacherService.undoTeacherTransfer(id);
        return "redirect:/teacherList"; // Redirect to refresh the page after undo transfer
    }
}
