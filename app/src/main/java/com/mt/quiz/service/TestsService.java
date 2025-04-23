package com.mt.quiz.service;

import com.mt.quiz.models.Answer;
import com.mt.quiz.models.Test;
import com.mt.quiz.models.TestResult;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TestsService extends BaseService {

    public String create(String apiToken, String name, String description, Integer minScoreToBeat) {
        throw new UnsupportedOperationException();
    }

    public Test getById(String apiToken, String groupId, String id) {
        throw new UnsupportedOperationException();
    }

    public List<Test> getTestsForGroup(String apiToken, String groupId, Integer limit, Integer offset) {
        throw new UnsupportedOperationException();
    }
    public void updateTest(String apiToken, String groupId,
        String newName, String newDescription, Integer newMinScore) {
        throw new UnsupportedOperationException();
    }

    public List<TestResult> getResultList(String apiToken, Optional<String> testId, Integer limit, Integer offset) {
        throw new UnsupportedOperationException();
    }

    public void submitResults(String apiToken, String testId, List<Answer> answers) {
        throw new UnsupportedOperationException();
    }
}
