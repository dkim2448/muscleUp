package com.example.muscleup;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// adapter for displaying days in recyclerview
public class DayAdapter extends RecyclerView.Adapter<DayAdapter.DayViewHolder> {

    private DayListActivity dayListActivity;
    private List<Day> dayList;
    private WorkoutDatabase db;

    // constructor
    public DayAdapter(DayListActivity dayListActivity, List<Day> dayList, WorkoutDatabase db) {
        this.dayListActivity = dayListActivity;
        this.dayList = dayList;
        this.db = db;
    }

    @NonNull
    @Override
    public DayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate the list item layout
        View listItem = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.day_list_item, parent, false);
        return new DayViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull DayViewHolder holder, int position) {
        // get day at this position
        Day day = dayList.get(position);

        // set day name
        holder.dayNameTextView.setText(day.getDayName());

        // get exercises for preview
        List<Exercise> exercises = db.exerciseDao().getExercisesByDayId(day.getId());

        // build preview text
        StringBuilder preview = new StringBuilder();
        for (int i = 0; i < exercises.size(); i++) {
            preview.append("• ").append(exercises.get(i).getExerciseName()).append("\n");
        }

        // show preview or "no workouts" message
        if (preview.length() > 0) {
            holder.exercisePreviewTextView.setText(preview.toString().trim());
        } else {
            holder.exercisePreviewTextView.setText("No workouts yet");
        }
    }

    @Override
    public int getItemCount() {
        return dayList.size();
    }

    // viewholder for day items
    public class DayViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView dayNameTextView;
        private TextView exercisePreviewTextView;

        // constructor
        public DayViewHolder(View itemView) {
            super(itemView);
            dayNameTextView = itemView.findViewById(R.id.dayNameTextView);
            exercisePreviewTextView = itemView.findViewById(R.id.exercisePreviewTextView);

            // make the whole item clickable
            itemView.setClickable(true);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            // get position and day
            int position = getAdapterPosition();
            Day day = dayList.get(position);

            // call daylistactivity method to open day detail
            dayListActivity.openDayDetail(day);
        }
    }
}