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


    String createPage = """
                                                              <!DOCTYPE html>
                                                              <html>
                                                              <head>
                                                                <title>Create Survey</title>
                                                              </head>
                                                              <body>
                                                              <h1>Create Survey</h1>
                                                              <form method="post" action="/survey">
                                                                <label for="title">Title:</label>
                                                                <input type="text" id="title" name="title" required><br><br>
                                                                <label for="description">Description:</label>
                                                                <input type="text" id="description" name="description" required><br><br>
                                                                <input type="submit" value="Save Survey">
                                                              </form>
                                                              </body>
                                                              </html>
                                                              """.replaceAll("\n", "\r\n");

    String homePage = """
            <!DOCTYPE HTML>
            <html xmlns="http://www.w3.org/1999/xhtml">
            <head>
                <title>Getting Started: Serving Web Content</title>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
                <link rel="stylesheet" href="/css/signin.css"/>
            </head>
            <body>
            <p> Create a Survey <a href="/createsurvey">here</a></p>
            <p> View All Surveys <a href="/viewsurveys">here</a></p>
            <p> View Specific Survey <a href="/selectsurvey">here</a></p>
            <p> View Questions (Not Implemented) <a href="/viewquestions">here</a></p>
            <p> View Results (Not Implemented) <a href="/removesurvey">here</a></p>
            <p> View H2 Console <a href="http://localhost:8080/h2-console">here</a> </p>
            </body>
            </html>
            """.replaceAll("\n", "\r\n");

    @Test
    public void testCreate() throws Exception{

        //Get the surveys questions
        mockMvc.perform(get("/createsurvey", ""))
                .andDo(print()).andExpect(content().string(createPage));
        mockMvc.perform(post("/survey", "")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("title", "test")
                        .param("description", "test"))
                .andDo(print())
                .andExpect(content().string(homePage))
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
                .andExpect(content().string(homePage))
                .andExpect(status().is2xxSuccessful());
        mockMvc.perform(get("/viewsurveys", ""))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.model().attribute("surveys",
                        Matchers.everyItem(samePropertyValuesAs(s, "id", "questions", "status"))));
    }
}
