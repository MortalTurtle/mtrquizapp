package com.mt.quiz.mtquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mt.quiz.models.Role;
import com.mt.quiz.models.Group;
import com.mt.quiz.service.GroupService;

public class GroupInfoActivity extends BaseMtrQuizActivity {

    private Button editGroupButton;
    private Button manageTestsButton;
    private Button joinAnotherGroupButton;
    private Role userRole;
    private String groupId;
    private Group group;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_info);

        Intent intent = getIntent();
        groupId = intent.getStringExtra("GROUP_ID");
        var groupResponse = GroupService.getById(apiToken, groupId);
        var roleResponse = GroupService.getUserRole(apiToken, groupId);
        if (groupResponse == null || !groupResponse.isSuccessful() ||
                roleResponse == null || !roleResponse.isSuccessful())
            throw new RuntimeException();
        group = groupResponse.body();
        TextView nameView = findViewById(R.id.groupNameTextView);
        TextView descriptionView = findViewById(R.id.groupDescriptionTextView);
        TextView codeView = findViewById(R.id.groupCodeTextView);
        nameView.setText(group.getName());
        descriptionView.setText(group.getName());
        codeView.setText(group.getId());
        userRole = Role.valueOf(roleResponse.body());
        editGroupButton = findViewById(R.id.editGroupButton);
        manageTestsButton = findViewById(R.id.manageTestsButton);
        joinAnotherGroupButton = findViewById(R.id.joinAnotherGroupButton);
        editGroupButton.setVisibility(userRole == Role.kOwner ? View.VISIBLE : View.GONE);
        manageTestsButton.setOnClickListener(v -> {
            Intent testsIntent = new Intent(GroupInfoActivity.this, TestsActivity.class);
            testsIntent.putExtra(this.API_TOKEN_KEY, apiToken);
            testsIntent.putExtra("GROUP_ID", groupId);
            testsIntent.putExtra("GROUP_ROLE", userRole.name());
            startActivity(testsIntent);
        });

        editGroupButton.setOnClickListener(v -> {
            if (userRole == Role.kOwner) {
                Intent editIntent = new Intent(GroupInfoActivity.this, EditGroupActivity.class);
                editIntent.putExtra("GROUP", group);
                editIntent.putExtra(API_TOKEN_KEY, apiToken);
                startActivity(editIntent);
            } else  showToast("Only admin can edit the group");
        });

        joinAnotherGroupButton.setOnClickListener(v -> {
            Intent groupIntent = new Intent(GroupInfoActivity.this, GroupConnActivity.class);
            startActivity(groupIntent);
        });
    }
}