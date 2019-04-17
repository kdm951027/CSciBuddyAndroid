package com.example.navigationtest.Databases;

import android.os.Bundle;

import com.example.navigationtest.DataTypes.Student;
import com.example.navigationtest.Pages.ProfilePage;
import com.example.navigationtest.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This is a class emulates a database.
 */
public class StudentDatabase {

    // The list of students.
    private static List<Student> students = new ArrayList<Student>() {
        {
            // Anlan He
            Student he000193 = new Student(
                    "Anlan He",
                    "I like BTS!",
                    "Computer Science",
                    "Senior",
                    "he000193",
                    CourseDatabase.getRandomCourses(3),
                    R.drawable.profile_photo_he000193,
                    R.drawable.profile_background_he000193
            );
            add(he000193);

            // Dongmin Kim
            Student kimx4043 = new Student(
                    "Dongmin Kim",
                    "I like Dinosaurs.",
                    "Computer Science",
                    "Senior",
                    "kimx4043",
                    CourseDatabase.getRandomCourses(3),
                    R.drawable.profile_photo_kimx4043,
                    R.drawable.profile_background_kimx4043
            );
            add(kimx4043);

            // Dan Shervheim
            Student sherv029 = new Student(
                    "Dan Shervheim",
                    "I like iced tea, computer graphics and physical simulations!",
                    "Computer Science",
                    "Senior",
                    "sherv029",
                    CourseDatabase.getCourses(
                            new String[]{"CSCI", "CSCI", "CSCI", "CSCI"},
                            new int[]{4611, 5115, 5609, 5611}
                            ),
                    R.drawable.profile_photo_sherv029,
                    R.drawable.profile_background_sherv029
            );
            add(sherv029);

            // Daniel Rockcastle
            Student rockc004 = new Student(
                    "Daniel Rockcastle",
                    "Graphic design minor.",
                    "Computer Science",
                    "Junior",
                    "rockc004",
                    CourseDatabase.getRandomCourses(3),
                    R.drawable.profile_photo_rockc004,
                    R.drawable.profile_background_rockc004
            );
            add(rockc004);

            // Add new students here based on the above template.

            Student rand000 = new Student(
                    "John Doe",
                    "I like many things.",
                    "History",
                    "Freshman",
                    "doexx001",
                    CourseDatabase.getRandomCourses(2),
                    R.drawable.profile_photo_null,
                    R.drawable.profile_background_null
            );
            add(rand000);

            Student rand001 = new Student(
                    "Barack Obama",
                    "Former president.",
                    "Political Science",
                    "Senior",
                    "obama001",
                    CourseDatabase.getRandomCourses(3),
                    R.drawable.profile_photo_obama,
                    R.drawable.profile_background_null
            );
            add(rand001);

            Student rand002 = new Student(
                    "Jeremy Clarkson",
                    "Gearhead and former Top Gear host.",
                    "History",
                    "Senior",
                    "clark001",
                    CourseDatabase.getRandomCourses(3),
                    R.drawable.profile_photo_clarkson,
                    R.drawable.profile_background_null
            );
            add(rand002);

            Student rand003 = new Student(
                    "Tom Hanks",
                    "Actor. Writer. Father. Husband.",
                    "Drama",
                    "Sophomore",
                    "hanx004",
                    CourseDatabase.getRandomCourses(5),
                    R.drawable.profile_photo_hanx,
                    R.drawable.profile_background_null
            );
            add(rand003);

            Student rand004 = new Student(
                    "Luke Skywalker",
                    "I saved the galaxy, no big deal.",
                    "Jedi Studies",
                    "Master",
                    "skywa001",
                    CourseDatabase.getRandomCourses(3),
                    R.drawable.profile_photo_skywalker,
                    R.drawable.profile_background_null
            );
            add(rand004);

            for (int i = 0; i < 20; i++) {
                // add(Student.generateRandomStudent());
            }
        }
    };

    public StudentDatabase() {
        Student.currentX500 = getRandomStudent().getX500();
    }

    public static Student getStudent(String x500) {
        for (Student student : students) {
            if (student.getX500().equals(x500)) {
                return student;
            }
        }

        return null;
    }

    public static Student getRandomStudent() {
        return students.get(new Random().nextInt(students.size()));
    }

    public static List<Student> getStudents(String[] x500s) {
        List<Student> subset = new ArrayList<Student>();
        for (String x500 : x500s) {
            for (Student student : students) {
                if (student.getX500().equals(x500)) {
                    subset.add(student);
                }
            }
        }

        return subset;
    }

    public static List<Student> getRandomStudents(int howMany) {
        // Clamp howMany to <= total number of students.
        howMany = howMany <= students.size() ? howMany : students.size();

        List<Integer> indices = new ArrayList<Integer>();

        Random r = new Random();
        // Initialize to -1.
        for (int i = 0; i < howMany; i++) {
            while (true) {
                int index = r.nextInt(students.size());
                if (!indices.contains(index)) {
                    indices.add(index);
                    break;
                }
            }
        }

        List<Student> subset = new ArrayList<Student>();

        for (int i = 0; i < howMany; i++) {
            subset.add(students.get(indices.get(i)));
        }

        return subset;
    }

    public static List<Student> getAllStudents() {
        return students;
    }

}

