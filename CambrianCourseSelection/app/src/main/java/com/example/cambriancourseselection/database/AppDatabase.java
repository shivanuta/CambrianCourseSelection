package com.example.cambriancourseselection.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.cambriancourseselection.model.Course;
import com.example.cambriancourseselection.model.Program;
import com.example.cambriancourseselection.model.Semester;


@Database(entities = {Course.class, Program.class, Semester.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ProgramDao programDao();

    public abstract SemesterDao semesterDao();

    public abstract CourseDao courseDao();
}
