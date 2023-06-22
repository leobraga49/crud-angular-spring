package com.example.service;

import com.example.dto.CourseDTO;
import com.example.dto.mapper.CourseMapper;
import com.example.exceptions.CourseNotFoundException;
import com.example.repository.CourseRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
@AllArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public List<CourseDTO> listAll() {
        return courseRepository.findAll()
                .stream()
                .map(courseMapper::toCourseDTO)
                .collect(Collectors.toList());
    }

    public CourseDTO findById(@NotNull @Positive Long id) {
        return courseRepository.findById(id).map(courseMapper::toCourseDTO)
                .orElseThrow(() -> new CourseNotFoundException("Course not found"));
    }

    public CourseDTO save(@Valid @NotNull CourseDTO course) {
        return courseMapper.toCourseDTO(courseRepository.save(courseMapper.toCourse(course)));
    }

    public CourseDTO update(@NotNull @Positive Long id, CourseDTO course) {
        return courseRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setName(course.name());
                    recordFound.setCategory(courseMapper.convertCategoryValue(course.category()));
                    return courseMapper.toCourseDTO(courseRepository.save(recordFound));
                }).orElseThrow(() -> new CourseNotFoundException("Course not found"));
    }

    public void delete(@NotNull @Positive Long id) {
        courseRepository.delete(courseRepository.findById(id).orElseThrow(
                () -> new CourseNotFoundException("Course not found")));
    }
}
