package com.example.swaggerjenkins.controller;

import com.example.swaggerjenkins.entity.Course;
import com.example.swaggerjenkins.request.CourseRequest;
import com.example.swaggerjenkins.service.CoursesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
@Tag(name = "Courses",
        description = "APIs for managing courses, including retrieval, creation, updating, and deletion of course data.")
public class CoursesController {

    @Autowired
    private CoursesService coursesService;

    // GET LIST OF ALL THE COURSES
    @GetMapping
    @Operation(
            summary = "Retrieve all available courses",
            description = "Fetches a list of all courses, including their unique IDs, names, and descriptions."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Fetched All Available Courses"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok(coursesService.getAllCourses());
    }

    // GETS A COURSE BY FILTERING IT BASED ON ID
    @GetMapping("/{id}")
    @Operation(
            summary = "Get course by id",
            description = "Retrieve details of a course using its unique ID"
    )
    public ResponseEntity<Course> getCourseById(
            @Parameter(description = "ID of the course",
                    required = true,
                    example = "1")
            @PathVariable Long id) {
        Course course = coursesService.getCourseById(id);
        if (course != null) {
            return ResponseEntity.ok(course);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // CREATE/SAVE A NEW COURSE
    @PostMapping
    @Operation(summary = "Add a new course",
            description = "Creates a new course by accepting the course details.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Course created successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Course> createCourse(
            @Parameter(description = "Course details for creation of a new course.", required = true)
            @RequestBody CourseRequest course) {
        Course savedCourse = coursesService.createCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCourse);
    }

    // UPDATE A COURSE
    @PatchMapping
    @Operation(
            summary = "Update an existing course",
            description = "Modifies the details of an existing course identified by its unique ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Course updated successfully"),
            @ApiResponse(responseCode = "404", description = "Course not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Course> updateCourse(
            @Parameter(description = "Course details for updating an existing course.", required = true)
            @RequestBody CourseRequest course) {
        Course updatedCourse = coursesService.updateCourse(course);
        if (updatedCourse != null) {
            return ResponseEntity.ok(updatedCourse);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // DELETE A COURSE
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an existing course", description = "Deletes a course")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Course deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Course not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Void> deleteCourse(
            @Parameter(description = "Course details for deletion an existing course.",
                    example = "1",
                    required = true)
            @PathVariable Long id) {
        boolean isDeleted = coursesService.deleteCourse(id);
        if (isDeleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}