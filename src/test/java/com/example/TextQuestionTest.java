package com.example;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.example.Questions.TextQuestion;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class TextQuestionTest {
    TextQuestion textQuestion;

    @BeforeEach
    public void setUp() {
        textQuestion = new TextQuestion(200);
        assertNull(textQuestion.getResponse());
    }

    @Test
    public void updateResponse() {
        textQuestion.setResponse("Feedback");
        assertEquals("Feedback", textQuestion.getResponse());
    }

}