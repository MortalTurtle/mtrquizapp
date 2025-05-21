package com.mt.quiz.mtquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import com.google.android.material.textfield.TextInputEditText;
import com.mt.quiz.service.UserService;
import retrofit2.Response;

public class CreateUser extends BaseMtrQuizActivity {

    private TextInputEditText loginEditText;
    private TextInputEditText passEditText;
    private Button createUserButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.create_user_screen);

        loginEditText = findViewById(R.id.loginEditText);
        passEditText = findViewById(R.id.passEditText);
        createUserButton = findViewById(R.id.createUserButton);

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
        if (response != null && response.isSuccessful()) {
            showToast(response.message());
            startActivity(new Intent(this, MainActivity.class));
        } else this.handleErrorCodes(response);
    }
}
