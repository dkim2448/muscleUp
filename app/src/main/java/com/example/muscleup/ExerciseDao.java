package com.example.muscleup;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ExerciseDao {

    // get all exercises for a specific day
    @Query("SELECT * FROM Exercise WHERE day_id = :dayId ORDER BY id")
    public List<Exercise> getExercisesByDayId(int dayId);

    // get a specific exercise by id
    @Query("SELECT * FROM Exercise WHERE id = :exerciseId")
    public Exercise getExerciseById(int exerciseId);

    // insert a new exercise, returns the id
    @Insert
    public long insertExercise(Exercise exercise);

    // delete an exercise
    @Delete
    public void deleteExercise(Exercise exercise);

    // update an exercise
    @Update
    public void updateExercise(Exercise exercise);
}