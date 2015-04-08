package com.ryanddawkins.gymapp.activities;

import android.os.Bundle;

import com.ryanddawkins.gymapp.R;
import com.ryanddawkins.gymapp.fragments.WorkoutFragment;

/**
 * Created by dawkins on 4/1/15.
 */
public class WorkoutActivity extends ToolbarActivity {

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        if (savedInstance == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content_frame, new WorkoutFragment())
                    .commit();
        }
    }

}
