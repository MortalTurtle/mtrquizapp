package com.mt.quiz.mtquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.mt.quiz.models.Group;
import com.mt.quiz.service.GroupService;
import com.mt.quiz.service.UserService;

import retrofit2.Response;

public class CreateGroupActivity extends BaseMtrQuizActivity {

    private TextInputEditText groupNameEditText;
    private TextInputEditText groupDescriptionEditText;
    private Button createGroupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        groupNameEditText = findViewById(R.id.groupNameEditText);
        groupDescriptionEditText = findViewById(R.id.groupDescriptionEditText);
        createGroupButton = findViewById(R.id.createGroupButton);

        createGroupButton.setOnClickListener(v -> createGroup());
    }

    private void createGroup() {
        String name = groupNameEditText.getText().toString().trim();
        String description = groupDescriptionEditText.getText().toString().trim();

        if (name.isEmpty()) {
            Toast.makeText(this, "Enter the name of the group", Toast.LENGTH_SHORT).show();
            return;
        }
        //group_id, error = GroupService.create(apiToken,name,description)
        Response<String> response = GroupService.create(apiToken,name,description);
        if (response != null && response.isSuccessful()) {
            Toast.makeText(this, "The group has been created!", Toast.LENGTH_SHORT).show();
            UserService.joinGroup(apiToken, response.body());
            Intent intent = new Intent(CreateGroupActivity.this, GroupInfoActivity.class);
            intent.putExtra(API_TOKEN_KEY, apiToken);
            intent.putExtra("GROUP_ID", response.body());
            startActivity(intent);
            finish();
        } handleErrorCodes(response);
    }
}