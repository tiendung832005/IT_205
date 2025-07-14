package com.data.session05.service;

import com.data.session05.entity.Student;
import com.data.session05.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Student insertStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student student) {
        return studentRepository.findById(id)
                .map(existingStudent -> {
                    existingStudent.setFullName(student.getFullName());
                    existingStudent.setGender(student.getGender());
                    existingStudent.setBirthday(student.getBirthday());
                    existingStudent.setAddress(student.getAddress());
                    existingStudent.setClassName(student.getClassName());
                    return studentRepository.save(existingStudent);
                }).orElseThrow(() -> new RuntimeException("Student not found"));
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public List<Student> getStudentsByName(String name) {
        return studentRepository.findByFullNameContaining(name);
    }

    public List<Student> getStudentsByAddress(String address) {
        return studentRepository.findByAddressContaining(address);
    }

    public List<Student> getStudentsByClassName(String className) {
        return studentRepository.findByClassName(className);
    }
}
