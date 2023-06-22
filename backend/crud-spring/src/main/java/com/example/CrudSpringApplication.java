package com.example;

import com.example.enums.Category;
import com.example.model.Course;
import com.example.repository.CourseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrudSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudSpringApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(CourseRepository courseRepository) {
        return args -> {
            courseRepository.deleteAll();
            var c = new Course();
            c.setName("Angular with Spring Boot");
            c.setCategory(Category.FRONTEND);
            courseRepository.save(c);
        };
    }

}
