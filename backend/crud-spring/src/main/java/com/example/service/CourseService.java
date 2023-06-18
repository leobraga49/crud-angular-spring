package com.example.service;

import com.example.model.Course;
import com.example.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public List<Course> listAll() {
        return courseRepository.findAll();
    }

    public Course save(Course course) {
        return courseRepository.save(course);
    }

    public Course findById(Long id) {
        return courseRepository.findById(id).orElseThrow();
    }

    public Course update(Long id, Course course) {
        var courseToUpdate = findById(id);
        courseToUpdate.setName(course.getName());
        courseToUpdate.setCategory(course.getCategory());
        return courseRepository.save(courseToUpdate);
    }

    public void delete(Long id) {
        courseRepository.delete(findById(id));
    }
}
