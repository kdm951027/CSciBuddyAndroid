package com.example.navigationtest.DataTypes;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.Fragment;

import com.example.navigationtest.Databases.CourseDatabase;
import com.example.navigationtest.Pages.ProfilePage;
import com.example.navigationtest.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Student implements Parcelable {
    private String name;
    private String about;
    private String major;
    private String year;
    private String x500;
    private List<Course> enrolledCourses;
    private List<Group> currentGroups;
    private int photoDrawableId;
    private int backgroundDrawableId;
    public static String currentX500;

    private boolean seated = false;


    // Fragment associated with this student.
    public Fragment fragment;

    //
    // CONSTRUCTOR
    //

    public Student(String name, String about, String major, String year, String x500, List<Course> enrolledCourses, int photoDrawableId, int backgroundDrawableId) {
        this.name = name;
        this.about = about;
        this.major = major;
        this.year = year;
        this.x500 = x500;
        this.enrolledCourses = enrolledCourses;
        this.currentGroups = new ArrayList<Group>();
        this.photoDrawableId = photoDrawableId;
        this.backgroundDrawableId = backgroundDrawableId;

        fragment = new ProfilePage();
        Bundle b = new Bundle();
        b.putParcelable("student", this);
        fragment.setArguments(b);
    }

    //
    // GETTERS and SETTERS
    //

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getX500() {
        return x500;
    }

    public void setX500(String x500) {
        this.x500 = x500;
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public boolean isSeated() {
        return seated;
    }

    public void setSeated(boolean seated) {
        this.seated = seated;
    }

    public String getEnrolledCoursesString() {
        String accum = "";
        for (int i = 0; i < enrolledCourses.size(); i++) {
            accum += enrolledCourses.get(i);
            if (i < enrolledCourses.size()-1) {
                accum += ", ";
            }
        }
        return accum;
    }

    public void setEnrolledCourses(List<Course> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }

    public List<Group> getCurrentGroups() { return currentGroups; }

    public void joinGroup(Group group) {
        if (!currentGroups.contains(group)) {
            currentGroups.add(group);
        }
    }

    public void leaveGroup(Group group) {
        if (currentGroups.contains(group)) {
            currentGroups.remove(group);
        }
    }

    public int getPhotoDrawableId() {
        return photoDrawableId;
    }

    public void setPhotoDrawableId(int photoDrawableId) {
        this.photoDrawableId = photoDrawableId;
    }

    public int getBackgroundDrawableId() {
        return backgroundDrawableId;
    }

    public void setBackgroundDrawableId(int backgroundDrawableId) {
        this.backgroundDrawableId = backgroundDrawableId;
    }

    //
    // EQUALS and HASHCODE
    //

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(getX500(), student.getX500());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX500());
    }

    //
    // TOSTRING
    //

    @Override
    public String toString() {
        return name;
    }

    //
    // PARCELABLE
    // this allows us to pass student instances into ProfilePage fragment instances
    // as a Bundle resource.
    //


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.about);
        dest.writeString(this.major);
        dest.writeString(this.year);
        dest.writeString(this.x500);
        dest.writeTypedList(this.enrolledCourses);
        dest.writeTypedList(this.currentGroups);
        dest.writeInt(this.photoDrawableId);
        dest.writeInt(this.backgroundDrawableId);
    }

    protected Student(Parcel in) {
        this.name = in.readString();
        this.about = in.readString();
        this.major = in.readString();
        this.year = in.readString();
        this.x500 = in.readString();
        in.readTypedList(this.enrolledCourses, Course.CREATOR);
        in.readTypedList(this.currentGroups, Group.CREATOR);
        // this.enrolledCourses = in.createTypedArrayList(Course.CREATOR);
        // this.currentGroups = in.createTypedArrayList(Group.CREATOR);
        this.photoDrawableId = in.readInt();
        this.backgroundDrawableId = in.readInt();
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel source) {
            return new Student(source);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    //
    // RANDOM GENERATION
    //

    static String[] firstNames = new String[]{
            "Mohamed",
            "Youssef",
            "Ahmed",
            "Santiago",
            "Miguel",
            "Jayden",
            "Noah",
            "Maria",
            "Alicia",
            "Sofia",
            "Jian",
            "Kensuke",
            "Kang",
            "Michiko",
            "Kiran",
            "Adam",
            "Jesse",
            "Lan",
            "Lukas",
            "Isak",
            "Anna",
            "Ellen",
            "Yvette"
    };

    static String[] lastNames = new String[]{
            "He",
            "Smith",
            "Johnson",
            "Williams",
            "Jones",
            "Brown",
            "Chen",
            "Kondo",
            "Fujita",
            "Choy",
            "Lechner",
            "Wolf",
            "Schneider",
            "Diaz",
            "Hernandez"
    };

    public static Student generateRandomStudent() {
        Random r = new Random();
        return new Student(
                firstNames[r.nextInt(firstNames.length)] + " " + lastNames[r.nextInt(lastNames.length)],
                "A bit about me...",
                "Major",
                "Freshman",
                "xxxxx000",
                CourseDatabase.getRandomCourses(r.nextInt(4)+1),
                R.drawable.profile_photo_null,
                R.drawable.profile_background_null
        );
    }
}
