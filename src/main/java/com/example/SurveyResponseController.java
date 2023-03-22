package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SurveyResponseController {

    @Autowired
    private final SurveyResponseRepository surveyResponseRepository;


    public SurveyResponseController(SurveyResponseRepository surveyResponseRepository) {
        this.surveyResponseRepository = surveyResponseRepository;
    }

    @PostMapping("/response")
    public String createResponse(@ModelAttribute SurveyResponse response){
        surveyResponseRepository.save(response);
        return "index";
    }
}
