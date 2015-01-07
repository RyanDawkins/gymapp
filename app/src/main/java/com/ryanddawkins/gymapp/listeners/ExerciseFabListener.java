package com.ryanddawkins.gymapp.listeners;

import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.ryanddawkins.gymapp.R;
import com.ryanddawkins.gymapp.fragments.CreateExerciseFragment;

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

        CreateExerciseFragment createExerciseFragment = new CreateExerciseFragment();
        createExerciseFragment.setSaveBtnNameText(this.activity.getString(R.string.exercise_create_btn));
        createExerciseFragment.setSaveButtonListener(new ExerciseCreateClickListener(this.activity));

        this.activity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, createExerciseFragment)
                .commit();

    }
}
