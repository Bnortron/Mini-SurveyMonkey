package com.example;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class MultipleChoiceQuestion extends SurveyQuestion {
    private int numChoices; // number of MC options user can select from
    private int selectedOption = -1; // the option the user selected

    @ElementCollection
    private List<String> options = new ArrayList<>();


    public MultipleChoiceQuestion(int numChoices, ArrayList<String> options) {
        super();
        this.questionType = QuestionType.MULTIPLE_CHOICE;
        this.numChoices = numChoices;
        this.options = options;
    }

    public MultipleChoiceQuestion() {
        super();
        this.questionType = QuestionType.MULTIPLE_CHOICE;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
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
