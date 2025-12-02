package com.example.muscleup;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

// list all entities, increment version if you change database structure
@Database(entities = {Week.class, Day.class, Exercise.class, WorkoutSet.class}, version = 1)
public abstract class WorkoutDatabase extends RoomDatabase {

    // database name
    private static final String DATABASE_NAME = "workout.db";
    private static WorkoutDatabase workoutDatabase;

    // singleton pattern - only one instance of database
    public static WorkoutDatabase getInstance(Context context) {
        if (workoutDatabase == null) {
            workoutDatabase = Room.databaseBuilder(context, WorkoutDatabase.class, DATABASE_NAME)
                    // allows queries to run on main thread
                    .allowMainThreadQueries()
                    .build();
        }
        return workoutDatabase;
    }

    // dao methods - tells room which daos to use
    public abstract WeekDao weekDao();
    public abstract DayDao dayDao();
    public abstract ExerciseDao exerciseDao();
    public abstract WorkoutSetDao workoutSetDao();
}