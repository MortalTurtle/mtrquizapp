package com.mt.quiz.service;

import androidx.annotation.Nullable;

import com.mt.quiz.models.Answer;
import com.mt.quiz.models.Test;
import com.mt.quiz.models.TestResult;
import com.mt.quiz.models.apimodels.TestRaw;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import retrofit2.Response;

@RequiredArgsConstructor
public class TestsService extends BaseService {

    public static Response<String> create(String apiToken, String groupId ,String name, String description, Integer minScoreToBeat) {
        return wrapHttpRequest(apiService.createTest(apiToken, groupId, new TestRaw(name, description, minScoreToBeat)));
    }

    public Test getById(String apiToken, String groupId, String id) {
        throw new UnsupportedOperationException();
    }

    public static Response<List<Test>> getTestsForGroup(String apiToken, String groupId, @Nullable Integer limit, @Nullable Integer offset) {
        return wrapHttpRequest(apiService.getTestsForGroup(apiToken, groupId, offset, limit));
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
