package com.example.Responses;

import com.example.Questions.Question;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ResponseRepository extends CrudRepository<Response, Long> {
    List<Response> findByQuestion(Question question);
}
