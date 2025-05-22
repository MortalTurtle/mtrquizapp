package com.mt.quiz.mtquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.mt.quiz.models.Role;

public class CreateTestActivity extends BaseMtrQuizActivity {
    private String groupId;
    private Role groupRole;

    private TextInputEditText testNameEditText;
    private TextInputEditText testDescriptionEditText;
    private TextInputEditText minScoreEditText;
    private Button createTestButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_test_screen);
        Intent intent = getIntent();
        groupId = intent.getStringExtra("GROUP_ID");
        groupRole = Role.valueOf(intent.getStringExtra("GROUP_ROLE"));
        testNameEditText = findViewById(R.id.testNameEditText);
        testDescriptionEditText = findViewById(R.id.testDescriptionEditText);
        minScoreEditText = findViewById(R.id.minScoreEditText);
        createTestButton = findViewById(R.id.createTestButtonCreateScreen);
        createTestButton.setOnClickListener(v -> createTest());
    }

    private void createTest() {

    }
}
