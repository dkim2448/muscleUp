package com.example.muscleup;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.fragment.app.DialogFragment;

import com.example.muscleup.databinding.DialogAddWeekBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

// dialog for adding a new week
public class AddWeekDialog extends DialogFragment {

    private DialogAddWeekBinding binding;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // inflate the dialog layout using view binding
        binding = DialogAddWeekBinding.inflate(LayoutInflater.from(getContext()));

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

        // save button - create new week
        binding.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get week number
                String weekNumberStr = binding.editTextWeekNumber.getText().toString();

                // validate input
                if (weekNumberStr.isEmpty()) {
                    return;
                }

                // convert to int
                int weekNumber = Integer.parseInt(weekNumberStr);

                // auto-generate current date
                String currentDate = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(new Date());

                // create week object
                Week week = new Week(weekNumber, currentDate);

                // add to database via mainactivity
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.addWeek(week);

                // close dialog
                dismiss();
            }
        });

        return builder.create();
    }
}