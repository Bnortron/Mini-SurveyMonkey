package com.example;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class NumberQuestionTest {
    NumberQuestion numberQuestion;

    @BeforeEach
    public void setUp() {
        numberQuestion = new NumberQuestion("test desc", 1, 5);
        assertEquals(-1, numberQuestion.getSelectedValue());
        assertEquals(1, numberQuestion.getMinRange());
        assertEquals(5, numberQuestion.getMaxRange());
    }

    @Test
    public void settingSelectedValue() {
        numberQuestion.setSelectedValue(2);
        assertEquals(2, numberQuestion.getSelectedValue());
    }
}