package com.example.Questions;

import com.example.Responses.Response;
import com.example.Responses.ResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class QuestionController {
    @Autowired
    private final QuestionRepository questionRepository;

    @Autowired
    private final ResponseRepository responseRepository;

    public QuestionController(QuestionRepository questionRepository, ResponseRepository responseRepository) {
        this.questionRepository = questionRepository;
        this.responseRepository = responseRepository;
    }

    @GetMapping("/createquestion")
    public String createQuestion(Model model) {
        model.addAttribute("surveyquestion", new Question());
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
    @GetMapping("/selectedresponse")
    public String getSelectedResponse(@RequestParam(name = "selectedResponse") Long selectedResponseId, Model model) {
        // Get the selected response from the repository
        Optional<Response> selectedResponse = responseRepository.findById(selectedResponseId);

        if (selectedResponse.isPresent()) {
            // Get the corresponding survey question
            Question question = selectedResponse.get().getQuestion();

            // Get the responses for the selected survey question
            List<Response> questionResponses = responseRepository.findByQuestion(question);

            // Add the selected response and the list of question responses to the model
            model.addAttribute("selectedResponse", selectedResponse.get());
            model.addAttribute("questionResponses", questionResponses);

            // Return the name of the Thymeleaf template to use
            return "selectresponse";
        } else {
            // If the selected response does not exist, redirect to the home page
            return "redirect:/";
        }
    }
}
