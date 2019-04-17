package com.example.navigationtest.Databases;

import com.example.navigationtest.DataTypes.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class CourseDatabase {

    // The list of courses.
    private static List<Course> courses = new ArrayList<Course>() {
        {
            add(new Course(
                    "CSCI",
                    1913,
                    "Introduction to Algorithms and Data Structures"
            ));
            add(new Course(
                    "CSCI",
                    5115,
                    "Interface Design"
            ));
            add(new Course(
                    "STAT",
                    3021,
                    "Introduction to Probability and Statistics"
            ));
            add(new Course(
                    "CSCI",
                    4707,
                    "Introduction to Database Systems"
            ));
            add(new Course(
                    "CSCI",
                    4611,
                    "Introduction to Graphics Programming"
            ));
            add(new Course(
                    "CSCI",
                    1933,
                    "Introduction to Algorithms and Data Structures"
            ));
            add(new Course(
                    "CSCI",
                    5802,
                    "Software Engineering II"
            ));
            add(new Course(
                    "CSCI",
                    4611,
                    "Programming Graphics and Games"
            ));
            add(new Course(
                    "CSCI",
                    5611,
                    "Animation and Planning in Games"
            ));
            add(new Course(
                    "CSCI",
                    5609,
                    "Visualization"
            ));
            add(new Course(
                    "CSCI",
                    5607,
                    "Graphics"
            ));
            add(new Course(
                    "CSCI",
                    2021,
                    "Machine Architecture"
            ));
            add(new Course(
                    "CSCI",
                    4061,
                    "Introduction to Operating Systems"
            ));
            add(new Course(
                    "CSCI",
                    2041,
                    "Functional Programming"
            ));
            add(new Course(
                    "MATH",
                    4707,
                    "Introduction to Combinatorics"
            ));
            add(new Course(
                    "CSCI",
                    4011,
                    "Finite Automata"
            ));
            add(new Course(
                    "CSCI",
                    2033,
                    "Linear Algebra"
            ));
        }
    };

    public static Course getCourse(String department, int number) {
        for (Course course : courses) {
            if (course.getDepartment().equals(department) && course.getNumber() == number) {
                return course;
            }
        }

        return null;
    }

    public static Course getRandomCourse() {
        return courses.get(new Random().nextInt(courses.size()));
    }

    public static List<Course> getCourses(String[] departments, int[] numbers) {
        if (departments.length != numbers.length) {
            System.out.println("Dimension mismatch in getCourses.");
            return null;
        }

        List<Course> subset = new ArrayList<Course>();

        for (int i = 0; i < departments.length; i++) {
            for (Course course : courses) {
                if (course.getDepartment().equals(departments[i]) && course.getNumber() == numbers[i]) {
                    subset.add(course);
                }
            }
        }

        return subset;
    }

    public static List<Course> getRandomCourses(int howMany) {
        // Clamp howMany to <= total number of courses.
        howMany = howMany <= courses.size() ? howMany : courses.size();

        List<Integer> indices = new ArrayList<Integer>();

        Random r = new Random();
        // Initialize to -1.
        for (int i = 0; i < howMany; i++) {
            while (true) {
                int index = r.nextInt(courses.size());
                if (!indices.contains(index)) {
                    indices.add(index);
                    break;
                }
            }
        }

        List<Course> subset = new ArrayList<Course>();

        for (int i = 0; i < howMany; i++) {
            subset.add(courses.get(indices.get(i)));
        }

        return subset;
    }

    public static List<Course> getAllCourses() {
        return courses;
    }

    public static Course getCourseById(String courseId) {
        for (Course course : courses) {
            if (course.getId().equals(courseId)) {
                return course;
            }
        }
        return null;
    }
}
