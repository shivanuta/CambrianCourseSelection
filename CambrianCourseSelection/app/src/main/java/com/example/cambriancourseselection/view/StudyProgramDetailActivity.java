package com.example.cambriancourseselection.view;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cambriancourseselection.R;
import com.example.cambriancourseselection.adapter.AdapterSectionRecycler;
import com.example.cambriancourseselection.model.Course;
import com.example.cambriancourseselection.model.Program;
import com.example.cambriancourseselection.model.SectionHeader;

import java.util.ArrayList;
import java.util.List;

public class StudyProgramDetailActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView tvProgramName;
    AdapterSectionRecycler adapterRecycler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_program_detail);

        Program programData = getIntent().getExtras().getParcelable("program_data");

        tvProgramName = (TextView) findViewById(R.id.tv_programName);
        tvProgramName.setText(programData.getName());

        //initialize RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.rvSemester);

        //setLayout Manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        //Create a List of Child DataModel
        List<Course> childList = new ArrayList<>();
        List<SectionHeader> sections = new ArrayList<>();

        for (int i = 0; i < programData.getSemesters().size(); i++) {
            sections.add(new SectionHeader(programData.getSemesters().get(i).getCourses(), "Semester " + (i+1)));
        }

        adapterRecycler = new AdapterSectionRecycler(this, sections);
        recyclerView.setAdapter(adapterRecycler);

    }
}
