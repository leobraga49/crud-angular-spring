package com.example.service;

import com.example.exceptions.CourseNotFoundException;
import com.example.model.Course;
import com.example.repository.CourseRepository;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
@Validated
@AllArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public List<Course> listAll() {
        return courseRepository.findAll();
    }

    public Course save(Course course) {
        return courseRepository.save(course);
    }

    public Course findById(@PathVariable @NotNull @Positive Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course not found"));
    }

    public Course update(@NotNull @Positive Long id, Course course) {
        return courseRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setName(course.getName());
                    recordFound.setCategory(course.getCategory());
                    return courseRepository.save(recordFound);
                }).orElseThrow(() -> new CourseNotFoundException("Course not found"));
    }

    public void delete(@PathVariable @NotNull @Positive Long id) {
        courseRepository.delete(courseRepository.findById(id).orElseThrow(
                () -> new CourseNotFoundException("Course not found")));
    }
}
