package com.example;

import com.example.objectdb.ObjectDBSurveyQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class QuestionController {
    @Autowired
    private final ObjectDBSurveyQuestionRepository questionRepository;

    public QuestionController(ObjectDBSurveyQuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @GetMapping("/createquestion")
    public String createQuestion(Model model) {
        model.addAttribute("surveyquestion", new SurveyQuestion());
        //model.addAttribute("listOfOptions", new ArrayList<String>());
        return "createquestion";
    }


    /**
     * Method that handles POST request for saving a new Survey to the database
     *
     * @param textQuestion the Survey to save to the database
     * @return redirects user back home (to be changed)
     */
    @PostMapping("/textquestion")
    public String textQuestion(@ModelAttribute TextQuestion textQuestion) {
        System.out.println(textQuestion.getId());
        questionRepository.save(textQuestion);
        return "index";
    }

    /**
     * Method that handles POST request for saving a new Survey to the database
     *
     * @param numberQuestion the Survey to save to the database
     * @return redirects user back home (to be changed)
     */
    @PostMapping("/numberquestion")
    public String saveNumberQuestion(@ModelAttribute NumberQuestion numberQuestion) {
        System.out.println(numberQuestion.getMaxRange());
        questionRepository.save(numberQuestion);
        return "index";
    }

    /**
     * Method that handles POST request for saving a new Survey to the database
     *
     * @param mcQuestion the Survey to save to the database
     * @return redirects user back home (to be changed)
     */
    @PostMapping("/mcquestion")
    public String saveMcQuestion(@RequestParam("listOfOptions") String list, @ModelAttribute MultipleChoiceQuestion mcQuestion) {
        String[] listArr = list.split(",");
        for (String s : listArr) {
            mcQuestion.addOption(s);
        }
        questionRepository.save(mcQuestion);
        return "index";
    }

    /**
     * Select a Survey to view (Displays Survey properties - not survey questions)
     *
     * @param model
     * @return
     */
    @GetMapping("/viewquestions")
    public String selectQuestion(Model model) {
        Iterable<SurveyQuestion> questions = questionRepository.findAll();
        model.addAttribute("surveyquestions", questions);
        return "viewquestions";
    }
}
