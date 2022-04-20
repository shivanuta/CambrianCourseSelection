package com.example.cambriancourseselection.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cambriancourseselection.R;
import com.example.cambriancourseselection.adapter.MyProgramDetailsAdapter;
import com.example.cambriancourseselection.database.AppDatabase;
import com.example.cambriancourseselection.database.CourseDao;
import com.example.cambriancourseselection.database.DatabaseClient;
import com.example.cambriancourseselection.database.ProgramDao;
import com.example.cambriancourseselection.database.SemesterDao;
import com.example.cambriancourseselection.model.Course;
import com.example.cambriancourseselection.model.ProgramSemester;
import com.example.cambriancourseselection.model.SectionHeader;
import com.example.cambriancourseselection.model.Semester;

import java.util.ArrayList;
import java.util.List;

public class MyProgramDetailActivity extends AppCompatActivity implements MyProgramDetailsAdapter.ClickListiner {
    private static final String TAG = "MyProgramDetailActivity";
    AppDatabase db;
    ProgramDao programDao;
    List<ProgramSemester> list = new ArrayList();
    ProgramSemester programData;
    SemesterDao semesterDao;
    CourseDao courseDao;
    RecyclerView recyclerView;
    TextView tvProgramName;
    MyProgramDetailsAdapter adapterRecycler;
    List<SectionHeader> sections = new ArrayList<>();
    int program_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_program_detail);
        db = DatabaseClient.getInstance(getApplicationContext()).getAppDb();
        programDao = db.programDao();
        courseDao = db.courseDao();
        semesterDao = db.semesterDao();

        program_id = getIntent().getExtras().getInt("program_id");
        programData = programDao.fetchProgramById(program_id);

        tvProgramName = (TextView) findViewById(R.id.tv_programName);
        tvProgramName.setText(programData.getName());

        //initialize RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.rvSemester);

        //setLayout Manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        adapterRecycler = new MyProgramDetailsAdapter(this, sections, this);
        recyclerView.setAdapter(adapterRecycler);
        updateList();
    }

    private void updateList() {
        programData = programDao.fetchProgramById(program_id);
        sections = new ArrayList<>();
        for (int i = 0; i < programData.getSemestersList().size(); i++) {
            sections.add(new SectionHeader(programData.getSemestersList().get(i).getCourses(), "Semester " + (i + 1)));
        }
        adapterRecycler.notifyDataChanged(sections);
    }

    @Override
    public void click(Course course) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Modify Course")
                .setMessage("")
                .setNegativeButton("Completed", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        course.setCompleted(true);
                        course.setSkipped(false);
                        courseDao.updateCourse(course);
                        updateProgram();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert);
        if (!course.isCompleted()) {
            builder.setPositiveButton("Drop Course", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    course.setCompleted(false);
                    course.setSkipped(true);
                    Semester semester = semesterDao.fetchSemesterById(course.getSemester_id() + 1);
                    if (semester != null)
                        course.setSemester_id(semester.getId());
                    courseDao.updateCourse(course);
                    updateProgram();
                }
            });
        }
        builder.create();
        builder.show();
    }

    private void updateProgram() {
        programData = programDao.fetchProgramById(program_id);
        boolean b = true;
        for (int j = 0; j < programData.getSemestersList().size(); j++) {
            if (!b) break;
            for (int k = 0; k < programData.getSemestersList().get(j).getCourses().size(); k++) {
                if (!b) break;
                b = programData.getSemestersList().get(j).getCourses().get(k).isCompleted();
                Log.d(TAG, "updateProgram: " + programData.getSemestersList().get(j).getCourses().get(k).isCompleted());
            }
        }

        if (b) {
            programData.setCompleted(b);
            programDao.updateProgram(programData);
        }

        updateList();
    }

    @Override
    public void onLongClick(Course course) {

    }
}
