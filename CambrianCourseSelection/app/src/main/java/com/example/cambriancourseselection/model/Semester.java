package com.example.cambriancourseselection.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "semester", foreignKeys = {
        @ForeignKey(entity = Program.class, childColumns = "program_id", parentColumns = "id", onDelete = ForeignKey.NO_ACTION),
})
public class Semester implements Parcelable {
    public Semester() {
    }

    public static final Creator<Semester> CREATOR = new Creator<Semester>() {
        @Override
        public Semester createFromParcel(Parcel in) {
            return new Semester(in);
        }

        @Override
        public Semester[] newArray(int size) {
            return new Semester[size];
        }
    };
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "program_id")
    private int program_id;

    @Ignore
    @SerializedName("courses")
    @Expose
    private List<Course> courses;



    protected Semester(Parcel in) {
        courses = in.createTypedArrayList(Course.CREATOR);
    }


    public int getProgram_id() {
        return program_id;
    }

    public void setProgram_id(int program_id) {
        this.program_id = program_id;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(courses);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
