package com.ryanddawkins.gymapp;

import com.orm.SugarRecord;

/**
 * Created by dawkins on 12/20/14.
 */
public class WorkoutExercise extends SugarRecord<WorkoutExercise> {

    public Workout workout;
    public Exercise exercise;

    public Workout getWorkout() {
        return workout;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }
}
