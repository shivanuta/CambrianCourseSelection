package com.example.cambriancourseselection.database;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cambriancourseselection.model.Program;
import com.example.cambriancourseselection.model.ProgramSemester;
import com.example.cambriancourseselection.model.Semester;
import com.example.cambriancourseselection.model.SemesterCourse;

import java.util.List;

@Dao
public interface SemesterDao {
    @Query("select * from semester")
    List<Semester> fetchSemester();

    @Query("select * from semester")
    List<SemesterCourse> fetchSemesterCour();

    @Query("select * from semester where id= :id")
    Semester fetchSemesterById(int id);

    @Query("select * from semester where program_id= :id")
    Semester fetchSemesterByProgId(int id);

    @Insert(onConflict = REPLACE)
    long insertSemester(Semester semester);

    @Insert
    void insertListSemester(List<Semester> semesterList);

    @Update
    void updateSemester(Semester semester);

    @Delete
    void deleteSemester(Semester semester);



}
