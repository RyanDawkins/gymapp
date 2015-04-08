package com.ryanddawkins.gymapp.activities;

import android.os.Bundle;

import com.ryanddawkins.gymapp.R;
import com.ryanddawkins.gymapp.fragments.DashFragment;

/**
 * Created by dawkins on 4/2/15.
 */
public class DashActivity extends DrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        if (savedInstance == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content_frame, new DashFragment())
                    .commit();
        }
    }

}
