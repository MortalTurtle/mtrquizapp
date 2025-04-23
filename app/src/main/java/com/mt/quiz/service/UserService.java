package com.mt.quiz.service;

import com.mt.quiz.models.User;

import java.util.List;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserService extends BaseService {

    public User getById(String id) {
        throw new UnsupportedOperationException();
    }

    public User getByUsername(String username) {
        throw new UnsupportedOperationException();
    }

    public void create(String username, String password) {
        throw new UnsupportedOperationException();
    }

    public void updateUser(String apiToken, String username, String password) {
        throw new UnsupportedOperationException();
    }

    public void joinGroup(String apiToken, String groupId) {
        throw new UnsupportedOperationException();
    }

    public String login(String username, String password) {
        throw new UnsupportedOperationException();
    }
}