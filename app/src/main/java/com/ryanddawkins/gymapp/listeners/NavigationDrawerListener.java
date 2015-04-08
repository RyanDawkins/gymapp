package com.ryanddawkins.gymapp.listeners;


import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;

import com.ryanddawkins.gymapp.activities.DrawerActivity;
import com.ryanddawkins.gymapp.R;
import com.ryanddawkins.gymapp.activities.DashActivity;
import com.ryanddawkins.gymapp.activities.ExerciseActivity;
import com.ryanddawkins.gymapp.activities.WorkoutActivity;

/**
 * Created by dawkins on 12/5/14.
 */
public class NavigationDrawerListener implements AdapterView.OnItemClickListener {

    private ActionBarActivity activity;

    public NavigationDrawerListener(ActionBarActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TypedArray navMenuTitles = this.activity.getResources().obtainTypedArray(R.array.drawer_items);
        String[] items = new String[navMenuTitles.length()];
        for(int i = 0; i < navMenuTitles.length(); i++) {
            items[i] = navMenuTitles.getString(i);
        }
        String itemText = items[position];

        Intent intent;
        if(itemText.equals(this.activity.getString(R.string.home))) {
            intent = new Intent(this.activity, DashActivity.class);
        }
        else if(itemText.equals(this.activity.getString(R.string.exercise))) {
            intent = new Intent(this.activity, ExerciseActivity.class);
        }
        else if(itemText.equals(this.activity.getString(R.string.workouts))) {
            intent = new Intent(this.activity, WorkoutActivity.class);
        }
        else {
            intent = new Intent(this.activity, DashActivity.class);
        }

        ((DrawerActivity)this.activity).closeDrawer();
        this.activity.startActivity(intent);
    }
}
