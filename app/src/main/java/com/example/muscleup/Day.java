package com.example.muscleup;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

// day entity - represents a workout day like "lower 1/day 1"
@Entity
public class Day {

    // primary key, auto generates id
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    // foreign key - which week this day belongs to
    @NonNull
    @ColumnInfo(name = "week_id")
    private int weekId;

    // day name like "lower 1/day 1"
    @NonNull
    @ColumnInfo(name = "day_name")
    private String dayName;

    // constructor
    public Day(int weekId, String dayName) {
        this.id = 0;
        this.weekId = weekId;
        this.dayName = dayName;
    }

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWeekId() {
        return weekId;
    }

    public void setWeekId(int weekId) {
        this.weekId = weekId;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }
}