# SYSC4806-Team2
Public repository for the SYSC 4806 Project 2 team


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


```mermaid
classDiagram
direction BT
class MiniSurveyMonkeyApplication {
  - Logger log
  + main(String[]) void
}
class MultipleChoiceQuestion {
  - int numChoices
  - String description
  - int selectedOption
  - List~String~ options
  + getOptions() List~String~
  + getSelectedOption() int
  + setOptions(List~String~) void
  + addOption(String) void
  + removeOption(int) void
  + getOption(int) String
  + setSelectedOption(int) void
}
class NumberQuestion {
  - int maxRange
  - int minRange
  - int selectedValue
  - String description
  + getMinRange() int
  + getMaxRange() int
  + setMinRange(int) void
  + setMaxRange(int) void
  + getSelectedValue() int
  + setSelectedValue(int) void
}
class QuestionType {
<<enumeration>>
  +  NUMBER_CHOICE_LINE
  +  TEXT
  +  MULTIPLE_CHOICE
  + values() QuestionType[]
  + valueOf(String) QuestionType
}
class Survey {
  - String description
  - boolean status
  - String title
  - List~SurveyQuestion~ questions
  - Long id
  + setQuestions(List~SurveyQuestion~) void
  + setStatus(boolean) void
  + getTitle() String
  + getStatus() boolean
  + getDescription() String
  + setId(Long) void
  + setDescription(String) void
  + setTitle(String) void
  + getId() Long
  + getQuestions() List~SurveyQuestion~
}
class SurveyController {
  - SurveyRepository surveyRepository
  + viewSurveys(Model) String
  + createSurvey(Model) String
  + saveSurvey(Survey) String
  + selectSurvey(Model) String
  + showSurvey(Long, Model) String
}
class SurveyQuestion {
  - Long id
  - QuestionType questionType
  - Survey survey
  - String description
  - int questionOrder
  + getId() Long
  + getSurvey() Survey
  + getQuestionType() QuestionType
  + getOrder() int
  + setOrder(int) void
  + setSurvey(Survey) void
  + setDescription(String) void
  + setQuestionType(QuestionType) void
  + getDescription() String
  + setId(Long) void
}
class SurveyRepository {
<<Interface>>

}
class SurveyResponse {
  - Long id
  + getId() Long
  + setId(Long) void
}
class SurveyResult {
  - Long id
  + getId() Long
  + setId(Long) void
}
class TextQuestion {
  - String response
  - String description
  - int charLimit
  + setResponse(String) void
  + getResponse() String
}

MultipleChoiceQuestion  -->  SurveyQuestion 
NumberQuestion  -->  SurveyQuestion 
TextQuestion  -->  SurveyQuestion 
``` 

 Milestone 2 (Expectations):
 - Fix bugs from milestone 1
 - Add questions to surveys
 - Be able to customize individual questions (allow users to add or remove options)

