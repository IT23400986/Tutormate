package com.group13.tutormate.repository;

import com.group13.tutormate.entity.FileUploadEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileUploadRepository extends JpaRepository<FileUploadEntity, Long> {
}

