package com.example;

import com.example.objectdb.ObjectDBSurveyQuestionRepository;
import com.example.objectdb.ObjectDBSurveyRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;

@Controller
public class SurveyController {
    private final ObjectDBSurveyRepository surveyRepository;
    private final ObjectDBSurveyQuestionRepository surveyQuestionRepository;

    public SurveyController(ObjectDBSurveyRepository surveyRepository, ObjectDBSurveyQuestionRepository surveyQuestionRepository) {
        this.surveyRepository = surveyRepository;
        this.surveyQuestionRepository = surveyQuestionRepository;
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
        Iterable<SurveyQuestion> questions = surveyQuestionRepository.findBySurvey(null);
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

        Optional<SurveyQuestion> questionOptional = surveyQuestionRepository.findById(selectedQuestionId);
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
        surveyQuestionRepository.save(question);

        return "index";
    }

    /**
     * Activate a Survey - Will allow users to respond to it's questions
     *
     * @param id
     * @return
     */
    @PostMapping("/survey/{id}/activate")
    public String activateSurvey(@PathVariable("id") Long id) {
        Optional<Survey> optionalSurvey = surveyRepository.findById(id);
        if (optionalSurvey.isPresent()) {
            Survey survey = optionalSurvey.get();
            survey.setActive(true);
            surveyRepository.save(survey);
        }
        return "redirect:/survey?selectedSurvey=" + id;
    }

    /**
     * Deactivate a Survey - Users no longer allowed to respond to it's questions
     *
     * @param id
     * @return
     */
    @PostMapping("/survey/{id}/deactivate")
    public String deactivateSurvey(@PathVariable("id") Long id) {
        Optional<Survey> optionalSurvey = surveyRepository.findById(id);
        if (optionalSurvey.isPresent()) {
            Survey survey = optionalSurvey.get();
            survey.setActive(false);
            surveyRepository.save(survey);
        }
        return "redirect:/survey?selectedSurvey=" + id;
    }

}