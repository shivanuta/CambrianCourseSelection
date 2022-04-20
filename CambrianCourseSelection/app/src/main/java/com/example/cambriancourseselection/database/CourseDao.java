package com.example.cambriancourseselection.database;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cambriancourseselection.model.Course;

import java.util.List;

@Dao
public interface CourseDao {
    @Query("select * from course")
    List<Course> fetchCourse();

    @Query("select * from course where id= :id")
    Course fetchCourseById(int id);

    @Query("select * from course where semester_id= :id")
    Course fetchCourseBySemId(int id);

    @Insert(onConflict = REPLACE)
    void insertCourse(Course course);

    @Insert
    void insertListCourse(List<Course> courseList);

    @Update
    void updateCourse(Course course);

    @Delete
    void deleteCourse(Course course);


}
