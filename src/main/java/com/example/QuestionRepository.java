package com.example;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends CrudRepository<SurveyQuestion, Long> {
    List<SurveyQuestion> findBySurvey(Survey survey);
}
