package com.mt.quiz.mtquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mt.quiz.models.Role;
import com.mt.quiz.service.BaseService;

import java.nio.charset.StandardCharsets;

import retrofit2.Response;

public class BaseMtrQuizActivity extends AppCompatActivity {
    protected final String API_TOKEN_KEY = "API_TOKEN";

    protected String apiToken = null;
    protected String groupId = null;
    protected Role groupRole = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (intent == null) return;
        apiToken = intent.getStringExtra(API_TOKEN_KEY);
        groupId = intent.getStringExtra("GROUP_ID");
        String roleString = intent.getStringExtra("GROUP_ROLE");
        if (roleString != null) groupRole = Role.valueOf(roleString);
    }
    protected<T> void handleErrorCodes(Response<T> response) {
        if (response == null || response.code() == 500) {
            showToast("Server error");
            return;
        }
        if (response.code() == 404) showToast("User not found");
        if (response.code() == 400) showToast(BaseService.parseError(response).getDescription());
        if (response.code() == 401) showToast("Unauthorized access");
    }

    protected void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    protected void startActivityWithAddedExtras(Intent intent) {
        intent.putExtra(API_TOKEN_KEY, apiToken);
        intent.putExtra("GROUP_ID", groupId);
        if (groupRole != null)
            intent.putExtra("GROUP_ROLE", groupRole.name());
        startActivity(intent);
    }
}
