package com.ryanddawkins.gymapp.listeners;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.ryanddawkins.gymapp.R;
import com.ryanddawkins.gymapp.Workout;
import com.ryanddawkins.gymapp.activities.WorkoutEditActivity;

/**
 * Created by dawkins on 12/20/14.
 */
public class WorkoutCreateClickListener implements View.OnClickListener {

    private FragmentActivity activity;
    private Workout workout;

    public WorkoutCreateClickListener(FragmentActivity activity) {
        this(activity, new Workout());
    }
    public WorkoutCreateClickListener(FragmentActivity activity, Workout workout) {
        this.activity = activity;
        this.workout = workout;
    }

    @Override
    public void onClick(View v) {

        if(this.workout == null) {
            this.workout = new Workout();
        }

        EditText nameEditText = (EditText) this.activity.findViewById(R.id.workout_name);
        this.workout.setName(nameEditText.getText().toString());

        this.workout.save();

        InputMethodManager imm = (InputMethodManager) this.activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(nameEditText.getWindowToken(), 0);

        ((WorkoutEditActivity) this.activity).saveSelected(this.workout);

        NavUtils.navigateUpFromSameTask(this.activity);
    }
}
