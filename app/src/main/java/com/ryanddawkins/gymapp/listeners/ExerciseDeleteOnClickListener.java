package com.ryanddawkins.gymapp.listeners;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.View;

import com.ryanddawkins.gymapp.Exercise;

/**
 * Created by dawkins on 12/16/14.
 */
public class ExerciseDeleteOnClickListener implements View.OnClickListener {

    private FragmentActivity activity;
    private Exercise exercise;

    public ExerciseDeleteOnClickListener(FragmentActivity activity, Exercise exercise) {
        this.activity = activity;
        this.exercise = exercise;
    }

    @Override
    public void onClick(View v) {
        this.exercise.delete();
        NavUtils.navigateUpFromSameTask(this.activity);
    }
}
