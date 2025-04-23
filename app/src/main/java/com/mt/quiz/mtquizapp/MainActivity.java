package com.mt.quiz.mtquizapp;

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
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        createUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });
    }

    private void login() {
        String password = passwordEditText.getText().toString().trim();
        String login = loginEditText.getText().toString().trim();

        if (password.isEmpty()) {
            showToast("Please enter the password");
            return;
        }
        if (login.isEmpty()) {
            showToast("Please enter your login");
            return;
        }
    }

    private void createUser() {
        String password = passwordEditText.getText().toString().trim();
        String login = loginEditText.getText().toString().trim();

        if (password.isEmpty()) {
            showToast("Please enter the code");
            return;
        }

        // Здесь проверяем пароль (замените на свою логику)
        if (password.equals("123456")) { // Пример проверки
            showToast("Access granted!");
            // Переход на следующий экран:
            // startActivity(new Intent(this, NextActivity.class));
        } else {
            showToast("Invalid code, please try again");
            passwordEditText.setText("");
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}