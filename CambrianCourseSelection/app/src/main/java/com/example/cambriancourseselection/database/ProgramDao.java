package com.example.cambriancourseselection.database;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cambriancourseselection.model.Program;
import com.example.cambriancourseselection.model.ProgramSemester;

import java.util.List;

@Dao
public interface ProgramDao {
    @Query("select * from programs")
    List<Program> fetchPrograms();

    @Query("select * from programs")
    List<ProgramSemester> fetchProgramSem();

    @Query("select * from programs where name= :name")
    Program fetchProgramByName(String name);

    @Query("select * from programs where id= :id")
    ProgramSemester fetchProgramById(int id);

    @Insert(onConflict = REPLACE)
    void insertProgram(Program program);

    @Insert
    void insertListProgram(List<Program> programList);

    @Update
    void updateProgram(Program program);

    @Delete
    void deleteProgram(Program program);

}
