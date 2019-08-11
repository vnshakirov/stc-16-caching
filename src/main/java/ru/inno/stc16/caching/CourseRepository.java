package ru.inno.stc16.caching;

import org.apache.commons.jcs.access.exception.CacheException;

import org.springframework.stereotype.Repository;
import ru.inno.stc16.caching.entity.Course;

import java.util.*;

@Repository
public class CourseRepository {

    private final Map<Integer, Course> courses = new HashMap<>();

    public CourseRepository() throws CacheException {
    }

    public Course getCourse(int id) throws CacheException {
        System.out.println("get course from storage");
        return courses.get(id);
    }

    public void addCourse(Course course) {
        System.out.println("add course to storage");
        courses.put(course.getId(), course);
    }

    public void updateCourse(Course course) {
        System.out.println("update course in storage");
        courses.put(course.getId(), course);
    }

    public Collection<Course> getCourses() throws CacheException {
        System.out.println("get all courses from storage");
        return courses.values();
    }

}
