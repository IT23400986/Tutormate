package com.group13.tutormate.repository;



import com.group13.tutormate.entity.TransferredTeacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferredTeacherRepository extends JpaRepository<TransferredTeacher,Integer> {//class Product and primary key Integer


}
