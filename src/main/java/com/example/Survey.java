package com.example;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Survey {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private boolean active;
    private List<SurveyQuestion> questions;

    // Constructor, getters, and setters

    // ObjectDB-specific annotations
    @javax.persistence.Id
    public Long getObjectId() {
        return id;
    }

    public void setObjectId(Long id) {
        this.id = id;
    }


    public Survey(Long id) {
        this.id = id;
    }

    public Survey() {
        this.questions = new ArrayList<>();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SurveyQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<SurveyQuestion> questions) {
        this.questions = questions;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean b) {
        this.active = b;
    }
}
