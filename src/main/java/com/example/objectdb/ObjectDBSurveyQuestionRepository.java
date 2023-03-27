package com.example.objectdb;

import com.example.Survey;
import com.example.SurveyQuestion;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ObjectDBSurveyQuestionRepository implements ObjectDBRepository<SurveyQuestion, Long> {
    private final ObjectDBRepository<SurveyQuestion, Long> surveyQuestionRepository;

    public ObjectDBSurveyQuestionRepository() {
        this.surveyQuestionRepository = new ObjectDBRepositoryImpl<>(SurveyQuestion.class);
    }

    @Override
    public List<SurveyQuestion> findAll() {
        return surveyQuestionRepository.findAll();
    }

    @Override
    public void create(SurveyQuestion entity) {

    }

    @Override
    public Optional<SurveyQuestion> findById(Long id) {
        return surveyQuestionRepository.findById(id);
    }

    @Override
    public SurveyQuestion save(SurveyQuestion surveyQuestion) {
        surveyQuestionRepository.save(surveyQuestion);
        return surveyQuestion;
    }

    @Override
    public List<SurveyQuestion> findBySurvey(Survey survey) {
        return null;
    }

    @Override
    public void update(SurveyQuestion entity) {
    }

    @Override
    public void delete(SurveyQuestion id) {
    }
}
