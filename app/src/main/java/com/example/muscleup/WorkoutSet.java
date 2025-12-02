package com.example.muscleup;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

// workout set entity - represents a single set like "120 lbs x 5 reps"
@Entity
public class WorkoutSet {

    // primary key, auto generates id
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    // foreign key - which exercise this set belongs to
    @NonNull
    @ColumnInfo(name = "exercise_id")
    private int exerciseId;

    // set number like 1, 2, 3
    @NonNull
    @ColumnInfo(name = "set_number")
    private int setNumber;

    // weight in pounds
    @NonNull
    @ColumnInfo(name = "weight")
    private double weight;

    // number of reps
    @NonNull
    @ColumnInfo(name = "reps")
    private int reps;

    // constructor
    public WorkoutSet(int exerciseId, int setNumber, double weight, int reps) {
        this.id = 0;
        this.exerciseId = exerciseId;
        this.setNumber = setNumber;
        this.weight = weight;
        this.reps = reps;
    }

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public int getSetNumber() {
        return setNumber;
    }

    public void setSetNumber(int setNumber) {
        this.setNumber = setNumber;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }
}