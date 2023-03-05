package com.example;

import jakarta.persistence.*;

@Entity
public class SurveyQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    public SurveyQuestion(Long id) {
        this.id = id;
    }

    public SurveyQuestion() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
