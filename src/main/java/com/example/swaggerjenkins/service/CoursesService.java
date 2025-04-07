package com.example.swaggerjenkins.service;


import com.example.swaggerjenkins.entity.Course;
import com.example.swaggerjenkins.request.CourseRequest;

import java.util.List;

public interface CoursesService {

    public List<Course> getAllCourses();
    public Course getCourseById(Long id);
    public Course createCourse(CourseRequest course);
    public Course updateCourse(CourseRequest course);
    public boolean deleteCourse(Long id);
}
