package com.mt.quiz.models.apimodels;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class BadRequestError {
    private String description;
    private String type;
}
