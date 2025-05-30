package com.mt.quiz.mtquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mt.quiz.models.Role;
import com.mt.quiz.models.Test;
import com.mt.quiz.service.TestsService;

import java.util.ArrayList;
import java.util.List;

public class TestsActivity extends BaseMtrQuizActivity {

    private RecyclerView testsRecyclerView;
    private TextView emptyStateText;
    private Button createTestButton;
    private List<Test> testList = new ArrayList<>();
    private String groupId;
    private Role groupRole;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tests);
        Intent intent = getIntent();
        groupId = intent.getStringExtra("GROUP_ID");
        groupRole = Role.valueOf(intent.getStringExtra("GROUP_ROLE"));
        testsRecyclerView = findViewById(R.id.testsRecyclerView);
        emptyStateText = findViewById(R.id.emptyStateText);
        createTestButton = findViewById(R.id.createTestButton);
        createTestButton.setVisibility(groupRole == Role.kOwner || groupRole == Role.kContributor ? View.VISIBLE : View.GONE);
        createTestButton.setOnClickListener(v -> {
            var createTestIntent = new Intent(TestsActivity.this, CreateTestActivity.class);
            startActivityWithAddedExtras(createTestIntent);
            loadTestData();
        });
        TestsAdapter adapter = new TestsAdapter(testList, test -> {
            Toast.makeText(this, "Starting: " + test.getName(), Toast.LENGTH_SHORT).show();

        });

        testsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        testsRecyclerView.setAdapter(adapter);

        loadTestData();
    }

    private void loadTestData() {
        var testsResponse = TestsService.getTestsForGroup(apiToken, groupId, null, null);
        if (testsResponse != null && testsResponse.isSuccessful()) {
            testList = testsResponse.body();
        } handleErrorCodes(testsResponse);
        if (testList == null)
            testList = new ArrayList<>();
        updateUI();
    }

    private void updateUI() {
        if (testList.isEmpty()) {
            emptyStateText.setVisibility(View.VISIBLE);
            testsRecyclerView.setVisibility(View.GONE);
        } else {
            emptyStateText.setVisibility(View.GONE);
            testsRecyclerView.setVisibility(View.VISIBLE);
            testsRecyclerView.getAdapter().notifyDataSetChanged();
        }
    }

    private static class TestsAdapter extends RecyclerView.Adapter<TestsAdapter.TestViewHolder> {
        private final List<Test> tests;
        private final TestClickListener listener;
        interface TestClickListener { void onTestClick(Test test); }

        TestsAdapter(List<Test> tests, TestClickListener listener) {
            this.tests = tests;
            this.listener = listener;
        }
        @Override
        public TestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Button button = (Button) View.inflate(parent.getContext(), R.layout.item_test, null);
            return new TestViewHolder(button);
        }
        @Override
        public void onBindViewHolder(TestViewHolder holder, int position) {
            Test test = tests.get(position);
            holder.button.setText(test.getName());
            holder.button.setOnClickListener(v -> listener.onTestClick(test));
        }

        @Override
        public int getItemCount() {
            return tests.size();
        }
        static class TestViewHolder extends RecyclerView.ViewHolder {
            Button button;
            TestViewHolder(Button itemView) {
                super(itemView);
                button = itemView;
            }
        }
    }
}