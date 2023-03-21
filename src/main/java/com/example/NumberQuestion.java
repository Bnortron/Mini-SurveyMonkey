package com.example;

import jakarta.persistence.Entity;

@Entity
public class NumberQuestion extends SurveyQuestion {
    private int minRange; // starting number on num line
    private int maxRange; // final number on num line
    private int selectedValue = -1; // value user selects

    public NumberQuestion(int minRange, int maxRange) {
        this.questionType = QuestionType.valueOf("NUMBER_CHOICE_LINE");
        this.minRange = minRange;
        this.maxRange = maxRange;
    }

    public NumberQuestion() {
        this.questionType = QuestionType.valueOf("NUMBER_CHOICE_LINE");

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
