package com.mt.quiz.mtquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.mt.quiz.models.apimodels.UserRaw;
import com.mt.quiz.service.BaseService;
import com.mt.quiz.service.UserService;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText loginEditText;
    private TextInputEditText passwordEditText;
    private Button loginButton;
    private Button createUserButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Настройка отступов для системных баров (EdgeToEdge)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Инициализация элементов
        loginEditText = findViewById(R.id.loginEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        createUserButton = findViewById(R.id.createUserButton);

        // Обработчик нажатия кнопки
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
        if (response == null) {
            showToast("Server does not respond");
            return;
        }
        if (response.isSuccessful()) showToast("Login successful!" + response.body());
        if (response.code() == 404) showToast("User not found");
        if (response.code() == 400) showToast(BaseService.parseError(response).getDescription());
    }

    private void createUser() {
        Intent intent =  new Intent(MainActivity.this, CreateUser.class);
        startActivity(intent);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}