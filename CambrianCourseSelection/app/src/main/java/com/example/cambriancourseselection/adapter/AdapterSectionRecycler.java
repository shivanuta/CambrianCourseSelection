package com.example.cambriancourseselection.adapter;

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

public class AdapterSectionRecycler extends SectionRecyclerViewAdapter<SectionHeader, Course, AdapterSectionRecycler.SectionViewHolder, AdapterSectionRecycler.ChildViewHolder> {

    Context context;

    public AdapterSectionRecycler(Context context, List<SectionHeader> sectionItemList) {
        super(context, sectionItemList);
        this.context = context;
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
        childViewHolder.courseCode.setText(child.getCourseCode());
        childViewHolder.courseName.setText(child.getCourseName());
        childViewHolder.courseCredits.setText(child.getCredits().toString());
    }

    public class SectionViewHolder extends RecyclerView.ViewHolder {

        TextView semesterNum;
        public SectionViewHolder(View itemView) {
            super(itemView);
            semesterNum = (TextView) itemView.findViewById(R.id.section);
        }
    }

    public class ChildViewHolder extends RecyclerView.ViewHolder {

        TextView courseName,courseCode,courseCredits;
        public ChildViewHolder(View itemView) {
            super(itemView);
            courseName = (TextView) itemView.findViewById(R.id.tv_course_name);
            courseCode = (TextView) itemView.findViewById(R.id.tv_course_code);
            courseCredits = (TextView) itemView.findViewById(R.id.tv_course_credits);
        }
    }
}
