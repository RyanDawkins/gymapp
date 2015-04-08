package com.ryanddawkins.gymapp;

import com.orm.SugarRecord;

import java.util.HashMap;
import java.util.List;

/**
 * Created by dawkins on 12/20/14.
 */
public class WorkoutExercise extends SugarRecord<WorkoutExercise> {

    public Workout workout;
    public Exercise exercise;
    public int numberOfSets;
    public int setReps;

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

    public int getNumberOfSets() {
        return numberOfSets;
    }

    public void setNumberOfSets(int numberOfSets) {
        this.numberOfSets = numberOfSets;
    }

    public int getSetReps() {
        return setReps;
    }

    public void setSetReps(int setReps) {
        this.setReps = setReps;
    }

    public static WorkoutExercise getByComposite(long workoutid, long exerciseid) {
        List<WorkoutExercise> workoutExercises = WorkoutExercise.find(WorkoutExercise.class, "workout = ? and exercise = ?", ""+workoutid, ""+exerciseid);
        if(workoutExercises.size() > 0) {
            return workoutExercises.get(0);
        } else {
            return null;
        }
    }

    public static HashMap<Long, Exercise> getExercisesByWorkout(long workoutid) {
        List<WorkoutExercise> workoutExerciseList = WorkoutExercise.find(
                WorkoutExercise.class, "workout = ?", ""+workoutid);
        HashMap<Long, Exercise> exercises = new HashMap<Long, Exercise>();
        for(int i = 0; i < workoutExerciseList.size(); i++) {
            WorkoutExercise workoutExercise = workoutExerciseList.get(i);
            exercises.put(workoutExercise.exercise.getId(), workoutExercise.exercise);
        }

        return exercises;
    }
}
