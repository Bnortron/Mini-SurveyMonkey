package com.example;

import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository extends CrudRepository<SurveyQuestion, Long> {
}
