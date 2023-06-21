package com.example.dto.mapper;

import com.example.dto.CourseDTO;
import com.example.model.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public CourseDTO toCourseDTO(Course course) {
        if (course == null){
            return null;
        }
        return new CourseDTO(
                course.getId(),
                course.getName(),
                course.getCategory()
        );
    }

    public Course toCourse(CourseDTO courseDTO) {
        if (courseDTO == null){
            return null;
        }
        var course = new Course();
        if (courseDTO.id() != null) {
            course.setId(courseDTO.id());
        }
        course.setName(courseDTO.name());
        course.setCategory(courseDTO.category());
        return course;
    }
}
