package com.example.muscleup;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.muscleup.databinding.ActivityDayDetailBinding;

import java.util.List;

// day detail activity - shows all workouts for a specific day
public class DayDetailActivity extends AppCompatActivity {

    private ActivityDayDetailBinding binding;
    private WorkoutDatabase workoutDatabase;
    private int dayId;
    private String dayName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // setup view binding
        binding = ActivityDayDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // get day info from intent
        dayId = getIntent().getIntExtra("dayId", -1);
        dayName = getIntent().getStringExtra("dayName");

        // setup toolbar with day name as title
        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(dayName);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // initialize database
        workoutDatabase = WorkoutDatabase.getInstance(getApplicationContext());

        // fab button to add new workout
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // open add workout dialog
                AddWorkoutDialog dialog = AddWorkoutDialog.newInstance(dayId);
                dialog.show(getSupportFragmentManager(), "");
            }
        });

        // load workouts
        loadWorkouts();
    }

    // loads all workouts and displays them
    public void loadWorkouts() {
        // get all exercises for this day
        List<Exercise> exercises = workoutDatabase.exerciseDao().getExercisesByDayId(dayId);

        // build workout text
        StringBuilder workoutText = new StringBuilder();

        for (Exercise exercise : exercises) {
            // add exercise name
            workoutText.append(exercise.getExerciseName()).append("\n");

            // get all sets for this exercise
            List<WorkoutSet> sets = workoutDatabase.workoutSetDao().getSetsByExerciseId(exercise.getId());

            for (WorkoutSet set : sets) {
                // add set info with indent
                workoutText.append("  Set ").append(set.getSetNumber()).append(": ")
                        .append(set.getWeight()).append(" lbs x ")
                        .append(set.getReps()).append(" reps\n");
            }

            workoutText.append("\n");
        }

        // display in textview
        if (workoutText.length() > 0) {
            binding.workoutsTextView.setText(workoutText.toString());
        } else {
            binding.workoutsTextView.setText(R.string.no_workouts_yet);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        // handle back button in toolbar
        finish();
        return true;
    }
}