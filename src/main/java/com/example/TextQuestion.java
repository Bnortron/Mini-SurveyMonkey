package com.example;

import jakarta.persistence.Entity;

@Entity
public class TextQuestion extends SurveyQuestion {
    private String description;
    private int charLimit;
    private String response = "";

    public TextQuestion(String description, int charLimit) {
        this.description = description;
        this.charLimit = charLimit;
    }

    public TextQuestion() {

    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
