package com.example;

import static org.junit.jupiter.api.Assertions.*;

import com.example.Questions.Question;
import com.example.Surveys.Survey;
import org.junit.jupiter.api.Test;

import java.util.List;

class SurveyTest {
    @Test
    public void testSetId() {
        Survey survey = new Survey();
        survey.setId(1L);
        assertEquals(1L, survey.getId());
    }

    @Test
    public void testSetTitle() {
        Survey survey = new Survey();
        survey.setTitle("Title");
        assertEquals("Title", survey.getTitle());
    }

    @Test
    public void testSetDescription() {
        Survey survey = new Survey();
        survey.setDescription("Description");
        assertEquals("Description", survey.getDescription());
    }

    @Test
    public void testSetQuestions() {
        Survey survey = new Survey();
        List<Question> questions = survey.getQuestions();
        questions.add(new Question());
        survey.setQuestions(questions);
        assertEquals(1, survey.getQuestions().size());
    }

    @Test
    public void testSetActive() {
        Survey survey = new Survey();
        survey.setActive(true);
        assertEquals(true, survey.getActive());
    }
}