package com.example;

import com.example.enums.Category;
import com.example.model.Course;
import com.example.model.Lesson;
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

            var l = new Lesson();
            l.setName("Angular 10");
            l.setUrl("watch?123");
            l.setCourse(c);
            c.getLessons().add(l);

            courseRepository.save(c);
        };
    }

}
