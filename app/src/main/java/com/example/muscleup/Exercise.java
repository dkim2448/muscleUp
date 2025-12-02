package com.example.muscleup;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

// exercise entity - represents an exercise like "seated hamstring curls"
@Entity
public class Exercise {

    // primary key, auto generates id
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    // foreign key - which day this exercise belongs to
    @NonNull
    @ColumnInfo(name = "day_id")
    private int dayId;

    // exercise name like "seated hamstring curls"
    @NonNull
    @ColumnInfo(name = "exercise_name")
    private String exerciseName;

    // constructor
    public Exercise(int dayId, String exerciseName) {
        this.id = 0;
        this.dayId = dayId;
        this.exerciseName = exerciseName;
    }

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDayId() {
        return dayId;
    }

    public void setDayId(int dayId) {
        this.dayId = dayId;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }
}