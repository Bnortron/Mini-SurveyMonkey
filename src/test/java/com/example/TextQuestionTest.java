package com.example;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TextQuestionTest {
    TextQuestion textQuestion;

    @Before
    public void setUp(){
        textQuestion = new TextQuestion();
        assertEquals("", textQuestion.getResponse());
    }

    @Test
    public void updateResponse(){
        textQuestion.setResponse("Feedback");
        assertEquals("Feedback", textQuestion.getResponse());
    }

}