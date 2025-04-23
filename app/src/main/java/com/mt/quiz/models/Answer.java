package com.mt.quiz.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class Answer {
    private String questionId;
    private String answer;
}
