package com.example.demo.service;

import com.example.demo.Exception.QuizNotFoundException;
import com.example.demo.model.Questions;
import com.example.demo.model.Quiz;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class QuizService {

    @Autowired
    QuizRepository quizRepository;

    public ResponseEntity<Quiz> createQuiz(Quiz quiz){
        String quizId = UUID.randomUUID().toString();

        //Creating a Quiz
       QuizRepository.quizMap.put(quizId,quiz);
       quiz.setQuizId(quizId);

        return ResponseEntity.ok(quiz);
    }

    public ResponseEntity<Quiz> createQuestions(String quizId, List<Questions> questions) throws QuizNotFoundException {
        if (!QuizRepository.quizMap.containsKey(quizId)){
            throw new QuizNotFoundException();
        }

        Quiz newQuiz = QuizRepository.quizMap.get(quizId);
        newQuiz.setNoOfQuestions(newQuiz.getNoOfQuestions() + questions.size());
        List<Questions> newQuestions =  new ArrayList<>();
        questions.stream()
                .forEach(a->
                        {
                            Questions question = new Questions();
                            String quesId = a.getQuesId();
                                if(Objects.isNull(quesId)){
                                    quesId = UUID.randomUUID().toString();
                                }
                                // Question already exist
                            if(QuestionRepository.questionsMap.containsKey(quesId)){
                                question = QuestionRepository.questionsMap.get(quesId);
                                question.getQuizIds().add(quizId);
                                QuestionRepository.questionsMap.replace(quesId,question);
                            }
                            else {
                                List<String> quizIds = new ArrayList<>();
                                quizIds.add(quizId);
//                                 question = Questions.builder()
//                                        .quesId(quesId)
//                                        .ques(a.getQues())
//                                        .options(a.getOptions())
//                                        .answer(a.getAnswer())
//                                         .quizIds(quizIds)
//                                        .build();
                                QuestionRepository.questionsMap.put(quesId,question);
                            }
                            newQuestions.add(question);
                        }
                );
         newQuiz.setQuestions(newQuestions);
         QuizRepository.quizMap.replace(quizId,newQuiz);
         return ResponseEntity.ok(newQuiz);
    }
}
