package com.example.Responses;

import com.example.Questions.*;
import com.example.Surveys.Survey;
import com.example.Surveys.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class ResponseController {

    private final SurveyRepository surveyRepository;
    private final QuestionRepository questionRepository;
    private final ResponseRepository responseRepository;

    public ResponseController(SurveyRepository surveyRepository,
                              QuestionRepository questionRepository,
                              ResponseRepository responseRepository) {
        this.surveyRepository = surveyRepository;
        this.questionRepository = questionRepository;
        this.responseRepository = responseRepository;
    }

    @PostMapping("/submitresponses")
    public String submitResponses(@RequestParam Long surveyid, @RequestParam Map<String, String> allParams, Model model) {
        // Print each of the responses to the console
        System.out.println("Survey " + surveyid + " Responses:");
        for (Map.Entry<String, String> entry : allParams.entrySet()) {
            System.out.println("Question: " + entry.getKey());
            System.out.println("Response: " + entry.getValue() + "\n");
        }

        // Find the Survey in the repository
        Optional<Survey> surveyOptional = surveyRepository.findById(surveyid);
        // Handle if Survey can't be found
        if (surveyOptional.isEmpty()) {
            return "survey not found!";
        }
        Survey survey = surveyOptional.get();
        System.out.println("Survey " + survey.getId() + " located!");

        // Get the responses for each question in the Survey
        List<Response> responses = new ArrayList<>();
        List<Question> questions = survey.getQuestions();
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i); // Get the Question
            String responseValue = allParams.get("responses[" + i + "]"); // Get the Response to Question

            // Handle case of a blank Response to one of the Questions
            if (responseValue == null || responseValue.isEmpty()) {
                model.addAttribute("errorMessage", "Please answer all questions");
                model.addAttribute("survey", survey);
                return "showsurvey";
            }

            // Initialize Response & set H2 table
            Response response = new Response();
            response.setQuestion(question);
            response.setQuestionType(question.getQuestionType().toString());
            response.setResponse(responseValue);
            response.setSurvey(survey);
            responses.add(response);
        }

        // Save the responses to the database
        responseRepository.saveAll(responses);
        survey.totalResponses();

        // Redirect user back home
        model.addAttribute("survey", survey);
        return "redirect:/survey?selectedSurvey=" + surveyid;
    }

    @GetMapping("/surveys/{id}/questions/{questionId}/responses")
    public String viewQuestionResponses(@PathVariable("id") Long surveyId,
                                        @PathVariable("questionId") Long questionId,
                                        Model model) {
        // Retrieve the survey and question data based on the surveyId and questionId
        Optional<Survey> survey = surveyRepository.findById(surveyId);
        Optional<Question> question = questionRepository.findById(questionId);

        // Retrieve the responses for the given question
        List<Response> responses = responseRepository.findByQuestion(question.get());

        // Add the survey, question, and response data to the model for use in the Thymeleaf template
        model.addAttribute("survey", survey.get());
        model.addAttribute("question", question.get());
        model.addAttribute("responses", responses);

        return "viewquestionresponses";
    }
}



