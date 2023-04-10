package com.example;

import static org.junit.jupiter.api.Assertions.*;
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
        List<SurveyQuestion> questions = survey.getQuestions();
        questions.add(new SurveyQuestion());
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