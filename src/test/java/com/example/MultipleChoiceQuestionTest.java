package com.example;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

public class MultipleChoiceQuestionTest {
    ArrayList<String> list = new ArrayList<>();
    MultipleChoiceQuestion mcq;

    @Before
    public void setUp(){
        list.add("Very Bad");
        list.add("Bad");
        list.add("Ok");
        list.add("Good");
        list.add("Great");
        mcq = new MultipleChoiceQuestion(list);
        mcq.setSelectedOption(1);
        assertEquals("Bad", mcq.getOption(1));
        assertEquals(1, mcq.getSelectedOption());
        assertEquals(5, mcq.getOptions().size());
    }

    @Test
    public void removeOption(){
        mcq.removeOption(1);
        assertEquals("Ok", mcq.getOption(1));
        assertEquals(4, mcq.getOptions().size());
    }

    @Test
    public void addOption(){
        mcq.addOption("New");
        mcq.addOption("Very New");
        System.out.println(mcq.getOptions());
        assertEquals(7, mcq.getOptions().size());
    }
}