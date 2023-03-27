package com.example.objectdb;

import com.example.Survey;
import com.example.SurveyQuestion;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ObjectDBSurveyRepository implements ObjectDBRepository<Survey, Long> {
    private final ObjectDBRepository<Survey, Long> surveyRepository;

    public ObjectDBSurveyRepository() {
        this.surveyRepository = new ObjectDBRepositoryImpl<>(Survey.class);
    }

    @Override
    public List<Survey> findAll() {
        return surveyRepository.findAll();
    }

    @Override
    public void create(Survey entity) {

    }

    @Override
    public Optional<Survey> findById(Long id) {
        return surveyRepository.findById(id);
    }

    @Override
    public Survey save(Survey survey) {
        surveyRepository.save(survey);
        return survey;
    }

    @Override
    public List<SurveyQuestion> findBySurvey(Survey survey) {
        return null;
    }

    @Override
    public void update(Survey entity) {

    }

    @Override
    public void delete(Survey id) {

    }
}
