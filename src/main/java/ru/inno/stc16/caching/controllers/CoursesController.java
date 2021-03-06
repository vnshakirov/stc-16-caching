package ru.inno.stc16.caching.controllers;

import org.apache.commons.jcs.access.exception.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.inno.stc16.caching.CourseRepository;
import ru.inno.stc16.caching.entity.Course;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/courses")
public class CoursesController {

    @Autowired
    CourseRepository repository;

    @GetMapping
    public Collection<Course> getAll() throws CacheException {
        return repository.getCourses();
    }

    @GetMapping("/{id}")
    public Course getById(@PathVariable("id") Integer id) throws CacheException {
        return repository.getCourse(id);
    }

    @PostMapping
    public void addCourse(@RequestBody Course course) {
        repository.addCourse(course);
    }
}
