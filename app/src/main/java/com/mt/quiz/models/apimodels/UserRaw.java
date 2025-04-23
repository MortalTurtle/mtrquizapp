package com.mt.quiz.models.apimodels;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRaw {
    private String username;
    private String password;
}
