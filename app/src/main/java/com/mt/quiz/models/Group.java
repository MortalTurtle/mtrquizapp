package com.mt.quiz.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Group implements Serializable {
    private String id;

    private String name;

    private String description;

    @Override
    public String toString() {
        return id + name + " " + description;
    }
}
