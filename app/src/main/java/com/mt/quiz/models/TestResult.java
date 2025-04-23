package com.mt.quiz.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class TestResult {

    private String userId;

    private String testId;

    private Integer score;
}
