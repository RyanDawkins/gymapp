package com.ryanddawkins.gymapp.listeners;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Spinner;

import com.ryanddawkins.gymapp.Exercise;
import com.ryanddawkins.gymapp.R;
import com.ryanddawkins.gymapp.fragments.ExerciseFragment;

/**
 * Created by dawkins on 12/13/14.
 */
public class ExerciseCreateClickListener implements View.OnClickListener {

    private FragmentActivity activity;
    private Exercise exercise;

    public ExerciseCreateClickListener(FragmentActivity activity) {
        this(activity, new Exercise());
    }
    public ExerciseCreateClickListener(FragmentActivity activity, Exercise exercise) {
        this.activity = activity;
        this.exercise = exercise;
    }

    @Override
    public void onClick(View v) {

        EditText exerciseNameEditText = (EditText) this.activity.findViewById(R.id.exercise_name);
        this.exercise.setName(exerciseNameEditText.getText().toString());

        InputMethodManager imm = (InputMethodManager) this.activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(exerciseNameEditText.getWindowToken(), 0);

        Spinner exerciseTypeSpinner = (Spinner) this.activity.findViewById(R.id.exercise_type);
        switch(exerciseTypeSpinner.getSelectedItemPosition()) {
            case 0:
                this.exercise.setType(Exercise.AEROBIC);
                break;
            case 1:
                this.exercise.setType(Exercise.STRENGTH);
                break;
            case 2:
                this.exercise.setType(Exercise.Flexibility);
                break;
            case 3:
                this.exercise.setType(Exercise.BALANCE);
                break;
            default:
                this.exercise.setType(-1);
        }
        this.exercise.save();

        this.activity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, new ExerciseFragment())
                .commit();
    }
}
