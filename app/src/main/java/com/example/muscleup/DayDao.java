package com.example.muscleup;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DayDao {

    // get all days for a specific week
    @Query("SELECT * FROM Day WHERE week_id = :weekId ORDER BY id")
    public List<Day> getDaysByWeekId(int weekId);

    // get a specific day by id
    @Query("SELECT * FROM Day WHERE id = :dayId")
    public Day getDayById(int dayId);

    // insert a new day, returns the id
    @Insert
    public long insertDay(Day day);

    // delete a day
    @Delete
    public void deleteDay(Day day);

    // update a day
    @Update
    public void updateDay(Day day);
}