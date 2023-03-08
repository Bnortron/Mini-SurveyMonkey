package com.example;

import jakarta.persistence.Entity;

@Entity
public class NumberQuestion extends SurveyQuestion {
    private String description; // question description
    private int minRange; // starting number on num line
    private int maxRange; // final number on num line
    private int selectedValue = -1; // value user selects

    public NumberQuestion(String description, int minRange, int maxRange) {
        this.description = description;
        this.minRange = minRange;
        this.maxRange = maxRange;
    }

    public NumberQuestion() {

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
