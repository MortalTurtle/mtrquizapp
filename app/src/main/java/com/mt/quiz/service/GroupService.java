package com.mt.quiz.service;

import com.mt.quiz.models.Group;

import lombok.RequiredArgsConstructor;
import retrofit2.Response;

public class GroupService extends BaseService {

    public static Response<Group> getById(String apiToken, String id) {
        return wrapHttpRequest(apiService.getGroup(apiToken, id));
    }

    public static Response<Group> editGroup(String apiToken, String id, Group editedGroup) {
        return wrapHttpRequest(apiService.editGroup(apiToken, id, editedGroup));
    }

    /**
     @return returns id of created group
     */
    public static Response<String> create(String apiToken, String name, String description) {
        return wrapHttpRequest(apiService.createGroup(apiToken, new Group(null, name, description)));
    }

    public static Response<String> getUserRole(String apiToken, String id) {
        return wrapHttpRequest(apiService.getUserRole(apiToken, id));
    }
}