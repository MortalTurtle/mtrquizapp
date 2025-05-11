package com.mt.quiz.mtquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.mt.quiz.models.Group;
import com.mt.quiz.service.GroupService;

import retrofit2.Response;

public class CreateGroupActivity extends AppCompatActivity {

    private TextInputEditText groupNameEditText;
    private TextInputEditText groupDescriptionEditText;
    private TextInputEditText groupCodeEditText;
    private Button createGroupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        groupNameEditText = findViewById(R.id.groupNameEditText);
        groupDescriptionEditText = findViewById(R.id.groupDescriptionEditText);
        groupCodeEditText = findViewById(R.id.groupCodeEditText);
        createGroupButton = findViewById(R.id.createGroupButton);

        createGroupButton.setOnClickListener(v -> createGroup());
    }

    private void createGroup() {
        String name = groupNameEditText.getText().toString().trim();
        String description = groupDescriptionEditText.getText().toString().trim();
        String code = groupCodeEditText.getText().toString().trim();
        String apiToken = "";

        if (name.isEmpty()) {
            Toast.makeText(this, "Enter the name of the group", Toast.LENGTH_SHORT).show();
            return;
        }

        Group group = new Group(name, description, code);

        Response<Group> response = GroupService.create(apiToken,name,description);
        if (response == null) {
            Toast.makeText(this, "Connection error", Toast.LENGTH_SHORT).show();
            return;
        }

        if (response.isSuccessful()) {
            Toast.makeText(this, "The group has been created!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(CreateGroupActivity.this, GroupConnActivity.class);
            intent.putExtra("isAdmin", true);
            //переход на вход группы и нужно пометить что пользователь админ
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Error: " + response.message(), Toast.LENGTH_SHORT).show();
        }
    }
}