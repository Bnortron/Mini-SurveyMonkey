package com.example.Responses;

import com.example.Questions.Question;
import com.example.Questions.QuestionRepository;
import com.example.Questions.QuestionType;
import com.example.Surveys.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String submitResponses(@RequestParam Map<String, String> responseParams) {
        for (Map.Entry<String, String> entry : responseParams.entrySet()) {
            if (entry.getKey().startsWith("question")) {
                Long questionId = Long.parseLong(entry.getKey().substring(8));
                Optional<Question> optionalQuestion = questionRepository.findById(questionId);
                if (optionalQuestion.isPresent()) {
                    Question question = optionalQuestion.get();
                    Response response = new Response();
                    response.setQuestion(question);
                    if (question.getQuestionType() == QuestionType.MULTIPLE_CHOICE) {
                        response.setResponse(entry.getValue());
                    } else if (question.getQuestionType() == QuestionType.NUMBER_CHOICE_LINE) {
                        response.setResponse(entry.getValue());
                    } else if (question.getQuestionType() == QuestionType.TEXT) {
                        response.setResponse(entry.getValue());
                    }
                    responseRepository.save(response);
                }
            }
        }
        return "redirect:/";
    }

}



