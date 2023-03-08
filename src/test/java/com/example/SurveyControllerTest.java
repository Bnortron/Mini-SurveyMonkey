package com.example;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
public class SurveyControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testCreate() throws Exception{

        //Get the surveys questions
        mockMvc.perform(get("/createsurvey", ""))
                .andDo(print()).andExpect(MockMvcResultMatchers.view().name("createsurvey"));
        mockMvc.perform(post("/survey", "")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("title", "test")
                        .param("description", "test"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.view().name("index"))
                .andExpect(status().is2xxSuccessful());

    }

    @Test
    public void testViewSurveys() throws Exception{
        Survey s = new Survey();
        s.setTitle("test");
        s.setDescription("test");
        mockMvc.perform(post("/survey", "")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("title", "test")
                        .param("description", "test"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.view().name("index"))
                .andExpect(status().is2xxSuccessful());
        mockMvc.perform(get("/viewsurveys", ""))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.model().attribute("surveys",
                        Matchers.everyItem(samePropertyValuesAs(s, "id", "questions", "status"))));
        mockMvc.perform(get("/survey?selectedSurvey=1", ""))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.view().name("showsurvey"))
                .andExpect(MockMvcResultMatchers.model().attribute("survey",
                        Matchers.samePropertyValuesAs(s, "id", "questions", "status")));
        mockMvc.perform(get("/survey?selectedSurvey=1", ""))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.view().name("showsurvey"))
                .andExpect(MockMvcResultMatchers.model().attribute("survey",
                        Matchers.samePropertyValuesAs(s, "id", "questions", "status")));
    }
}
