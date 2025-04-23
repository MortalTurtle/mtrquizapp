package com.mt.quiz.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class QuestionType {

    private QuestionTypeEnum type;

    private String humanReadableName;

    private String description;
}
