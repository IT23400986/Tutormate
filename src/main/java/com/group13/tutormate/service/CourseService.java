package com.group13.tutormate.service;


import com.group13.tutormate.entity.Course;
import com.group13.tutormate.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public void updateCourse(Course course) {
        courseRepository.save(course); // Save or update the course in the database
    }
}
