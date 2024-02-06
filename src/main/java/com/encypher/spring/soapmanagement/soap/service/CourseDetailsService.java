package com.encypher.spring.soapmanagement.soap.service;

import com.encypher.spring.soapmanagement.soap.Course;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CourseDetailsService {

    private static List<Course> courses = new ArrayList<>();

    static {
        Course cs1 = new Course(1, "Theory of Computation", "Theory of computation is focused on answering fundamental questions about what can be computed and what amount of resources are required to perform those computations.");
        courses.add(cs1);

        Course cs2 = new Course(2, "Information & coding theory", "Coding theory is the study of the properties of codes (systems for converting information from one form to another) and their fitness for a specific application.");
        courses.add(cs2);

        Course cs3 = new Course(3, "Data Structure & Algorithms", "Data structures and algorithms are the studies of commonly used computational methods and their computational efficiency.");
        courses.add(cs3);

        Course cs4 = new Course(4, "Programming language theory and formal methods", "Programming language theory is a branch of computer science that deals with the design, implementation, analysis, characterization, and classification of programming languages and their individual features.");
        courses.add(cs4);
    }

    // course - 1
   public Course findById(int id) {
        for(Course course : courses) {
            if(course.getId() == id) {
                return course;
            }
        }
        return null;
    }

    // courses
    public List<Course> findAll() {
        return courses;
    }

    // delete course
    public int deleteById(int id) {

        // for ... each throws a concurrentModificationException
        Iterator<Course> iterator = courses.iterator();
        while(iterator.hasNext()) {
            Course course = iterator.next();
            if(course.getId() == id) {
                iterator.remove();
                return 1;
            }
        }
        return 0;
    }

    // updating course & new course



}
