package com.example.Questions;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class NumberQuestion extends Question {
    @Column(name = "min")
    private int minRange;// starting number on num line

    @Column(name = "max")
    private int maxRange; // final number on num line

    @Column(name = "response")
    private int selectedValue = -1; // value user selects

    public NumberQuestion(int minRange, int maxRange) {
        this.questionType = QuestionType.NUMBER_CHOICE_LINE;
        this.minRange = minRange;
        this.maxRange = maxRange;
    }

    public NumberQuestion() {
        this.questionType = QuestionType.NUMBER_CHOICE_LINE;

    }

    public int getMinRange() {
        return minRange;
    }

    public void setMinRange(int minRange) {
        this.minRange = minRange;
    }

    public int getMaxRange() {
        return maxRange;
    }

    public void setMaxRange(int maxRange) {
        this.maxRange = maxRange;
    }

    public int getSelectedValue() {
        return selectedValue;
    }

    public void setSelectedValue(int selectedValue) {
        this.selectedValue = selectedValue;
    }
}
