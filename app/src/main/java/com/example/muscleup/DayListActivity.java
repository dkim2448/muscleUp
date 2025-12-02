package com.example.muscleup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.muscleup.databinding.ActivityDayListBinding;

import java.util.ArrayList;
import java.util.List;

// day list activity - shows all days in a week
public class DayListActivity extends AppCompatActivity {

    private ActivityDayListBinding binding;
    private WorkoutDatabase workoutDatabase;
    private int weekId;
    private int weekNumber;
    private List<Day> dayList;
    private DayAdapter dayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // setup view binding
        binding = ActivityDayListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // get week info from intent
        weekId = getIntent().getIntExtra("weekId", -1);
        weekNumber = getIntent().getIntExtra("weekNumber", -1);

        // setup toolbar with week number as title
        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Week " + weekNumber);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // initialize database
        workoutDatabase = WorkoutDatabase.getInstance(getApplicationContext());

        // initialize day list
        dayList = new ArrayList<>();

        // setup recyclerview adapter
        dayAdapter = new DayAdapter(this, dayList, workoutDatabase);

        // setup recyclerview
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        binding.daysRecyclerView.setLayoutManager(layoutManager);
        binding.daysRecyclerView.setAdapter(dayAdapter);

        // fab button to add new day
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // open add day dialog
                AddDayDialog dialog = AddDayDialog.newInstance(weekId);
                dialog.show(getSupportFragmentManager(), "");
            }
        });

        // load days
        loadDays();
    }

    // adds a day to database and refreshes list
    public void addDay(Day day) {
        long id = workoutDatabase.dayDao().insertDay(day);
        loadDays();
        dayAdapter.notifyDataSetChanged();
    }

    // loads all days for this week
    public void loadDays() {
        dayList.clear();
        List<Day> days = workoutDatabase.dayDao().getDaysByWeekId(weekId);
        dayList.addAll(days);
        dayAdapter.notifyDataSetChanged();
    }

    // opens day detail activity
    public void openDayDetail(Day day) {
        Intent intent = new Intent(this, DayDetailActivity.class);
        intent.putExtra("dayId", day.getId());
        intent.putExtra("dayName", day.getDayName());
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        // handle back button in toolbar
        finish();
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        // reload days when returning to this activity
        loadDays();
    }
}