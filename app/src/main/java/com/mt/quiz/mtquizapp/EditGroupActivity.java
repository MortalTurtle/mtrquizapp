package com.mt.quiz.mtquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.mt.quiz.models.Group;
import com.mt.quiz.service.GroupService;

public class EditGroupActivity extends BaseMtrQuizActivity {

    private TextInputEditText groupNameEditText;
    private TextInputEditText groupDescriptionEditText;
    private Button saveButton;
    private Group group;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_group);
        Intent intent = getIntent();
        group = (Group) intent.getSerializableExtra("GROUP");
        TextView nameView = findViewById(R.id.groupNameEditPreviewText);
        nameView.setText(group.getName());
        groupNameEditText = findViewById(R.id.groupNameEditText);
        groupDescriptionEditText = findViewById(R.id.groupDescriptionEditText);
        saveButton = findViewById(R.id.saveButton);


        saveButton.setOnClickListener(v -> saveChanges());
    }
    private void saveChanges() {
        String newName = groupNameEditText.getText().toString().trim();
        String newDescription = groupDescriptionEditText.getText().toString().trim();
        newName = newName.isEmpty() ? group.getName() : newName;
        newDescription = newDescription.isEmpty() ? group.getDescription() : newName;
        var response = GroupService.editGroup(apiToken, group.getId(), new Group(group.getId(), newName, newDescription));
        if (response != null && response.isSuccessful()) {
            Toast.makeText(this, "Changes saved successfully", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK);
        } else handleErrorCodes(response);
    }
}