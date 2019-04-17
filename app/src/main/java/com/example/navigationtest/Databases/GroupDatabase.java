package com.example.navigationtest.Databases;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;

import com.example.navigationtest.DataTypes.Group;
import com.example.navigationtest.DataTypes.Lab;
import com.example.navigationtest.DataTypes.Student;
import com.example.navigationtest.SubPages.GroupPage;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GroupDatabase {

    private static List<Group> groups;

    static String[] descriptions = new String[]{
            "Help us prepare for the upcoming exam!",
            "We are looking for another study partner.",
            "We don't know what we're doing...",
            "Join us!",
            "Help us study for the midterm haha",
            "Lets get this homework done!",
            "Looking for a project partner",
            "Help us finish this assignment",
            "Work on this assignment with us!",
            "Lets get this homework done.",
            "Looking for more study partners!"
    };

    public GroupDatabase() {
        groups = generateRandomGroups(10);
    }

    public static List<Group> getAllGroups() {
        return groups;
    }

    public static void addGroup(Group group) {
        if (!groups.contains(group)) {
            groups.add(group);
        }
    }

    static Group generateRandomGroup() {
        Random r = new Random();

        List<Student> members = StudentDatabase.getRandomStudents(r.nextInt(3)+1);

        List<Lab> labs = LabDatabase.getAllLabs();
        Lab lab = labs.get(r.nextInt(labs.size()));

        Group group = new Group(
                CourseDatabase.getRandomCourse(),
                members,
                descriptions[r.nextInt(descriptions.length)],
                getRandomTime(7, 1, 3),
                "April " + (r.nextInt(7)+16) + "th",
                lab.getName()
        );

        for (Student student : members) {
            student.joinGroup(group);
        }

        return group;
    }

    static List<Group> generateRandomGroups(int howMany) {
        List<Group> subset = new ArrayList<Group>();
        for (int i = 0; i < howMany; i++) {
            subset.add(generateRandomGroup());
        }
        return subset;
    }

    static String getRandomTime(int startMin, int durationMin, int durationMax) {
        Random r = new Random();

        int h1 = r.nextInt(12)+startMin;
        int m1 = r.nextInt(60);

        int h2 = r.nextInt(durationMax-durationMin)+h1+durationMin;
        int m2 = r.nextInt(60);

        String time = "";
        if (h1 > 12) {
            time += "" + String.format("%02d", h1%12) + ":" + String.format("%02d", m1)  + "pm";
        }
        else {
            time += "" + String.format("%02d", h1)  + ":" + String.format("%02d", m1) + "am";
        }
        time += "-";
        if (h2 > 12) {
            time += "" + String.format("%02d", h2%12)  + ":" + String.format("%02d", m2) + "pm";
        }
        else {
            time += "" + String.format("%02d", h2)  + ":" + String.format("%02d", m2) + "am";
        }

        return time;
    }
}
