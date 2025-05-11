package com.mt.quiz.mtquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.mt.quiz.service.BaseService;
import com.mt.quiz.service.UserService;

public class GroupConnActivity extends AppCompatActivity {

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

        var response = "";
        if (response == null) {
            showToast("Server does not respond");
            return;
        }
        if (response.isSuccessful()) {
            showToast("Joining group with code: " + code);
            Intent intent = new Intent(GroupConnActivity.this, GroupInfoActivity.class); //инфо о группе
            startActivity(intent);
            finish();
        }
        if (response.code() == 404) showToast("User not found");
        if (response.code() == 400) showToast(BaseService.parseError(response).getDescription());
    }

    private void createGroup() {
        Intent intent = new Intent(this, CreateGroupActivity.class);
        startActivity(intent);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}