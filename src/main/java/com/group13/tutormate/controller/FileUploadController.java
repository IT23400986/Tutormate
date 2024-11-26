package com.group13.tutormate.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.group13.tutormate.service.FileUploadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/student")
public class FileUploadController {

    private final FileUploadService fileUploadService;

    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    private static final String UPLOAD_DIR = "uploads/";

    public FileUploadController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    @GetMapping("/payment-proof")
    public String uploadPaymentProofPage() {
        return "paymentProof"; // Name of the HTML file (without .html)
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> uploadFile(@RequestParam("paymentProof") MultipartFile file) throws IOException {

        // Handles case when file is empty
        if (file.isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is empty.");

        String uploadFile = fileUploadService.uploadFile(file);

        // Validate file type
        String contentType = file.getContentType();
        if (!"image/jpeg".equals(contentType) && !"image/png".equals(contentType) && !"application/pdf".equals(contentType)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid file type. Only JPEG, PNG, and PDF files are allowed.");
        }

        // Validate file < size 2 MB
        if (file.getSize() > 2 * 1024 * 1024) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File size exceeds 2 MB.");
        }

        // Save file
        try {
            File uploadDir = new File(UPLOAD_DIR);
            // Create directory if it does not exist
            if (!uploadDir.exists()) {
                if (uploadDir.mkdirs()) {
                    System.out.println("Directories created successfully.");
                } else throw new IOException("Failed to create directories for uploadDir.");
            }

            // Save the file to the uploads directory
            String filePath = UPLOAD_DIR + file.getOriginalFilename();
            File destinationFile = new File(filePath);
            file.transferTo(destinationFile);

            return ResponseEntity.ok("File uploaded successfully to: " + destinationFile.getAbsolutePath());
        } catch (IOException e) {
            logger.error("Error while saving the file", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while saving the file.");
        }
    }
}


