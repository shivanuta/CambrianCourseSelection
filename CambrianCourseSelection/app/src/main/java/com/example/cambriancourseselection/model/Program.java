package com.example.cambriancourseselection.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "programs")
public class Program implements Parcelable {
    public static final Creator<Program> CREATOR = new Creator<Program>() {
        @Override
        public Program createFromParcel(Parcel in) {
            return new Program(in);
        }

        @Override
        public Program[] newArray(int size) {
            return new Program[size];
        }
    };
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "name")
    @SerializedName("name")
    @Expose
    private String name;
    @Ignore
    @SerializedName("semesters")
    @Expose
    private List<Semester> semesters;
    @ColumnInfo(name = "completed")
    private boolean completed;
    @Ignore
    private List<SemesterCourse> semestersList;

    public Program() {
    }

    protected Program(Parcel in) {
        name = in.readString();
        semesters = in.createTypedArrayList(Semester.CREATOR);
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public List<SemesterCourse> getSemestersList() {
        return semestersList;
    }

    public void setSemestersList(List<SemesterCourse> semestersList) {
        this.semestersList = semestersList;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeTypedList(semesters);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Semester> getSemesters() {
        return semesters;
    }

    public void setSemesters(List<Semester> semesters) {
        this.semesters = semesters;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
