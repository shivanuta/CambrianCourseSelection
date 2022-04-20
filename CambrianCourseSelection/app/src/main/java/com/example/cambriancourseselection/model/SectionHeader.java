package com.example.cambriancourseselection.model;

import com.intrusoft.sectionedrecyclerview.Section;

import java.util.List;

public class SectionHeader implements Section<Course> {

    List<Course> childList;
    String sectionText;

    public SectionHeader(List<Course> childList, String sectionText) {
        this.childList = childList;
        this.sectionText = sectionText;
    }

    @Override
    public List<Course> getChildItems() {
        return childList;
    }

    public String getSectionText() {
        return sectionText;
    }
}