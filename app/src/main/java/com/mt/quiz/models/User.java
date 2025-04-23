package com.mt.quiz.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class User {

    private String id;

    private String username;

    private String groupId;
}
