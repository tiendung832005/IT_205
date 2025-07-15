package com.data.session06.service;

import com.data.session06.entity.Class;
import com.data.session06.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    private ClassRepository classRepository;

    @Override
    public Class createClass(Class classEntity) {
        return classRepository.save(classEntity);
    }

    @Override
    public Class updateClass(String classId, Class classEntity) {
        Class existingClass = classRepository.findById(classId)
                .orElseThrow(() -> new RuntimeException("Class not found"));
        existingClass.setClassName(classEntity.getClassName());
        existingClass.setStatus(classEntity.getStatus());
        return classRepository.save(existingClass);
    }

    @Override
    public void deleteClass(String classId) {
        classRepository.deleteById(classId);
    }

    @Override
    public Class getClassById(String classId) {
        return classRepository.findById(classId)
                .orElseThrow(() -> new RuntimeException("Class not found"));
    }

    @Override
    public List<Class> getAllClasses() {
        return classRepository.findAll();
    }
}