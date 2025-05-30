package com.mt.quiz.service;

import com.mt.quiz.models.User;
import com.mt.quiz.models.apimodels.UserRaw;
import lombok.RequiredArgsConstructor;
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

    public static Response<Void> updateUser(String apiToken, String username, String password) {
        return wrapHttpRequest(apiService.editUser(apiToken, new UserRaw(username, password)));
    }

    public static Response<Void> joinGroup(String apiToken, String groupId) {
        return wrapHttpRequest(apiService.joinGroup(apiToken, groupId));
    }
    /**
     @return returns api token
     */
    public static Response<String> login(String username, String password) {
        return wrapHttpRequest(apiService.login(new UserRaw(username, password)));
    }
}