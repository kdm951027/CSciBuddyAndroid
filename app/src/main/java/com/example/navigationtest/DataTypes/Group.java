package com.example.navigationtest.DataTypes;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.Fragment;

import com.example.navigationtest.SubPages.GroupPage;

import java.util.List;
import java.util.Objects;

public class Group implements Parcelable {
    // Id tracking variables
    private static int count;
    private int id;

    // Main variables
    private Course course;
    private List<Student> members;
    private String description;
    private String time;
    private String date;
    private String place;

    // Fragment for this group instance.
    public Fragment fragment;

    //
    // CONSTRUCTOR
    //

    public Group(Course course, List<Student> members, String description, String time, String date, String place) {
        this.id = this.count;
        this.count += 1;

        this.course = course;
        this.members = members;
        this.description = description;
        this.time = time;
        this.date = date;
        this.place = place;

        fragment = new GroupPage();
        Bundle b = new Bundle();
        b.putParcelable("group", this);
        fragment.setArguments(b);
    }

    //
    // GETTERS and SETTERS
    //

    public int getId() {
        return id;
    }

    // No setter for ID on purpose.

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Student> getMembers() {
        return members;
    }

    public String getMembersString() {
        String accum = "";
        for (int i = 0; i < members.size(); i++) {
            accum += members.get(i);

            if (i < members.size()-1) {
                accum += ", ";
            }
        }

        return accum;
    }

    public void setMembers(List<Student> members) {
        this.members = members;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }


    //
    // EQUALS and HASHCODE
    //

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return getId() == group.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public boolean isInGroup(Student student) {
        return members.contains(student);
    }

    public void removeFromGroup(Student student) {
        if (members.contains(student)) {
            members.remove(student);
        }
    }

    public void addToGroup(Student student) {
        if (!members.contains(student)) {
            members.add(student);
        }
    }

    //
    // PARCELABLE
    //

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeParcelable(this.course, flags);
        dest.writeTypedList(this.members);
        dest.writeString(this.description);
        dest.writeString(this.time);
        dest.writeString(this.date);
        dest.writeString(this.place);
    }

    protected Group(Parcel in) {
        this.id = in.readInt();
        this.course = in.readParcelable(Course.class.getClassLoader());
        in.readTypedList(this.members, Student.CREATOR);
        // this.members = in.createTypedArrayList(Student.CREATOR);
        this.description = in.readString();
        this.time = in.readString();
        this.date = in.readString();
        this.place = in.readString();
    }

    public static final Parcelable.Creator<Group> CREATOR = new Parcelable.Creator<Group>() {
        @Override
        public Group createFromParcel(Parcel source) {
            return new Group(source);
        }

        @Override
        public Group[] newArray(int size) {
            return new Group[size];
        }
    };
}
