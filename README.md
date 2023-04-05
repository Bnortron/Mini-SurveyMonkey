# SYSC4806-Team2

Public repository for the SYSC 4806 Project 2 team

![Status](https://github.com/Bnortron/SYSC4806-Team2/actions/workflows/integration.yaml/badge.svg)

## Milestone 1:

- Setup Github Actions
- Created the models for the survey questions (TextQuestion, MultipleChoiceQuestion, NumberLineQuestion)
- Created the controllers for creating the surveys
- Created the html page for creating surveys
- Wrote unit tests

![965DF801-AD2A-4617-89AC-37C00BA40EB3](https://user-images.githubusercontent.com/72236016/223782303-df8389c6-d7f4-4e5b-8bfa-3421850777d3.jpg)
![B4657871-B36D-4B71-AC46-240752D937BE](https://user-images.githubusercontent.com/72236016/223782400-b2a0d600-f998-4b9f-8331-2609a38a62ef.jpg)
![3471BF90-C4FB-4DFB-AAEB-272DF71CFFA7](https://user-images.githubusercontent.com/72236016/223782412-26d3f17f-645e-47ef-88f3-05a627b0ca31.jpg)
![3B4BC6D0-B7D9-4411-B588-E0C5C58ADA62](https://user-images.githubusercontent.com/72236016/223782424-4723c43b-040b-460d-b02e-a03ae0d54f9a.jpg)
![1B65D808-DE15-4498-BE3B-E5D16C65802F](https://user-images.githubusercontent.com/72236016/223782443-869fff06-2271-4d30-8326-10813c6091c9.jpg)

### Milestone 2 (Expectations):

- Fix bugs from milestone 1
- Add questions to surveys
- Be able to customize individual questions (allow users to add or remove options)


## Milestone 2: Alpha Release

- Added functionality for creating 3 types of Survey questions
- Added functionality for adding Survey questions to a Survey
- Added Activate/Deactivate functionality to Surveys
- Added functionality to display Survey with all added questions
- Added response functionality when viewing an active Survey that has questions
- Unit testing for MultipleChoiceQuestion, NumberQuestion, TextQuestion, QuestionController, SurveyController


### Schemas:

[![M2-Schemas.jpg](https://i.postimg.cc/3RJdBLPP/M2-Schemas.jpg)](https://postimg.cc/94KmXBXp)


### UML Diagram:
```mermaid
classDiagram
direction BT
class MiniSurveyMonkeyApplication {
  - Logger log
  + main(String[]) void
}
class MultipleChoiceQuestion {
  - int selectedOption
  - List~String~ options
  - int numChoices
  + getOptions() List~String~
  + removeOption(int) void
  + getSelectedOption() int
  + getOption(int) String
  + addOption(String) void
  + setOptions(List~String~) void
  + setSelectedOption(int) void
}
class NumberQuestion {
  - int minRange
  - int maxRange
  - int selectedValue
  + setMinRange(int) void
  + getMaxRange() int
  + getMinRange() int
  + setSelectedValue(int) void
  + getSelectedValue() int
  + setMaxRange(int) void
}
class QuestionController {
  - QuestionRepository questionRepository
  + selectQuestion(Model) String
  + createQuestion(Model) String
  + textQuestion(TextQuestion) String
  + saveNumberQuestion(NumberQuestion) String
  + saveMcQuestion(String, MultipleChoiceQuestion) String
}
class QuestionRepository {
<<Interface>>
  + findBySurvey(Survey) List~SurveyQuestion~
}
class QuestionType {
<<enumeration>>
  +  MULTIPLE_CHOICE
  +  NUMBER_CHOICE_LINE
  +  TEXT
  + valueOf(String) QuestionType
  + values() QuestionType[]
}
class Survey {
  - String description
  - List~SurveyQuestion~ questions
  - boolean active
  - Long id
  - String title
  + setId(Long) void
  + setDescription(String) void
  + getId() Long
  + setTitle(String) void
  + getActive() boolean
  + getTitle() String
  + setQuestions(List~SurveyQuestion~) void
  + setActive(boolean) void
  + getDescription() String
  + getQuestions() List~SurveyQuestion~
}
class SurveyController {
  - SurveyRepository surveyRepository
  - QuestionRepository questionRepository
  + createSurvey(Model) String
  + viewSurveys(Model) String
  + selectSurvey(Model) String
  + addQuestion(Long, Long, Model) String
  + selectQuestion(Model) String
  + deactivateSurvey(Long) String
  + saveSurvey(Survey) String
  + showSurvey(Long, Model) String
  + activateSurvey(Long) String
}
class SurveyQuestion {
  # QuestionType questionType
  # Long id
  # Survey survey
  # int questionOrder
  # String description
  + setId(Long) void
  + getQuestionType() QuestionType
  + getSurvey() Survey
  + setQuestionType(QuestionType) void
  + getDescription() String
  + getOrder() int
  + setSurvey(Survey) void
  + setDescription(String) void
  + setOrder(int) void
  + getId() Long
}
class SurveyRepository {
<<Interface>>

}
class SurveyResponse {
  - Long id
  + getId() Long
  + setId(Long) void
}
class SurveyResponseController {
  - SurveyResponseRepository surveyResponseRepository
  + createResponse(SurveyResponse) String
}
class SurveyResponseRepository {
<<Interface>>

}
class SurveyResult {
  - Long id
  + getId() Long
  + setId(Long) void
}
class TextQuestion {
  - int charLimit
  - String response
  + getResponse() String
  + setCharLimit(int) void
  + setResponse(String) void
  + getCharLimit() int
}

MultipleChoiceQuestion  -->  SurveyQuestion 
NumberQuestion  -->  SurveyQuestion 
TextQuestion  -->  SurveyQuestion 
```

### Milestone 3 Expectations:

- Clean up UI (Custom Thymeleaf template, NAV bar, Format for Home page)
- Fix bugs that cause duplicate Survey if user refreshes page when trying to add a question
- Determine final scope of project
- Analyze Survey results and produce graphs/plots based on Survey questions
