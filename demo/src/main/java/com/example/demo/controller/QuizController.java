package com.example.demo.controller;

import com.example.demo.Exception.QuizNotFoundException;
import com.example.demo.model.Questions;
import com.example.demo.model.Quiz;
import com.example.demo.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class QuizController {

    @Autowired
    private QuizService quizService;

    /***
     *
     * 1. API to create Quiz
     * @return quizId
     */
    @PostMapping(value = "/quiz")
    public ResponseEntity<Quiz> createQuiz(@RequestBody Quiz quiz){
       return quizService.createQuiz(quiz);
    }


    /***
     *
     * 1. API to create Questions For Quiz
     * @return quizId
     */
    @PostMapping(value = "/ques/{quizId}")
    public ResponseEntity<Quiz> createQuestions(@PathVariable("quizId")String quizId, @RequestBody List<Questions> questions) throws QuizNotFoundException {
        return quizService.createQuestions(quizId, questions);
    }
}
