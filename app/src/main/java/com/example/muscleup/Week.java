package com.example.muscleup;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

// week entity for room database
@Entity
public class Week {

    // primary key, auto generates id
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    // week number like 141
    @NonNull
    @ColumnInfo(name = "week_number")
    private int weekNumber;

    // date of the week
    @ColumnInfo(name = "date")
    private String date;

    // constructor
    public Week(int weekNumber, String date) {
        this.id = 0;
        this.weekNumber = weekNumber;
        this.date = date;
    }

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(int weekNumber) {
        this.weekNumber = weekNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}