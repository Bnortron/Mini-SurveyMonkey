package com.example.Surveys;

import com.example.Questions.Question;
import com.example.Responses.Response;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "survey")
    private List<Question> questions;

    @Column(name = "active")
    private boolean active = false;

    public int responses;

    /**
     * Constructor for Survey with Generated ID
     * @param id
     */
    public Survey(Long id) {
        this.id = id;
    }

    /**
     * Empty constructor for Survey that creates the questions arraylist
     */
    public Survey() {
        this.questions = new ArrayList<>();
    }

    /**
     * Getters & Setters
     */
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

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean b) {
        this.active = b;
    }

    public int getResponses() { return this.responses; }

    public void totalResponses() {
        for(Question q : questions) {
            for(Response r : q.getResponses()) {
                this.responses++;
            }
        }
        System.out.println("Responses: " + responses);
    }
}
