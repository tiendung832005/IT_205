package com.data.session06.service;

import com.data.session06.entity.Class;

import java.util.List;

public interface ClassService {
    Class createClass(Class classEntity);
    Class updateClass(String classId, Class classEntity);
    void deleteClass(String classId);
    Class getClassById(String classId);
    List<Class> getAllClasses();
}