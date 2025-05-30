package com.mt.quiz.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class User implements Serializable {

    private String id;

    private String username;

    private String groupId;
}
