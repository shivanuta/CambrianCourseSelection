package com.example.cambriancourseselection.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cambriancourseselection.R;
import com.example.cambriancourseselection.adapter.MyStudyProgramAdapter;
import com.example.cambriancourseselection.database.AppDatabase;
import com.example.cambriancourseselection.database.CourseDao;
import com.example.cambriancourseselection.database.DatabaseClient;
import com.example.cambriancourseselection.database.ProgramDao;
import com.example.cambriancourseselection.database.SemesterDao;
import com.example.cambriancourseselection.model.Program;
import com.example.cambriancourseselection.model.ProgramSemester;

import java.util.ArrayList;
import java.util.List;

public class MyProgramFragment extends Fragment implements MyStudyProgramAdapter.ClickListiner {
    MyStudyProgramAdapter adapter;
    RecyclerView recyclerView;
    AppDatabase db;
    ProgramDao programDao;
    List<ProgramSemester> list = new ArrayList();
    SemesterDao semesterDao;
    CourseDao courseDao;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && programDao != null && adapter != null) {
            list = programDao.fetchProgramSem();
            adapter.updateList(list);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        db = DatabaseClient.getInstance(getContext()).getAppDb();
        programDao = db.programDao();
        courseDao = db.courseDao();
        semesterDao = db.semesterDao();
        list = programDao.fetchProgramSem();

        View rootView = inflater.inflate(R.layout.fragment_my_program, container, false);
        recyclerView = rootView.findViewById(R.id.rv_my_programs);
        adapter = new MyStudyProgramAdapter(list, requireContext(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        return rootView;
    }

    @Override
    public void click(Program program) {
        Intent intent = new Intent(requireActivity(), MyProgramDetailActivity.class);
        intent.putExtra("program_id", program.getId());
        requireActivity().startActivity(intent);
    }

    @Override
    public void onLongClick(Program programData) {

    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
