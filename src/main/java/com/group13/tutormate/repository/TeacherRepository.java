package com.group13.tutormate.repository;



import com.group13.tutormate.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Integer> {//class Product and primary key Integer


}
