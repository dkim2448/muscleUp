package com.example.muscleup;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.fragment.app.DialogFragment;

import com.example.muscleup.databinding.DialogAddDayBinding;

// dialog for adding a new day
public class AddDayDialog extends DialogFragment {

    private DialogAddDayBinding binding;
    private int weekId;

    // static method to create dialog with week id
    public static AddDayDialog newInstance(int weekId) {
        AddDayDialog dialog = new AddDayDialog();
        Bundle args = new Bundle();
        args.putInt("weekId", weekId);
        dialog.setArguments(args);
        return dialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // get week id from arguments
        weekId = getArguments().getInt("weekId");

        // inflate the dialog layout using view binding
        binding = DialogAddDayBinding.inflate(LayoutInflater.from(getContext()));

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

        // save button - create new day
        binding.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get day name
                String dayName = binding.editTextDayName.getText().toString();

                // validate input
                if (dayName.isEmpty()) {
                    return;
                }

                // create day object
                Day day = new Day(weekId, dayName);

                // add to database via daylistactivity
                DayListActivity dayListActivity = (DayListActivity) getActivity();
                dayListActivity.addDay(day);

                // close dialog
                dismiss();
            }
        });

        return builder.create();
    }
}