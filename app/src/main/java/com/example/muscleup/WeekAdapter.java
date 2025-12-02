package com.example.muscleup;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// adapter for displaying weeks in recyclerview
public class WeekAdapter extends RecyclerView.Adapter<WeekAdapter.WeekViewHolder> {

    private MainActivity mainActivity;
    private List<Week> weekList;

    // constructor
    public WeekAdapter(MainActivity mainActivity, List<Week> weekList) {
        this.mainActivity = mainActivity;
        this.weekList = weekList;
    }

    @NonNull
    @Override
    public WeekViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate the list item layout
        View listItem = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.week_list_item, parent, false);
        return new WeekViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull WeekViewHolder holder, int position) {
        // get week at this position
        Week week = weekList.get(position);

        // set the text to show week number and date
        holder.weekTextView.setText("Week " + week.getWeekNumber());
        holder.dateTextView.setText(week.getDate());
    }

    @Override
    public int getItemCount() {
        return weekList.size();
    }

    // viewholder for week items
    public class WeekViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView weekTextView;
        private TextView dateTextView;

        // constructor
        public WeekViewHolder(View itemView) {
            super(itemView);
            weekTextView = itemView.findViewById(R.id.weekTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);

            // make the whole item clickable
            itemView.setClickable(true);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            // get position and week
            int position = getAdapterPosition();
            Week week = weekList.get(position);

            // call mainactivity method to open day list
            mainActivity.openDayList(week);
        }
    }
}