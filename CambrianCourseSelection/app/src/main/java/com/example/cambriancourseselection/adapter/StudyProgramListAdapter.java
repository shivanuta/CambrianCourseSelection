package com.example.cambriancourseselection.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cambriancourseselection.R;
import com.example.cambriancourseselection.model.Program;

import java.util.Collections;
import java.util.List;

public class StudyProgramListAdapter
        extends RecyclerView.Adapter<StudyProgramListAdapter.ProgramViewHolder> {

    List<Program> list = Collections.emptyList();

    Context context;
    ClickListiner listiner;

    public StudyProgramListAdapter(List<Program> list,
                                   Context context, ClickListiner listiner) {
        this.list = list;
        this.context = context;
        this.listiner = listiner;
    }

    @Override
    public ProgramViewHolder onCreateViewHolder(ViewGroup parent,
                                                int viewType) {

        Context context
                = parent.getContext();
        LayoutInflater inflater
                = LayoutInflater.from(context);

        // Inflate the layout

        View photoView
                = inflater
                .inflate(R.layout.row_program,
                        parent, false);

        ProgramViewHolder viewHolder
                = new ProgramViewHolder(photoView);
        return viewHolder;
    }

    @Override
    public void
    onBindViewHolder(final ProgramViewHolder viewHolder,
                     final int position) {
        Program program = list.get(position);
        viewHolder.programName
                .setText(program.getName());
        viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listiner.click(program);
            }
        });
        viewHolder.view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                listiner.onLongClick(program);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(
            RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public class ProgramViewHolder
            extends RecyclerView.ViewHolder {
        TextView programName;
        View view;

        ProgramViewHolder(View itemView) {
            super(itemView);
            programName
                    = (TextView) itemView
                    .findViewById(R.id.tv_programName);
            view = itemView;
        }
    }

    public interface ClickListiner {

        public void click(Program programData);

        public void onLongClick(Program programData);

    }


}