package com.mt.quiz.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class TestResult implements Serializable {

    private String userId;

    private String testId;

    private Integer score;
}
