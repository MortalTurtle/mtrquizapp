package com.mt.quiz.mtquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

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

public class CreateUser extends AppCompatActivity {

    private TextInputEditText loginEditText;
    private TextInputEditText passEditText;
    private Button createUserButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.create_user_screen); // Укажите ваш новый макет

        // Инициализация элементов
        loginEditText = findViewById(R.id.loginEditText);
        passEditText = findViewById(R.id.passEditText);
        createUserButton = findViewById(R.id.createUserButton);

        // Обработчик кнопки
        createUserButton.setOnClickListener(v -> createUser());
    }

    private void createUser() {
        String login = loginEditText.getText().toString().trim();
        String password = passEditText.getText().toString().trim();

        if (login.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show();
            return;
        }
        Response<String> response = UserService.create(login, password);
        if (response == null) {
            showToast("Server does not respond");
            return;
        }
        if (response.isSuccessful()) {
            showToast(response.message());
            startActivity(new Intent(this, MainActivity.class));
        }
        if (response.code() == 400) {
            var err = BaseService.parseError(response);
            showToast(err.getDescription());
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
