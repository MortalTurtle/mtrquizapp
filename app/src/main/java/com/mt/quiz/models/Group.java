package com.mt.quiz.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Group {
    private String id;

    private String name;

    private String description;

    @Override
    public String toString() {
        return id + name + " " + description;
    }
}
