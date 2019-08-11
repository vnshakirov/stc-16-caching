package ru.inno.stc16.caching;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import ru.inno.stc16.caching.entity.Course;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class CachingApplication {

    @Autowired
    private CourseRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(CachingApplication.class, args);
    }

    @PostConstruct
    public void init() {
        repository.addCourse(new Course("Air Guitar"));
        repository.addCourse(new Course("Pinball Mastery"));
    }

}
