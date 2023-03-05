package com.example;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class NumberQuestionTest {
        NumberQuestion numberQuestion;

        @Before
        public void setUp(){
            numberQuestion = new NumberQuestion(1, 5);
            assertEquals(-1, numberQuestion.getSelectedValue());
            assertEquals(1, numberQuestion.getMinRange());
            assertEquals(5, numberQuestion.getMaxRange());
        }

        @Test
        public void settingSelectedValue(){
            numberQuestion.setSelectedValue(2);
            assertEquals(2, numberQuestion.getSelectedValue());
        }
}