package com.mt.quiz.mtquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.mt.quiz.service.BaseService;
import com.mt.quiz.service.GroupService;
import com.mt.quiz.service.UserService;

public class GroupConnActivity extends BaseMtrQuizActivity {

    private TextInputEditText codeEditText;
    private Button joinGroupButton;
    private Button createGroupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connection_screen);

        codeEditText = findViewById(R.id.loginEditText);
        joinGroupButton = findViewById(R.id.JoinGroupButton);
        createGroupButton = findViewById(R.id.createUserButton);

        joinGroupButton.setOnClickListener(v -> joinGroup());
        createGroupButton.setOnClickListener(v -> createGroup());
    }

    private void joinGroup() {
        String code = codeEditText.getText().toString().trim();

        if (code.isEmpty()) {
            Toast.makeText(this, "Enter group code", Toast.LENGTH_SHORT).show();
            return;
        }

        var response = UserService.joinGroup(apiToken, code);
        if (response != null && response.isSuccessful()) {
            showToast("Joining group with code: " + code);
            Intent intent = new Intent(GroupConnActivity.this, GroupInfoActivity.class);
            intent.putExtra("GROUP_ID", code);
            intent.putExtra(this.API_TOKEN_KEY, apiToken);
            startActivity(intent);
            finish();
        } else handleErrorCodes(response);
    }

    private void createGroup() {
        Intent intent = new Intent(this, CreateGroupActivity.class);
        intent.putExtra(API_TOKEN_KEY, apiToken);
        startActivity(intent);
    }
}