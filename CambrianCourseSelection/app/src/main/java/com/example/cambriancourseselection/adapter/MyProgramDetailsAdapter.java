package com.example.cambriancourseselection.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cambriancourseselection.R;
import com.example.cambriancourseselection.model.Course;
import com.example.cambriancourseselection.model.SectionHeader;
import com.intrusoft.sectionedrecyclerview.SectionRecyclerViewAdapter;

import java.util.List;

public class MyProgramDetailsAdapter extends SectionRecyclerViewAdapter<SectionHeader, Course, MyProgramDetailsAdapter.SectionViewHolder, MyProgramDetailsAdapter.ChildViewHolder> implements View.OnClickListener {

    Context context;
    ClickListiner listiner;


    public MyProgramDetailsAdapter(Context context, List<SectionHeader> sectionItemList, ClickListiner listiner) {
        super(context, sectionItemList);
        this.context = context;
        this.listiner = listiner;
    }

    @Override
    public SectionViewHolder onCreateSectionViewHolder(ViewGroup sectionViewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.section_item, sectionViewGroup, false);
        return new SectionViewHolder(view);
    }

    @Override
    public ChildViewHolder onCreateChildViewHolder(ViewGroup childViewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, childViewGroup, false);
        return new ChildViewHolder(view);
    }

    @Override
    public void onBindSectionViewHolder(SectionViewHolder sectionViewHolder, int sectionPosition, SectionHeader section) {
        sectionViewHolder.semesterNum.setText(section.getSectionText());
    }


    @Override
    public void onBindChildViewHolder(ChildViewHolder childViewHolder, int sectionPosition, int childPosition, Course child) {
        childViewHolder.options.setVisibility(View.VISIBLE);
        if (child.isCompleted()) {
            childViewHolder.itemView.setBackgroundColor(context.getResources().getColor(R.color.light_green));
            childViewHolder.options.setVisibility(View.GONE);
        } else if (child.isSkipped()) {
            childViewHolder.itemView.setBackgroundColor(context.getResources().getColor(R.color.light_red));
        }

        childViewHolder.courseCode.setText(child.getCourseCode());
        childViewHolder.courseName.setText(child.getCourseName());
        childViewHolder.courseCredits.setText(child.getCredits().toString());
        childViewHolder.options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listiner.click(child);
            }
        });
    }

    @Override
    public void onClick(View view) {


    }

    public interface ClickListiner {

        public void click(Course course);

        public void onLongClick(Course course);

    }

    public class SectionViewHolder extends RecyclerView.ViewHolder {

        TextView semesterNum;

        public SectionViewHolder(View itemView) {
            super(itemView);
            semesterNum = (TextView) itemView.findViewById(R.id.section);
        }
    }

    public class ChildViewHolder extends RecyclerView.ViewHolder {

        TextView courseName, courseCode, courseCredits, options;

        public ChildViewHolder(View itemView) {
            super(itemView);
            courseName = (TextView) itemView.findViewById(R.id.tv_course_name);
            courseCode = (TextView) itemView.findViewById(R.id.tv_course_code);
            courseCredits = (TextView) itemView.findViewById(R.id.tv_course_credits);
            options = (TextView) itemView.findViewById(R.id.options);

        }
    }
}
