package com.mt.quiz.models.apimodels;

import androidx.annotation.Nullable;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class TestRaw {
    private String name;
    private String description;
    @Nullable
    private Integer minScoreToPass;
}
