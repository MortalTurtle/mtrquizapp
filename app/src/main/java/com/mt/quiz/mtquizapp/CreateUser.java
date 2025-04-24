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

public class CreateUser extends AppCompatActivity {

    private TextInputEditText loginEditText;
    private TextInputEditText passEditText;
    private Button createUserButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        } else {
            // Логика регистрации
            Toast.makeText(this, "Пользователь создан!", Toast.LENGTH_SHORT).show();
        }
    }
}
