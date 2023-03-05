package com.example;

import java.util.ArrayList;

public class MultipleChoiceQuestion extends SurveyQuestion {
    private ArrayList<String> options;
    private int selectedOption = -1;

    public MultipleChoiceQuestion(ArrayList<String> options) {
        this.options = options;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }

    public String getOption(int index) {
        return options.get(index);
    }

    public void removeOption(int index) {
        options.remove(index);
    }

    public void addOption(String s) {
        options.add(s);
    }

    public int getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(int selectedOption) {
        this.selectedOption = selectedOption;
    }
}
