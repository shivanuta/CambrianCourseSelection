
package com.example.cambriancourseselection.model;

import androidx.room.Ignore;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StudyProgram {

    @Ignore
    @SerializedName("programs")
    @Expose
    private List<Program> programs = null;

    public List<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(List<Program> programs) {
        this.programs = programs;
    }

}
