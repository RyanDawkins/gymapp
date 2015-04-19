package com.ryanddawkins.gymapp.activities;

import android.os.Bundle;

import com.ryanddawkins.gymapp.Exercise;
import com.ryanddawkins.gymapp.R;
import com.ryanddawkins.gymapp.Workout;
import com.ryanddawkins.gymapp.WorkoutExercise;
import com.ryanddawkins.gymapp.fragments.CreateWorkoutFragment;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by dawkins on 4/2/15.
 */
public class WorkoutEditActivity extends ToolbarActivity {

    public static String WORKOUT_EXERCISES = "selectedExercises";
    public static String WORKOUT_ID = "workoutid";
    public static String WORKOUT_NAME = "workout_name";

    private Workout workout;
    private long[] selected;
    private HashMap<Long, Exercise> workoutExercises;

    public WorkoutEditActivity() {}

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        if (savedInstance == null) {

            this.workout = Workout.findById(Workout.class, this.getIntent().getLongExtra(WORKOUT_ID, -1));

            this.selected = this.getIntent().getLongArrayExtra(WORKOUT_EXERCISES);
            if(this.selected == null && this.workout != null) {
                HashMap<Long, Exercise> exercisesByWorkout = WorkoutExercise.getExercisesByWorkout(workout.getId());
                Iterator it = exercisesByWorkout.entrySet().iterator();
                long[] exercisePks = new long[exercisesByWorkout.size()];
                int i = 0;
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry)it.next();
                    exercisePks[i] = ((Exercise) pair.getValue()).getId();
                    it.remove();
                    i++;
                }
                this.selected = exercisePks;
            }

            CreateWorkoutFragment fragment = new CreateWorkoutFragment();
            if(this.workout != null) {
                fragment.setWorkout(workout);
                this.workoutExercises = WorkoutExercise.getExercisesByWorkout(this.workout.getId());
            } else {
                setTitle("Create Workout");
                this.workoutExercises = new HashMap<Long, Exercise>();
            }
            if(this.selected != null) {
                for (int i = 0; i < this.selected.length; i++) {
                    Exercise exercise = Exercise.findById(Exercise.class, (long) this.selected[i]);
                    this.workoutExercises.put(exercise.getId(), exercise);
                }
            }

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content_frame, fragment)
                    .commit();
        }
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    public void saveSelected() {
        this.saveSelected(this.workout);
    }

    public boolean hasSelected() {
        return (this.selected != null);
    }

    public long[] getSelected() {
        return selected;
    }

    public void setSelected(long[] selected) {
        this.selected = selected;
    }

    public void saveSelected(Workout workout) {

        this.workout = workout;

        if(this.selected == null) return;
        HashMap<Long, Exercise> exerciseHashMap = WorkoutExercise.getExercisesByWorkout(this.workout.getId());
        for(int i = 0; i < this.selected.length; i++) {

            Long exerciseID = this.selected[i];
            Exercise exercise = exerciseHashMap.get(exerciseID);
            if(exercise == null) {
                exercise = Exercise.findById(Exercise.class, this.selected[i]);
                WorkoutExercise workoutExercise = new WorkoutExercise();
                workoutExercise.setWorkout(this.workout);
                workoutExercise.setExercise(exercise);
                workoutExercise.save();
            } else {
                exerciseHashMap.remove(exerciseID);
                this.workoutExercises.remove(exerciseID);
            }
            this.workoutExercises.put(exercise.getId(), exercise);
        }

        Iterator it = exerciseHashMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            Exercise exercise = (Exercise) pair.getValue();
            WorkoutExercise.deleteAll(WorkoutExercise.class, "exercise = ? and workout = ?", ""+exercise.getId(), ""+this.workout.getId());
        }

    }

    public HashMap<Long, Exercise> getWorkoutExercises() {
        return this.workoutExercises;
    }

}
