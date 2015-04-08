package com.ryanddawkins.gymapp.listeners;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.ryanddawkins.gymapp.activities.WorkoutEditActivity;

/**
 * Created by dawkins on 12/18/14.
 */
public class WorkoutFabListener implements View.OnClickListener {

    private FragmentActivity activity;

    public WorkoutFabListener(FragmentActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this.activity, WorkoutEditActivity.class);
        this.activity.startActivity(intent);
    }


}
