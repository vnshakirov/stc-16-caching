package ru.inno.stc16.caching;


import org.apache.commons.jcs.JCS;
import org.apache.commons.jcs.access.CacheAccess;
import org.apache.commons.jcs.access.exception.CacheException;

import org.apache.commons.jcs.admin.CacheRegionInfo;
import org.apache.commons.jcs.admin.JCSAdminBean;
import org.apache.commons.jcs.engine.control.CompositeCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import ru.inno.stc16.caching.entity.Course;

import java.lang.reflect.Method;
import java.util.*;

@Repository
public class CourseRepository {

    private final Map<Integer, Course> courses = new HashMap<>();
    private CacheAccess<Integer, Course> cache = JCS.getInstance("default");

    public CourseRepository() throws CacheException {
    }

    //    @Cacheable(cacheNames = "courses")
    public Course getCourse(int id) throws CacheException {
        Course course = cache.get(id);
        if (course == null) {
            course = courses.get(id);
            cache.put(id, course);
            System.out.println("get course from storage");
        }
        return course;
    }

//    @CachePut("courses")
    public void addCourse(Course course) {
        System.out.println("add course to storage");
        courses.put(course.getId(), course);
    }

//    @CachePut("courses")
    public void updateCourse(Course course) {
        System.out.println("update course in storage");
        courses.put(course.getId(), course);
    }

//    @Cacheable(cacheNames = "courses")
    public Collection<Course> getCourses() throws CacheException {
        Set<Integer> ids = courses.keySet();
        Collection<Course> result = new LinkedList<>();
        for (Integer id : ids) {
            Course course = cache.get(id);
            if (course == null) {
                course = courses.get(id);
                System.out.println("Cache miss");
            }
            result.add(course);
        }
        return result;
    }


    public void writeStatistics() throws Exception {
        JCSAdminBean jcs = new JCSAdminBean();
        CacheRegionInfo[] infoItems = jcs.buildCacheInfo();
        for (CacheRegionInfo infoItem : infoItems) {
            System.out.println("Cache statistics: \n" + infoItem.getCacheStatistics());
        }
    }
}
