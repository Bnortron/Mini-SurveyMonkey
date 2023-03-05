package com.example;

public class TextQuestion extends SurveyQuestion {
    private String response = "";

    public TextQuestion() {
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
