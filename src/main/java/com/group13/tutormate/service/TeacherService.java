package com.group13.tutormate.service;


import com.group13.tutormate.entity.Teacher;
import com.group13.tutormate.entity.TransferredTeacher;
import com.group13.tutormate.repository.TeacherRepository;
import com.group13.tutormate.repository.TransferredTeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private TransferredTeacherRepository transferredTeacherRepository;

    // Get all teachers from the Teacher table
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    // Get all transferred teachers from the TQ table
    public List<TransferredTeacher> getAllTQTeachers() {
        return transferredTeacherRepository.findAll();
    }

    // Transfer teacher from Teacher table to TQ table
    public void transferTeacherToTQ(Integer id) {
        Optional<Teacher> teacherOpt = teacherRepository.findById(id);
        if (teacherOpt.isPresent()) {
            Teacher teacher = teacherOpt.get();
            TransferredTeacher tqTeacher = new TransferredTeacher();
            tqTeacher.setId(teacher.getId());
            tqTeacher.setName(teacher.getName());
            tqTeacher.setSubject(teacher.getSubject());

            // Save to TQ table
            transferredTeacherRepository.save(tqTeacher);
            // Delete from Teacher table
            teacherRepository.delete(teacher);
        }
    }

    // Undo transfer: Move a teacher from TQ table back to Teacher table
    public void undoTeacherTransfer(Integer id) {
        Optional<TransferredTeacher> tqTeacherOpt = transferredTeacherRepository.findById(id);
        if (tqTeacherOpt.isPresent()) {
            TransferredTeacher tqTeacher = tqTeacherOpt.get();
            Teacher teacher = new Teacher();
            teacher.setId(tqTeacher.getId());
            teacher.setName(tqTeacher.getName());
            teacher.setSubject(tqTeacher.getSubject());

            // Save to Teacher table
            teacherRepository.save(teacher);
            // Delete from TQ table
            transferredTeacherRepository.delete(tqTeacher);
        }
    }

    // Delete teacher from the Teacher table
    public void deleteTeacher(Integer id) {
        teacherRepository.deleteById(id);
    }
}
