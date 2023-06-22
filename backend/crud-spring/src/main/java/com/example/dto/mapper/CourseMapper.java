package com.example.dto.mapper;

import com.example.dto.CourseDTO;
import com.example.enums.Category;
import com.example.model.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public CourseDTO toCourseDTO(Course course) {
        if (course == null) {
            return null;
        }
        return new CourseDTO(
                course.getId(),
                course.getName(),
                course.getCategory().getValue()
        );
    }

    public Course toCourse(CourseDTO courseDTO) {
        if (courseDTO == null) {
            return null;
        }
        var course = new Course();
        if (courseDTO.id() != null) {
            course.setId(courseDTO.id());
        }
        course.setName(courseDTO.name());
        course.setCategory(convertCategoryValue(courseDTO.category()));
        return course;
    }

    public Category convertCategoryValue(String value) {
        if (value == null) {
            return null;
        }
        return switch (value) {
            case "Back-end" -> Category.BACKEND;
            case "Front-end" -> Category.FRONTEND;
            default -> throw new IllegalArgumentException("Unknown value: " + value);
        };
    }
}
