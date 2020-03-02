package ru.inno.stc16.caching;

import net.sf.ehcache.Cache;
import net.sf.ehcache.statistics.StatisticsGateway;
import net.sf.ehcache.store.StoreOperationOutcomes;
import org.apache.commons.jcs.access.exception.CacheException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import ru.inno.stc16.caching.entity.Course;

import java.util.*;

@Repository
public class CourseRepository {

    private final Map<Integer, Course> courses = new HashMap<>();

    @Autowired
    private CacheManager cacheManager;

    public CourseRepository() throws CacheException {
    }

    @Cacheable(cacheNames = "courses")
    public Course getCourse(int id) throws CacheException {
        System.out.println("get course from storage");
        return courses.get(id);
    }

    @CachePut(value = "courses", key = "#course.id")
    public Course addCourse(Course course) {
        System.out.println("add course to storage");
        courses.put(course.getId(), course);
        return course;
    }

    @CachePut("courses")
    public void updateCourse(Course course) {
        System.out.println("update course in storage");
        courses.put(course.getId(), course);
    }

    public Collection<Course> getCourses() throws CacheException {
        System.out.println("get all courses from storage");
        return courses.values();
    }

    public void writeStatistics() throws Exception {
        Cache cache = (Cache)cacheManager.getCache("courses").getNativeCache();
        StatisticsGateway statistics = cache.getStatistics();
        System.out.println("HIT: " + statistics.getCore().localHeapGet().value(StoreOperationOutcomes.GetOutcome.HIT));
        System.out.println("MISS: " + statistics.getCore().localHeapGet().value(StoreOperationOutcomes.GetOutcome.MISS));
    }
}