package com.ryanddawkins.gymapp;

import com.orm.SugarRecord;

/**
 * Created by dawkins on 4/14/15.
 */
public class Sets extends SugarRecord<Sets> {

    Exercise exercise;
    Workout workout;
    int numberOfReps;
    double weight;

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public Workout getWorkout() {
        return workout;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    public int getNumberOfReps() {
        return numberOfReps;
    }

    public void setNumberOfReps(int numberOfReps) {
        this.numberOfReps = numberOfReps;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
