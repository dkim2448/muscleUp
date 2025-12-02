package com.example.muscleup;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.fragment.app.DialogFragment;

import com.example.muscleup.databinding.DialogAddWorkoutBinding;

import java.util.List;

// dialog for adding a workout set
public class AddWorkoutDialog extends DialogFragment {

    private DialogAddWorkoutBinding binding;
    private int dayId;

    // static method to create dialog with day id
    public static AddWorkoutDialog newInstance(int dayId) {
        AddWorkoutDialog dialog = new AddWorkoutDialog();
        Bundle args = new Bundle();
        args.putInt("dayId", dayId);
        dialog.setArguments(args);
        return dialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // get day id from arguments
        dayId = getArguments().getInt("dayId");

        // inflate the dialog layout using view binding
        binding = DialogAddWorkoutBinding.inflate(LayoutInflater.from(getContext()));

        // create alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(binding.getRoot());

        // cancel button - dismiss dialog
        binding.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        // save button - save set and close dialog
        binding.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSet();
                dismiss();
            }
        });

        return builder.create();
    }

    // saves the set to database
    private void saveSet() {
        String exerciseName = binding.editTextExerciseName.getText().toString();
        String weightStr = binding.editTextWeight.getText().toString();
        String repsStr = binding.editTextReps.getText().toString();

        // validate inputs
        if (exerciseName.isEmpty() || weightStr.isEmpty() || repsStr.isEmpty()) {
            return;
        }

        // parse values
        double weight = Double.parseDouble(weightStr);
        int reps = Integer.parseInt(repsStr);

        // get database instance
        WorkoutDatabase db = WorkoutDatabase.getInstance(getContext());

        // check if exercise with this name already exists for this day (case insensitive)
        List<Exercise> existingExercises = db.exerciseDao().getExercisesByDayId(dayId);
        int exerciseId = -1;

        for (Exercise ex : existingExercises) {
            if (ex.getExerciseName().equalsIgnoreCase(exerciseName)) {
                exerciseId = ex.getId();
                break;
            }
        }

        // if exercise doesn't exist, create it
        if (exerciseId == -1) {
            Exercise exercise = new Exercise(dayId, exerciseName);
            exerciseId = (int) db.exerciseDao().insertExercise(exercise);
        }

        // figure out the next set number for this exercise
        List<WorkoutSet> existingSets = db.workoutSetDao().getSetsByExerciseId(exerciseId);
        int nextSetNumber = existingSets.size() + 1;

        // create and insert set
        WorkoutSet set = new WorkoutSet(exerciseId, nextSetNumber, weight, reps);
        db.workoutSetDao().insertSet(set);

        // notify day detail activity to refresh
        DayDetailActivity dayDetailActivity = (DayDetailActivity) getActivity();
        dayDetailActivity.loadWorkouts();
    }
}