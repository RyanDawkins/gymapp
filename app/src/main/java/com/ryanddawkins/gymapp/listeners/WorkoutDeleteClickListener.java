package com.ryanddawkins.gymapp.listeners;

import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.ryanddawkins.gymapp.R;
import com.ryanddawkins.gymapp.Workout;
import com.ryanddawkins.gymapp.fragments.WorkoutFragment;

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
        this.activity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, new WorkoutFragment())
                .commit();
    }
}
