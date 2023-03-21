package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import java.util.List;

@Controller
public class SurveyController {
    @Autowired
    private final SurveyRepository surveyRepository;

    @Autowired
    private final QuestionRepository questionRepository;

    public SurveyController(SurveyRepository surveyRepository, QuestionRepository questionRepository) {
        this.surveyRepository = surveyRepository;
        this.questionRepository = questionRepository;
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
        System.out.println(survey);
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
     * <p>
     * NOTE: The "View Details" option in the table should bring user to Survey page (if Survey = open)
     * and allow them to begin responding to the questions (Starts the Survey for the user)
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

    @GetMapping("/addquestion")
    public String selectQuestion(Model model) {
        Iterable<Survey> surveys = surveyRepository.findAll();
        Iterable<SurveyQuestion> questions = questionRepository.findBySurvey(null);
        model.addAttribute("surveys", surveys);
        model.addAttribute("questions", questions);
        return "addquestion";
    }

    @PostMapping("/addquestion")
    public String addQuestion(@RequestParam("selectedSurvey") Long selectedSurveyId, @RequestParam("selectedQuestion") Long selectedQuestionId, Model model) {
        Optional<Survey> surveyOptional = surveyRepository.findById(selectedSurveyId);
        if (!surveyOptional.isPresent()) {
            // handle the case where the survey with the given id does not exist
            return "error";
        }
        Survey survey = surveyOptional.get();

        Optional<SurveyQuestion> questionOptional = questionRepository.findById(selectedQuestionId);
        if (!questionOptional.isPresent()) {
            // handle the case where the question with the given id does not exist
            return "error";
        }
        SurveyQuestion question = questionOptional.get();

        List<SurveyQuestion> questions = survey.getQuestions();
        questions.add(question);
        survey.setQuestions(questions);
        surveyRepository.save(survey);

        question.setSurvey(survey);
        questionRepository.save(question);

        return "index";
    }
}
