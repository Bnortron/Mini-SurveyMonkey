package com.example.Responses;

import com.example.Questions.Question;
import jakarta.persistence.*;

@Entity
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "survey_question_id")
    private Question question;

    @Column(name = "response")
    private String response;

    public Response() {
    }

    public Response(Long id, Question question, String response) {
        this.id = id;
        this.question = question;
        this.response = response;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public void setResponseText(String responseText) {
    }
}
