package com.mt.quiz.mtquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.mt.quiz.service.UserService;

public class MainActivity extends BaseMtrQuizActivity {

    private TextInputEditText loginEditText;
    private TextInputEditText passwordEditText;
    private Button loginButton;
    private Button createUserButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        loginEditText = findViewById(R.id.usernameLoginText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        createUserButton = findViewById(R.id.createUserButton);
        loginButton.setOnClickListener(v -> login());
        createUserButton.setOnClickListener(v -> createUser());
    }

    private void login() {
        String password = passwordEditText.getText().toString().trim();
        String login = loginEditText.getText().toString().trim();

        if (login.isEmpty()) {
            showToast("Please enter your login");
            return;
        }
        if (password.isEmpty()) {
            showToast("Please enter the password");
            return;
        }
        var response = UserService.login(login, password);
        if (response != null && response.isSuccessful()) {
            showToast("Login successful!");
            var userResponse = UserService.getByUsername(login);
            if (userResponse != null && response.isSuccessful() && userResponse.body().getGroupId() != null) {
                Intent groupIntent = new Intent(MainActivity.this, GroupInfoActivity.class);
                groupIntent.putExtra("GROUP_ID", userResponse.body().getGroupId());
                groupIntent.putExtra(this.API_TOKEN_KEY, response.body());
                startActivity(groupIntent);
                return;
            }
            Intent intent = new Intent(MainActivity.this, GroupConnActivity.class);
            intent.putExtra(this.API_TOKEN_KEY, response.body());
            startActivity(intent);
        } else handleErrorCodes(response);
    }

    private void createUser() {
        Intent intent =  new Intent(MainActivity.this, CreateUser.class);
        startActivity(intent);
    }
}