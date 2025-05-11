package com.mt.quiz.mtquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.mt.quiz.models.Group;
import com.mt.quiz.service.GroupService;

public class EditGroupActivity extends AppCompatActivity {

    private TextInputEditText groupNameEditText;
    private TextInputEditText groupDescriptionEditText;
    private Button saveButton;

    private Group currentGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_group);

        groupNameEditText = findViewById(R.id.groupNameEditText);
        groupDescriptionEditText = findViewById(R.id.groupDescriptionEditText);
        saveButton = findViewById(R.id.saveButton);

        loadGroupData(); //загрузка группы

        saveButton.setOnClickListener(v -> saveChanges());
    }

    private void loadGroupData() {

        currentGroup = (Group) getIntent().getSerializableExtra("GROUP_DATA");

        if (currentGroup != null) {
            groupNameEditText.setText(currentGroup.getName());
            groupDescriptionEditText.setText(currentGroup.getDescription());
        } else {

            currentGroup = new Group("", "", "");
            Toast.makeText(this, "Group data not loaded", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveChanges() {
        String newName = groupNameEditText.getText().toString().trim();
        String newDescription = groupDescriptionEditText.getText().toString().trim();

        if (newName.isEmpty()) {
            groupNameEditText.setError("Group name cannot be empty");
            return;
        }


        currentGroup.setName(newName);
        currentGroup.setDescription(newDescription);
        boolean success = GroupService.updateGroup(currentGroup);

        if (success) {
            Toast.makeText(this, "Changes saved successfully", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK);
            Intent intent= new Intent(EditGroupActivity.this, GroupInfoActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Failed to save changes", Toast.LENGTH_SHORT).show();
        }
    }
}