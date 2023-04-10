package com.example.Responses;

import jakarta.persistence.*;

@Entity
@Table(name = "survey_results")
public class SurveyResult {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "question_num")
    private int questionNum;

    @Column(name = "num_Responses")
    private int responseNum;

    public SurveyResult(Long id) {
        this.id = id;
    }

    public SurveyResult() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
