package com.example.muscleup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.muscleup.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

// main activity - shows list of weeks
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private List<Week> weekList;
    private WeekAdapter weekAdapter;
    private WorkoutDatabase workoutDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // setup view binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // setup toolbar
        setSupportActionBar(binding.toolbar);

        // initialize database
        workoutDatabase = WorkoutDatabase.getInstance(getApplicationContext());

        // initialize week list
        weekList = new ArrayList<>();

        // setup recyclerview adapter
        weekAdapter = new WeekAdapter(this, weekList);

        // setup recyclerview layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        binding.content.recyclerView.setLayoutManager(layoutManager);

        // add divider between items
        binding.content.recyclerView.addItemDecoration(
                new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        // set adapter
        binding.content.recyclerView.setAdapter(weekAdapter);

        // fab button to add new week
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // open add week dialog
                AddWeekDialog addWeekDialog = new AddWeekDialog();
                addWeekDialog.show(getSupportFragmentManager(), "");
            }
        });
    }

    // adds a week to database and refreshes list
    public void addWeek(Week week) {
        long id = workoutDatabase.weekDao().insertWeek(week);
        loadWeeks();
        weekAdapter.notifyDataSetChanged();
    }

    // loads all weeks from database
    public void loadWeeks() {
        weekList.clear();
        List<Week> weeks = workoutDatabase.weekDao().getAllWeeks();
        weekList.addAll(weeks);
        weekAdapter.notifyDataSetChanged();
    }

    // opens day list activity
    public void openDayList(Week week) {
        Intent intent = new Intent(this, DayListActivity.class);
        intent.putExtra("weekId", week.getId());
        intent.putExtra("weekNumber", week.getWeekNumber());
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // reload weeks when returning to this activity
        loadWeeks();
    }
}