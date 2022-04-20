package com.example.cambriancourseselection.model;

import android.os.Parcel;

import androidx.room.Relation;

import java.util.List;

public class ProgramSemester extends Program {
    @Relation(parentColumn = "id", entityColumn = "program_id", entity = Semester.class)
    private List<Semester> semesters;
    @Relation(parentColumn = "id", entityColumn = "program_id", entity = Semester.class)
    private List<SemesterCourse> semestersList2;

    public ProgramSemester() {
    }

    protected ProgramSemester(Parcel in) {
        super(in);
    }

    public List<SemesterCourse> getSemestersList2() {
        return semestersList2;
    }

    public void setSemestersList2(List<SemesterCourse> semestersList2) {
        this.semestersList2 = semestersList2;
        setSemestersList(semestersList2);
    }

}
