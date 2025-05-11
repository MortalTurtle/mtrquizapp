package com.mt.quiz.mtquizapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mt.quiz.models.Test;

import java.util.ArrayList;
import java.util.List;

public class TestsActivity extends AppCompatActivity {

    private RecyclerView testsRecyclerView;
    private TextView emptyStateText;
    private List<Test> testList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tests);

        testsRecyclerView = findViewById(R.id.testsRecyclerView);
        emptyStateText = findViewById(R.id.emptyStateText);

        // Простой адаптер для кнопок
        TestsAdapter adapter = new TestsAdapter(testList, test -> {
            Toast.makeText(this, "Starting: " + test.getName(), Toast.LENGTH_SHORT).show();
            // Здесь переход к тесту
        });

        testsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        testsRecyclerView.setAdapter(adapter);

        loadTestData(); // Загрузка тестов
    }

    private void loadTestData() {
        // Временные данные для примера
        testList.add(new Test("1", "group1", "user1", "Math Test", 70, "Algebra and Geometry"));
        testList.add(new Test("2", "group1", "user2", "History Quiz", 60, "World History"));

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

    // Простой адаптер для кнопок
    private static class TestsAdapter extends RecyclerView.Adapter<TestsAdapter.TestViewHolder> {

        private final List<Test> tests;
        private final TestClickListener listener;

        interface TestClickListener {
            void onTestClick(Test test);
        }

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