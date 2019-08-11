package ru.inno.stc16.caching.controllers;

import org.apache.commons.jcs.access.exception.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.inno.stc16.caching.CourseRepository;
import ru.inno.stc16.caching.entity.Course;

import java.util.Collection;

@RestController
public class CoursesController {

    @Autowired
    CourseRepository repository;

    @GetMapping("/courses")
    public Collection<Course> getAll() throws CacheException {
        return repository.getCourses();
    }

    @GetMapping("/courses/{id}")
    public Course getById(@PathVariable("id") Integer id) throws CacheException {
        return repository.getCourse(id);
    }

    @GetMapping
    public void getStats() throws Exception {
        repository.writeStatistics();
    }
}