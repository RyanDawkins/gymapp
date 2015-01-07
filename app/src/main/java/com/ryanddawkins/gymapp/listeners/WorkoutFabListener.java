package com.ryanddawkins.gymapp.listeners;

import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.ryanddawkins.gymapp.R;
import com.ryanddawkins.gymapp.fragments.CreateWorkoutFragment;

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

        CreateWorkoutFragment fragment = new CreateWorkoutFragment();
        fragment.setSaveBtnNameText(this.activity.getString(R.string.workout_create_btn));
        fragment.setSaveButtonListener(new WorkoutCreateClickListener(this.activity));

        this.activity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();

    }


}
