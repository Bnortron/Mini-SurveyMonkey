package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class SurveyController {
    @Autowired
    private final SurveyRepository surveyRepository;

    public SurveyController(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    @GetMapping("/createsurvey")
    public String createSurvey(Model model) {
        model.addAttribute("survey", new Survey());
        return "createsurvey";
    }


    /**
     * Method that handles POST request for saving a new Survey to the database
     *
     * @param survey the Survey to save to the database
     * @return redirects user back home (to be changed)
     */
    @PostMapping("/survey")
    public String saveSurvey(@ModelAttribute Survey survey) {
        surveyRepository.save(survey);
        return "index";
    }

    /**
     * Select a Survey to view (Displays Survey properties - not survey questions)
     *
     * @param model
     * @return
     */
    @GetMapping("/selectsurvey")
    public String selectSurvey(Model model) {
        Iterable<Survey> surveys = surveyRepository.findAll();
        model.addAttribute("surveys", surveys);
        return "selectsurvey";
    }

    /**
     * Displays the single Survey chosen from the list of ALL Surveys
     *
     * @param surveyId the id of the Survey to view
     * @param model
     * @return
     */
    @GetMapping("/survey/view")
    public String viewSurvey(@RequestParam("surveyId") Long surveyId, Model model) {
        Optional<Survey> surveyOptional = surveyRepository.findById(surveyId);
        if (!surveyOptional.isPresent()) {
            // handle the case where the survey with the given id does not exist
            return "error";
        }
        Survey survey = surveyOptional.get();
        model.addAttribute("survey", survey);
        return "showsurvey";
    }

    /**
     * Displays the single Survey selected from the list of Surveys
     * NOTE: Future implementation will include ALL questions stored in this Survey as well
     *
     * @param selectedSurveyId the id of the selected Survey to view
     * @param model
     * @return
     */
    @GetMapping("/survey")
    public String showSurvey(@RequestParam("selectedSurvey") Long selectedSurveyId, Model model) {
        Optional<Survey> surveyOptional = surveyRepository.findById(selectedSurveyId);
        if (!surveyOptional.isPresent()) {
            // handle the case where the survey with the given id does not exist
            return "error";
        }
        Survey survey = surveyOptional.get();
        model.addAttribute("survey", survey);
        return "showsurvey";
    }

    /**
     * View ALL Surveys stored in database
     *
     * NOTE: The "View Details" option in the table should bring user to Survey page (if Survey = open)
     *       and allow them to begin responding to the questions (Starts the Survey for the user)
     *
     * @param model
     * @return
     */
    @GetMapping("/viewsurveys")
    public String viewSurveys(Model model) {
        Iterable<Survey> surveys = surveyRepository.findAll();
        model.addAttribute("surveys", surveys);
        return "viewsurveys";
    }
}
