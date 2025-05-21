package com.mt.quiz.mtquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mt.quiz.service.BaseService;

import retrofit2.Response;

public class BaseMtrQuizActivity extends AppCompatActivity {
    protected final String API_TOKEN_KEY = "API_TOKEN";

    protected String apiToken = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (intent != null) {
            apiToken = intent.getStringExtra(API_TOKEN_KEY);
        }
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
}
