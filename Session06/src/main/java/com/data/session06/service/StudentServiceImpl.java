package com.data.session06.service;

import com.data.session06.entity.Student;
import com.data.session06.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(String studentId, Student student) {
        Student existingStudent = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        existingStudent.setFullName(student.getFullName());
        existingStudent.setGender(student.getGender());
        existingStudent.setBirthday(student.getBirthday());
        existingStudent.setAddress(student.getAddress());
        existingStudent.setClassEntity(student.getClassEntity());
        return studentRepository.save(existingStudent);
    }

    @Override
    public void deleteStudent(String studentId) {
        studentRepository.deleteById(studentId);
    }

    @Override
    public Student getStudentById(String studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}