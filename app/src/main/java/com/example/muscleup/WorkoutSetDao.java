package com.example.muscleup;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WorkoutSetDao {

    // get all sets for a specific exercise
    @Query("SELECT * FROM WorkoutSet WHERE exercise_id = :exerciseId ORDER BY set_number")
    public List<WorkoutSet> getSetsByExerciseId(int exerciseId);

    // insert a new set, returns the id
    @Insert
    public long insertSet(WorkoutSet set);

    // delete a set
    @Delete
    public void deleteSet(WorkoutSet set);

    // update a set
    @Update
    public void updateSet(WorkoutSet set);
}