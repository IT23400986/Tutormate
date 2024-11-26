package com.group13.tutormate.service;

import com.group13.tutormate.entity.Classes;
import com.group13.tutormate.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassService {
    @Autowired
    private ClassRepository classRepository; // Repository for database interaction

    public List<Classes> getAllClasses() {
        return classRepository.findAll();
    }
}