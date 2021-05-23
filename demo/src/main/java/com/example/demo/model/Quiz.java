package com.example.demo.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder

public class Quiz {

    private String quizId;
    private int noOfQuestions = 0;
    private List<Questions> questions;
}
