package com.mt.quiz.service;

import com.mt.quiz.models.User;
import com.mt.quiz.models.apimodels.UserRaw;

import java.io.IOException;
import java.lang.annotation.Documented;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.RequiredArgsConstructor;
import retrofit2.Call;
import retrofit2.Response;

@RequiredArgsConstructor
public class UserService extends BaseService {

    public static Response<User> getById(String id) {
        return wrapHttpRequest(apiService.getUserByIdOrUsername(id, null));
    }

    public static Response<User> getByUsername(String username) {
        return wrapHttpRequest(apiService.getUserByIdOrUsername(null, username));
    }

    /**
    @return returns id of created user
     */
    public static Response<String> create(String username, String password) {
        return wrapHttpRequest(apiService.postUser(new UserRaw(username, password)));
    }

    public void updateUser(String apiToken, String username, String password) {
        throw new UnsupportedOperationException();
    }

    public void joinGroup(String apiToken, String groupId) {
        throw new UnsupportedOperationException();
    }
    /**
     @return returns api token
     */
    public static Response<String> login(String username, String password) {
        return wrapHttpRequest(apiService.login(new UserRaw(username, password)));
    }
}