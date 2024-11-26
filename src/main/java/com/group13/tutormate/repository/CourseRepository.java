package com.group13.tutormate.repository;

import com.group13.tutormate.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CourseRepository extends JpaRepository<Course, Long> {
}
