package com.example.navigationtest.DataTypes;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class Course implements Parcelable {
    private String department;
    private int number;
    private String title;

    //
    // CONSTRUCTOR
    //

    public Course(String department, int number, String title) {
        this.department = department;
        this.number = number;
        this.title = title;
    }

    //
    // GETTERS and SETTERS
    //

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return department + " " + number;
    }

    //
    // EQUALS and HASHCODE
    //

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return getNumber() == course.getNumber() &&
                Objects.equals(getDepartment(), course.getDepartment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDepartment(), getNumber());
    }

    //
    // PARCELABLE
    // The Student class is parcelable and includes a list of
    // Course instances, so Course must be parcelable as well.
    //

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.department);
        dest.writeInt(this.number);
        dest.writeString(this.title);
    }

    protected Course(Parcel in) {
        this.department = in.readString();
        this.number = in.readInt();
        this.title = in.readString();
    }

    public static final Creator<Course> CREATOR = new Creator<Course>() {
        @Override
        public Course createFromParcel(Parcel source) {
            return new Course(source);
        }

        @Override
        public Course[] newArray(int size) {
            return new Course[size];
        }
    };

    //
    // TOSTRING
    //

    @Override
    public String toString() {
        return department + " " + number;
    }
}
