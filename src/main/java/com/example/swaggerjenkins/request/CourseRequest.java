package com.example.swaggerjenkins.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Schema(description = "Course details")
@AllArgsConstructor
public class CourseRequest {
    @Schema(description = "Unique identifier of the course", example = "101", required = true)
    private Long id;

    @Schema(description = "Name of the course", example = "Java for Beginners", required = true)
    private String name;

    @Schema(description = "Description of the course", example = "Learn Java from scratch", required = true)
    private String description;
}
