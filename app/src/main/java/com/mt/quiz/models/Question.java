package com.mt.quiz.models;

import java.io.Serializable;
import java.sql.Timestamp;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Question implements Serializable {
    private String id;

    private String testId;

    private QuestionTypeEnum type;

    private String answer;

    private Integer weight;

    private String text;
}