package com.example.Surveys;

import com.example.Questions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;

@Controller
public class SurveyController {
    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private QuestionRepository questionRepository;

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
    public String selectQuestion(@RequestParam("survey") Long surveyId, Model model) {
        model.addAttribute("surveyId", surveyId);
        model.addAttribute("surveyquestion", new Question());
        return "addquestion";
    }

    @PostMapping("/addtextquestion/{id}")
    public String textQuestion(@ModelAttribute TextQuestion textQuestion, @PathVariable("id") Long surveyId) {
        System.out.println(textQuestion.getId());

        Optional<Survey> surveyOptional = surveyRepository.findById(surveyId);
        if (!surveyOptional.isPresent()) {
            // handle the case where the survey with the given id does not exist
            return "error";
        }
        Survey survey = surveyOptional.get();
        System.out.println("id: " + survey.getId());
        textQuestion.setSurvey(survey);
        //questionRepository.save(textQuestion);

        List<Question> questions = survey.getQuestions();
        questions.add(textQuestion);
        survey.setQuestions(questions);
        surveyRepository.save(survey);

        return "redirect:/survey?selectedSurvey=" + survey.getId();
    }

    @PostMapping("/addnumberquestion/{id}")
    public String saveNumberQuestion(@ModelAttribute NumberQuestion numberQuestion, @PathVariable("id") Long surveyId) {
        System.out.println(numberQuestion.getMaxRange());

        Optional<Survey> surveyOptional = surveyRepository.findById(surveyId);
        if (!surveyOptional.isPresent()) {
            // handle the case where the survey with the given id does not exist
            return "error";
        }
        Survey survey = surveyOptional.get();
        System.out.println("id: " + survey.getId());
        numberQuestion.setSurvey(survey);
        //questionRepository.save(numberQuestion);

        List<Question> questions = survey.getQuestions();
        questions.add(numberQuestion);
        survey.setQuestions(questions);
        surveyRepository.save(survey);

        return "redirect:/survey?selectedSurvey=" + survey.getId();
    }

    @PostMapping("/addmcquestion/{id}")
    public String saveMcQuestion(@RequestParam("listOfOptions") String list, @ModelAttribute MultipleChoiceQuestion mcQuestion, @PathVariable("id") Long surveyId) {
        String[] listArr = list.split(",");
        for (String s : listArr) {
            mcQuestion.addOption(s);
        }

        Optional<Survey> surveyOptional = surveyRepository.findById(surveyId);
        if (!surveyOptional.isPresent()) {
            // handle the case where the survey with the given id does not exist
            return "error";
        }
        Survey survey = surveyOptional.get();
        System.out.println("id: " + survey.getId());
        mcQuestion.setSurvey(survey);
        //questionRepository.save(mcQuestion);

        List<Question> questions = survey.getQuestions();
        questions.add(mcQuestion);
        survey.setQuestions(questions);
        surveyRepository.save(survey);

        return "redirect:/survey?selectedSurvey=" + survey.getId();
    }

    @PostMapping("/addquestion")
    public String addQuestion(@RequestParam("selectedSurvey") Long selectedSurveyId, @RequestParam("selectedQuestion") Long selectedQuestionId, Model model) {
        Optional<Survey> surveyOptional = surveyRepository.findById(selectedSurveyId);
        if (!surveyOptional.isPresent()) {
            // handle the case where the survey with the given id does not exist
            return "error";
        }
        Survey survey = surveyOptional.get();

        Optional<Question> questionOptional = questionRepository.findById(selectedQuestionId);
        if (!questionOptional.isPresent()) {
            // handle the case where the question with the given id does not exist
            return "error";
        }
        Question question = questionOptional.get();

        List<Question> questions = survey.getQuestions();
        questions.add(question);
        survey.setQuestions(questions);
        surveyRepository.save(survey);

        question.setSurvey(survey);
        questionRepository.save(question);

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

    /**
    @GetMapping("/survey/{id}/viewresponses")
    public String viewResponses(@PathVariable("id") Long id, Model model) {
        Survey survey = surveyRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid survey ID: " + id));
        model.addAttribute("survey", survey);
        return "viewresponses";
    }
     **/

    @GetMapping("/viewresults")
    public String viewResults(Model model) {
        List<Survey> surveys = (List<Survey>) surveyRepository.findAll();
        model.addAttribute("surveys", surveys);
        model.addAttribute("question", new Question()); // add this line
        return "viewresults";
    }


    @GetMapping("/surveys/{id}/questions")
    public String viewResponses(@PathVariable("id") Long id, Model model) {
        // Retrieve the survey and questions data based on the surveyId
        Optional<Survey> survey = surveyRepository.findById(id);
        List<Question> questions = questionRepository.findBySurvey(Optional.of(survey.get()));

        // Add the survey and questions data to the model for use in the Thymeleaf template
        model.addAttribute("survey", survey.get());
        model.addAttribute("questions", questions);

        return "viewresponses";
    }

}
