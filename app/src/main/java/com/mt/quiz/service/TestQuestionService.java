package com.mt.quiz.service;

import com.mt.quiz.models.QuestionTypeEnum;
import com.mt.quiz.models.Question;

import java.util.List;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TestQuestionService extends BaseService {

    public List<Question> getQuestionsByTestId(String apiToken, String testId, int limit, int offset) {
        throw new UnsupportedOperationException();
    }

    public Question getQuestionById(String apiToken, String testId, String questionId) {
        throw new UnsupportedOperationException();
    }

    public String addQuestion(String apiToken, String testId, QuestionTypeEnum type, String text, int weight) {
        throw new UnsupportedOperationException();
    }

    public void updateTestQuestion(String apiToken, String testId, String questionId, String text, QuestionTypeEnum type, int weight) {
        throw new UnsupportedOperationException();
    }

    public List<String> getAnswers(String apiToken, String questionId, Boolean getFalse) {
        throw new UnsupportedOperationException();
    }

    public void addAnswers(String apiToken, String questionId, Boolean getFalse, List<String> answers) {
        throw new UnsupportedOperationException();
    }

    public void replaceAllAnswers(String apiToken, String questionId, Boolean getFalse, List<String> answers) {
        throw new UnsupportedOperationException();
    }
}