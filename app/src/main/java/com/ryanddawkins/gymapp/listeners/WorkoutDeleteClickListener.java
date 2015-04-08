package com.ryanddawkins.gymapp.listeners;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.View;

import com.ryanddawkins.gymapp.Workout;

/**
 * Created by dawkins on 12/20/14.
 */
public class WorkoutDeleteClickListener implements View.OnClickListener {

    private FragmentActivity activity;
    private Workout workout;

    public WorkoutDeleteClickListener(FragmentActivity activity, Workout workout) {
        this.activity = activity;
        this.workout = workout;
    }

    @Override
    public void onClick(View v) {
        this.workout.delete();
        NavUtils.navigateUpFromSameTask(this.activity);
    }
}
