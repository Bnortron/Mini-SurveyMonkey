package com.example.Questions;

import com.example.Surveys.Survey;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends CrudRepository<Question, Long> {
    List<Question> findBySurvey(Optional<Survey> survey);
}
