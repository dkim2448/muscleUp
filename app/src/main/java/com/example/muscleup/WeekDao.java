package com.example.muscleup;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WeekDao {

    // get all weeks ordered by week number descending
    @Query("SELECT * FROM Week ORDER BY week_number DESC")
    public List<Week> getAllWeeks();

    // get a specific week by id
    @Query("SELECT * FROM Week WHERE id = :weekId")
    public Week getWeekById(int weekId);

    // insert a new week, returns the id
    @Insert
    public long insertWeek(Week week);

    // delete a week
    @Delete
    public void deleteWeek(Week week);

    // update a week
    @Update
    public void updateWeek(Week week);
}