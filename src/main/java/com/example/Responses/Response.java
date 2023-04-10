package com.example.Responses;

import com.example.Questions.Question;
import com.example.Surveys.Survey;
import jakarta.persistence.*;

@Entity
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "survey_id", referencedColumnName = "id")
    private Survey survey;

    @ManyToOne
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    private Question question;

    @Column(name = "question_type")
    private String questionType; // new field

    @Column(name = "response")
    private String response;

    public Response() {
    }

    public Response(Long id, Survey survey, Question question, String response) {
        this.id = id;
        this.survey = survey;
        this.question = question;
        this.response = response;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Survey getSurvey() { return survey; }

    public void setSurvey(Survey survey) { this.survey = survey; }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }
}
