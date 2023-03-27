package com.example.objectdb;

import com.example.Survey;
import com.example.SurveyQuestion;

import java.util.List;
import java.util.Optional;

public interface ObjectDBRepository<T, Long> {

    List<T> findAll();

    void create(T entity);

    Optional<T> findById(Long id);

    T save(T entity);

    List<SurveyQuestion> findBySurvey(Survey survey);

    void update(T entity);

    void delete(T id);
}

