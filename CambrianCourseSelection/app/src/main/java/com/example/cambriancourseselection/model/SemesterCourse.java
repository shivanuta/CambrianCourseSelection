package com.example.cambriancourseselection.model;

import android.os.Parcel;

import androidx.room.Relation;

import java.util.List;

public class SemesterCourse extends Semester {

    public SemesterCourse() {
    }

    @Relation(parentColumn = "id", entityColumn = "semester_id", entity = Course.class)
    private List<Course> coursesList;

    protected SemesterCourse(Parcel in) {
        super(in);
    }

    public List<Course> getCoursesList() {
        return coursesList;
    }

    public void setCoursesList(List<Course> coursesList) {
        this.coursesList = coursesList;
        setCourses(coursesList);
    }

}
