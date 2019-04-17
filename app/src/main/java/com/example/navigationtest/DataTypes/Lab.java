package com.example.navigationtest.DataTypes;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.Fragment;

public class Lab implements Parcelable {
    private String name;
    private int totalSeats;
    private int availableSeats;
    private int photoDrawableId;

    public Fragment fragment;

    //
    // CONSTRUCTOR
    //

    public Lab(String name, int totalSeats, int availableSeats, int photoDrawableId) {
        this.name = name;
        this.totalSeats = totalSeats;
        this.availableSeats = availableSeats;
        this.photoDrawableId = photoDrawableId;
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

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public int getPhotoDrawableId() {
        return photoDrawableId;
    }

    public void setPhotoDrawableId(int photoDrawableId) {
        this.photoDrawableId = photoDrawableId;
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
        dest.writeString(this.name);
        dest.writeInt(this.totalSeats);
        dest.writeInt(this.availableSeats);
        dest.writeInt(this.photoDrawableId);
    }

    protected Lab(Parcel in) {
        this.name = in.readString();
        this.totalSeats = in.readInt();
        this.availableSeats = in.readInt();
        this.photoDrawableId = in.readInt();
    }

    public static final Parcelable.Creator<Lab> CREATOR = new Parcelable.Creator<Lab>() {
        @Override
        public Lab createFromParcel(Parcel source) {
            return new Lab(source);
        }

        @Override
        public Lab[] newArray(int size) {
            return new Lab[size];
        }
    };
}
