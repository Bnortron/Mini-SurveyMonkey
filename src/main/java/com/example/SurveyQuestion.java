package com.example;

import jakarta.persistence.*;

@Entity
public class SurveyQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    protected Long id;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    protected Survey survey;

    @Enumerated(EnumType.STRING)
    protected QuestionType questionType;

    protected String description;
    protected int questionOrder; // order of the question in the survey (1=first question, 2=second question,.., n=nth question in survey)

    /**
     * SurveyQuestion constructor using ID
     *
     * @param id
     */
    public SurveyQuestion(Long id) {
        this.id = id;
    }

    /**
     * SurveyQuestion blank constructor
     */
    public SurveyQuestion() {

    }

    /**
     * Getter for ID
     *
     * @return SurveyQuestion ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter for ID
     *
     * @param id the ID to set to this SurveyQuestion
     */
    public void setId(Long id) {
        this.id = id;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getOrder() {
        return questionOrder;
    }

    public void setOrder(int questionOrder) {
        this.questionOrder = questionOrder;
    }
}
