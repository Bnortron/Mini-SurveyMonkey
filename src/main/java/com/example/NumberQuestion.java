package com.example;

public class NumberQuestion extends SurveyQuestion {

    private int minRange;
    private int maxRange;
    private int selectedValue = -1;

    public NumberQuestion(int minRange, int maxRange) {
        this.minRange = minRange;
        this.maxRange = maxRange;
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
