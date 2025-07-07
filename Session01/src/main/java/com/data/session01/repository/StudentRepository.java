package com.data.session01.repository;

import com.data.session01.Entity.Student;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentRepository {
    private static List<Student> students = new ArrayList<>();

    static {
        students.add(new Student("1", "John Doe", true, new Date(), "123 Main St", "Class A"));
        students.add(new Student("2", "Jane Smith", false, new Date(), "456 Elm St", "Class B"));
        students.add(new Student("3", "Alice Johnson", false, new Date(), "789 Oak St", "Class C"));
    }

    public static List<Student> getStudents() {
        return students;
    }

    public static boolean addStudent(Student student) {
        return students.add(student);
    }

    public static boolean updateStudent(Student student) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(student.getId())) {
                students.set(i, student);
                return true;
            }
        }
        return false;
    }

    public static boolean deleteStudent(String id) {
        return students.removeIf(s -> s.getId().equals(id));
    }

    public static Student getStudentById(String id) {
        return students.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
