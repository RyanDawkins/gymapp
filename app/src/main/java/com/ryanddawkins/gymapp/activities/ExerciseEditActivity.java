package com.ryanddawkins.gymapp.activities;

import android.os.Bundle;

import com.ryanddawkins.gymapp.Exercise;
import com.ryanddawkins.gymapp.R;
import com.ryanddawkins.gymapp.fragments.CreateExerciseFragment;

/**
 * Created by dawkins on 4/2/15.
 */
public class ExerciseEditActivity extends ToolbarActivity {

    private Exercise exercise;

    public static String EXERCISE_ID = "exerciseid";

    public ExerciseEditActivity() {}

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        if (savedInstance == null) {

            long exerciseid = this.getIntent().getLongExtra(EXERCISE_ID, -1);
            this.exercise = Exercise.findById(Exercise.class, exerciseid);


            CreateExerciseFragment fragment = new CreateExerciseFragment();
            if(this.exercise != null) {
                fragment.setExercise(exercise);
            } else {
                setTitle("Create Exercise");
            }

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content_frame, fragment)
                    .commit();
        }
    }

}
