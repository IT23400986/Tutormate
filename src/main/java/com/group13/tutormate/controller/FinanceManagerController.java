package com.group13.tutormate.controller;

import com.group13.tutormate.service.FileUploadService;
import com.group13.tutormate.entity.FileUploadEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/finance")
public class FinanceManagerController {

    private final FileUploadService fileUploadService;

    public FinanceManagerController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }



    @GetMapping("/updateStatusPage")
    public String showUpdateStatusPage() {
        return "update-status";
    }

    // Get all uploaded files and their status
    @GetMapping("/submissions")
    public List<FileUploadEntity> getAllUploadedFiles() {
        return fileUploadService.getAllFiles();
    }

    // Update the status of uploaded file
    @PutMapping("/submissions/{fileId}")
    public ResponseEntity<String> updateFileStatus(@PathVariable Long fileId, @RequestParam String status) {
        boolean updated = fileUploadService.updateFileStatus(fileId, status);
        if (updated) {
            return ResponseEntity.ok("File status updated successfully.");
        } else {
            return ResponseEntity.badRequest().body("Error updating file status.");
        }
    }

    @GetMapping("/dashboard")
    public String financeDashboard() {
        return "financeDashboard";
    }


}

