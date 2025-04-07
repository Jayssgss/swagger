package com.example.swaggerjenkins.service.impl;

import com.example.swaggerjenkins.entity.Course;
import com.example.swaggerjenkins.repo.CourseRepo;
import com.example.swaggerjenkins.request.CourseRequest;
import com.example.swaggerjenkins.service.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoursesServiceImpl implements CoursesService {

    @Autowired
    private CourseRepo courseRepo;


    @Override
    public List<Course> getAllCourses() {
        return courseRepo.findAll();
    }

    @Override
    public Course getCourseById(Long id) {
        Optional<Course> course = courseRepo.findById(id);
        return course.orElse(null);
    }

    @Override
    public Course createCourse(CourseRequest course) {
        Course newCourse = mapToCourseEntity(course);
        return courseRepo.save(newCourse);
    }

    @Override
    public Course updateCourse(CourseRequest course) {
        Optional<Course> courseOptional = courseRepo.findById(course.getId());
        if(courseOptional.isPresent()) {
            Course courseToUpdate = mapToCourseEntity(course);
            return courseRepo.save(courseToUpdate);
        }
        return courseOptional.orElse(null);
    }

    @Override
    public boolean deleteCourse(Long id) {
        if (courseRepo.findById(id).isEmpty()) {
            return false;
        }
        try {
            courseRepo.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static Course mapToCourseEntity(CourseRequest courseRequest){
        return Course.builder()
                .id(courseRequest.getId())
                .name(courseRequest.getName())
                .description(courseRequest.getDescription())
                .build();
    }
}
