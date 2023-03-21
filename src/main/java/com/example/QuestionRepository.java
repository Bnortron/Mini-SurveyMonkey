package com.example;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuestionRepository extends CrudRepository<SurveyQuestion, Long> {
    List<SurveyQuestion> findBySurvey(Survey survey);
}
