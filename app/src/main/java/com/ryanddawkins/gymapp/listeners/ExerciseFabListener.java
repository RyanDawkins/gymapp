package com.ryanddawkins.gymapp.listeners;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.ryanddawkins.gymapp.activities.ExerciseEditActivity;

/**
 * Created by dawkins on 12/13/14.
 */
public class ExerciseFabListener implements View.OnClickListener {

    private FragmentActivity activity;

    public ExerciseFabListener(FragmentActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this.activity, ExerciseEditActivity.class);
        this.activity.startActivity(intent);
    }
}
