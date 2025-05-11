package com.mt.quiz.mtquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class GroupInfoActivity extends AppCompatActivity {

    private Button editGroupButton;
    private Button manageTestsButton;
    private boolean isAdmin = false; // Это значение должно приходить из предыдущего экрана или из API

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_info);

        // Получаем информацию о правах пользователя из Intent
        Intent intent = getIntent();
        if (intent != null) {
            isAdmin = intent.getBooleanExtra("isAdmin", false);
        }

        editGroupButton = findViewById(R.id.editGroupButton);
        manageTestsButton = findViewById(R.id.manageTestsButton);

        // Настраиваем видимость кнопок в зависимости от прав
        editGroupButton.setVisibility(isAdmin ? View.VISIBLE : View.GONE);

        // Обработчики нажатий
        manageTestsButton.setOnClickListener(v -> {
            // Переход к тестам доступен всем
            Intent testsIntent = new Intent(GroupInfoActivity.this, TestsActivity.class);
            startActivity(testsIntent);
        });

        editGroupButton.setOnClickListener(v -> {
            // Редактирование группы доступно только админу
            if (isAdmin) {
                Intent editIntent = new Intent(GroupInfoActivity.this, EditGroupActivity.class);
                startActivity(editIntent);
            } else {
                Toast.makeText(this, "Only admin can edit the group", Toast.LENGTH_SHORT).show();
            }
        });
    }
}