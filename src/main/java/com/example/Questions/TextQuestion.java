package com.example.Questions;

import jakarta.persistence.Entity;

@Entity
public class TextQuestion extends Question {
    private int charLimit;
    private String response = "";

    public TextQuestion(int charLimit) {
        super();
        this.questionType = QuestionType.TEXT;
        this.charLimit = charLimit;
    }

    public TextQuestion() {
        super();
        this.questionType = QuestionType.TEXT;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public int getCharLimit() {
        return charLimit;
    }

    public void setCharLimit(int charLimit) {
        this.charLimit = charLimit;
    }
}
