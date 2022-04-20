package com.example.cambriancourseselection.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "course", foreignKeys = {
        @ForeignKey(entity = Semester.class, childColumns = "semester_id", parentColumns = "id", onDelete = ForeignKey.NO_ACTION),
})
public class Course implements Parcelable {
    public Course() {
    }

    public static final Creator<Course> CREATOR = new Creator<Course>() {
        @Override
        public Course createFromParcel(Parcel in) {
            return new Course(in);
        }

        @Override
        public Course[] newArray(int size) {
            return new Course[size];
        }
    };
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "course_code")
    @SerializedName("course_code")
    @Expose
    private String courseCode;

    @ColumnInfo(name = "course_name")
    @SerializedName("course_name")
    @Expose
    private String courseName;

    @ColumnInfo(name = "credits")
    @SerializedName("credits")
    @Expose
    private Integer credits;

    @ColumnInfo(name = "proffesor_name")
    @SerializedName("proffesor_name")
    @Expose
    private String proffesorName;

    @ColumnInfo(name = "course_desc")
    @SerializedName("course_desc")
    @Expose
    private String courseDesc;

    @ColumnInfo(name = "semester_id")
    private int semester_id;

    @ColumnInfo(name = "completed")
    private boolean completed;

    @ColumnInfo(name = "skipped")
    private boolean skipped;

    protected Course(Parcel in) {
        courseCode = in.readString();
        courseName = in.readString();
        if (in.readByte() == 0) {
            credits = null;
        } else {
            credits = in.readInt();
        }
        proffesorName = in.readString();
        courseDesc = in.readString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSemester_id() {
        return semester_id;
    }

    public void setSemester_id(int semester_id) {
        this.semester_id = semester_id;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean isSkipped() {
        return skipped;
    }

    public void setSkipped(boolean skipped) {
        this.skipped = skipped;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(courseCode);
        dest.writeString(courseName);
        if (credits == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(credits);
        }
        dest.writeString(proffesorName);
        dest.writeString(courseDesc);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public String getProffesorName() {
        return proffesorName;
    }

    public void setProffesorName(String proffesorName) {
        this.proffesorName = proffesorName;
    }

    public String getCourseDesc() {
        return courseDesc;
    }

    public void setCourseDesc(String courseDesc) {
        this.courseDesc = courseDesc;
    }

}
