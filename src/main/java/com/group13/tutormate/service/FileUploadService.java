package com.group13.tutormate.service;

import com.group13.tutormate.entity.FileUploadEntity;
import com.group13.tutormate.repository.FileUploadRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.group13.tutormate.util.FileUtil;

import java.io.IOException;
import java.util.List;

@Service
public class FileUploadService {


    private final FileUploadRepository fileUploadRepository; // Repository to handle file upload data

    public FileUploadService(FileUploadRepository fileUploadRepository) {
        this.fileUploadRepository = fileUploadRepository;
    }

    // upload file
    public String uploadFile(MultipartFile file) throws IOException {
        FileUploadEntity fileUpload = fileUploadRepository.save(FileUploadEntity.builder()
                .filename(file.getOriginalFilename())
                .fileUpload(FileUtil.compressFile(file.getBytes())).build());
        return "file uploaded successfully : " + file.getOriginalFilename();
    }
    // Get all uploaded files
    public List<FileUploadEntity> getAllFiles() {
        return fileUploadRepository.findAll();
    }

    // Update file status
    public boolean updateFileStatus(Long fileId, String status) {
        FileUploadEntity file = fileUploadRepository.findById(fileId).orElse(null);
        if (file != null) {
            fileUploadRepository.save(file);
            return true;
        }
        return false;
    }
}

