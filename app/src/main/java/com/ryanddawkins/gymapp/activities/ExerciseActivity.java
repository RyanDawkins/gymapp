package com.ryanddawkins.gymapp.activities;

import android.content.Intent;
import android.os.Bundle;

import com.ryanddawkins.gymapp.R;
import com.ryanddawkins.gymapp.fragments.ExerciseFragment;

/**
 * Created by dawkins on 4/1/15.
 */
public class ExerciseActivity extends ToolbarActivity {

    public static String SELECT_MODE_ON = "selectModeOn";
    public static String WORKOUT_ID = "workoutid";
    public static String WORKOUT_NAME = "workout_name";
    public static String EXERCISES_SELECTED = "selected_exercises";

    private boolean selectModeOn;
    private long workoutid;
    private String workout_name;

    public ExerciseActivity() {
        this.selectModeOn = false;
    }

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        Intent intent = this.getIntent();
        this.selectModeOn = intent.getBooleanExtra(SELECT_MODE_ON, false);
        this.workoutid = intent.getLongExtra(WORKOUT_ID, -1);
        this.workout_name = intent.getStringExtra(WORKOUT_NAME);

        if (savedInstance == null) {
            ExerciseFragment exerciseFragment = new ExerciseFragment();
            exerciseFragment.setPreSelected(intent.getLongArrayExtra(EXERCISES_SELECTED));
            exerciseFragment.setSelectModeOn(this.selectModeOn);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content_frame, exerciseFragment)
                    .commit();
        }
    }

    public boolean isSelectModeOn() {
        return selectModeOn;
    }

    public void setSelectModeOn(boolean selectModeOn) {
        this.selectModeOn = selectModeOn;
    }

    public long getWorkoutid() {
        return workoutid;
    }

    public void setWorkoutid(long workoutid) {
        this.workoutid = workoutid;
    }

    public String getWorkout_name() {
        return workout_name;
    }

    public void setWorkout_name(String workout_name) {
        this.workout_name = workout_name;
    }
}
