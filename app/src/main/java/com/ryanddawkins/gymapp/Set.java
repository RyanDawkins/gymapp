package com.ryanddawkins.gymapp;

import com.orm.SugarRecord;

/**
 * Created by dawkins on 4/14/15.
 */
public class Set extends SugarRecord<Set> {

    Exercise exercise;
    Workout workout;
    double numberOfSets;
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

    public double getNumberOfSets() {
        return numberOfSets;
    }

    public void setNumberOfSets(double numberOfSets) {
        this.numberOfSets = numberOfSets;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
