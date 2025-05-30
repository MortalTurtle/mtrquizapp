package com.mt.quiz.mtquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.mt.quiz.models.Role;

public class TestInfoActivity extends BaseMtrQuizActivity {

    TextView testNameView;
    Button editButton;
    Button startButton;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_test_screen);
        Intent intent = getIntent();

    }
}
