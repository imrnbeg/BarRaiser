package com.example.demo.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class Questions implements Serializable {

    private String quesId;
    private String ques;
    //{A : answer }
    private Map<Answer,String> options = new HashMap<>();
    //List of option as as an answer
    private List<Answer> answer;

    private List<String> quizIds;
}
