package com.example;

import com.example.Questions.Question;
import com.example.Questions.TextQuestion;
import com.example.Surveys.Survey;
import com.example.Surveys.SurveyRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static net.bytebuddy.matcher.ElementMatchers.is;

import java.util.ArrayList;

import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class SurveyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SurveyRepository surveyRepository;


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

    @Test
    public void testAddQuestion() throws Exception {
        mockMvc.perform(post("/survey", "")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("title", "test")
                        .param("description", "test"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.view().name("index"))
                .andExpect(status().is2xxSuccessful());
        mockMvc.perform(post("/addtextquestion/1", "")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("description", "Do you like ice cream?")
                        .param("charLimit", "500"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/survey?selectedSurvey=1"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void testViewSurvey() throws Exception {
        Survey s = new Survey();
        s.setTitle("test");
        s.setDescription("test");
        ArrayList<Question> questions = new ArrayList<>();
        TextQuestion txtQ = new TextQuestion(500);
        txtQ.setDescription("Do you like ice cream?");
        questions.add(txtQ);
        s.setQuestions(questions);

        mockMvc.perform(get("/survey?selectedSurvey=1"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.model().attribute("survey",
                        Matchers.samePropertyValuesAs(s, "id", "questions", "status")))
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.view().name("showsurvey"));
    }

    /*
    @Test
    public void testActivateSurvey() throws Exception {
        mockMvc.perform(post("/survey")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("title", "test")
                        .param("description", "test"))
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(post("/survey/activate")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("surveyId", "1"))
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(get("/survey/1"))
                .andExpect(MockMvcResultMatchers.model().attribute("active", equalTo(true)))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void testDeactivateSurvey() throws Exception {
        mockMvc.perform(post("/survey")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("title", "test")
                        .param("description", "test"))
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(post("/survey/deactivate")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("surveyId", "1"))
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(get("/survey/1"))
                .andExpect(MockMvcResultMatchers.model().attribute("active", equalTo(false)))
                .andExpect(status().is2xxSuccessful());
    }
     */

    @Test
    public void testActivateDeactivateSurvey() throws Exception {
        Survey survey = new Survey();
        survey.setTitle("test");
        survey.setDescription("test");
        survey.setActive(false);
        surveyRepository.save(survey);

        mockMvc.perform(post("/survey/" + survey.getId() + "/activate"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/survey?selectedSurvey=1"));

        mockMvc.perform(post("/survey/" + survey.getId() + "/deactivate"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/survey?selectedSurvey=1"));
    }

}