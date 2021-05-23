package com.example.demo.repository;

import com.example.demo.model.Quiz;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class QuizRepository {

    public static Map<String, Quiz> quizMap = new HashMap<>();

}
