package com.example.cambriancourseselection.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cambriancourseselection.R;
import com.example.cambriancourseselection.adapter.StudyProgramListAdapter;
import com.example.cambriancourseselection.database.AppDatabase;
import com.example.cambriancourseselection.database.CourseDao;
import com.example.cambriancourseselection.database.DatabaseClient;
import com.example.cambriancourseselection.database.ProgramDao;
import com.example.cambriancourseselection.database.SemesterDao;
import com.example.cambriancourseselection.model.Course;
import com.example.cambriancourseselection.model.Program;
import com.example.cambriancourseselection.model.Semester;
import com.example.cambriancourseselection.model.StudyProgram;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AllProgramFragment extends Fragment implements StudyProgramListAdapter.ClickListiner {

    private static final String TAG = "AllProgramFragment";
    StudyProgramListAdapter adapter;
    RecyclerView recyclerView;
    StudyProgram data;
    AppDatabase db;
    ProgramDao programDao;
    List<Program> list = new ArrayList();
    SemesterDao semesterDao;
    CourseDao courseDao;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        db = DatabaseClient.getInstance(getContext()).getAppDb();
        programDao = db.programDao();
        courseDao = db.courseDao();
        semesterDao = db.semesterDao();

        data = new Gson().fromJson(loadJSONFromAsset(), StudyProgram.class);
        Log.d("data", new Gson().toJson(data));

        View rootView = inflater.inflate(R.layout.fragment_all_programs, container, false);

        init(rootView);

        return rootView;
    }

    private void init(View rootView) {
        /*Recycler view*/

        recyclerView = rootView.findViewById(R.id.rv_programs);

        adapter
                = new StudyProgramListAdapter(
                data.getPrograms(), requireContext(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(requireContext()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = requireActivity().getAssets().open("CambrianProgramStudy.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    @Override
    public void click(Program programData) {

        Intent intent = new Intent(requireActivity(), StudyProgramDetailActivity.class);
        intent.putExtra("program_data", programData);
        requireActivity().startActivity(intent);
    }

    @Override
    public void onLongClick(Program program) {
        list = programDao.fetchPrograms();

        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Log.d(TAG, "onLongClick: " + list.get(i).getName() + " " + program.getName());
                if (list.get(i).getName().equals(program.getName()))
                    new AlertDialog.Builder(getContext())
                            .setTitle("Program Exists!")
                            .setMessage("You already have this program in my study.")
                            .setNegativeButton("Close", null)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                else {
                    if (!list.get(i).isCompleted())
                        Toast.makeText(getContext(), "You should complete all programs to take a new one.", Toast.LENGTH_LONG).show();
                    else showDialog(program);
                }
            }
        } else {
            showDialog(program);
        }

    }

    private void showDialog(Program program) {
        new AlertDialog.Builder(getContext())
                .setTitle("Add Program")
                .setMessage("Add " + program.getName() + " to my study?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        addToMyStudy(program);
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void addToMyStudy(Program program) {
        Log.d(TAG, "addToMyStudy: " + program.toString());
        programDao.insertProgram(program);
        Program program1 = programDao.fetchProgramByName(program.getName());
        List<Course> courseList = new ArrayList<>();
        for (int i = 0; i < program.getSemesters().size(); i++) {
            Semester semester = new Semester();
            program.getSemesters().get(i).setProgram_id(program1.getId());
            semester = program.getSemesters().get(i);
            long id = semesterDao.insertSemester(semester);
            for (int j = 0; j < semester.getCourses().size(); j++) {
                semester.getCourses().get(j).setSemester_id((int) id);
                courseList.add(semester.getCourses().get(j));
            }
        }
        courseDao.insertListCourse(courseList);
        Toast.makeText(getContext(), "Course Added Successfully", Toast.LENGTH_SHORT).show();
    }
}
