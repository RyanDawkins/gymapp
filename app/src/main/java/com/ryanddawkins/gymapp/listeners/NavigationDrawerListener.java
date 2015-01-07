package com.ryanddawkins.gymapp.listeners;


import android.content.res.TypedArray;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;

import com.ryanddawkins.gymapp.R;
import com.ryanddawkins.gymapp.fragments.DashFragment;
import com.ryanddawkins.gymapp.fragments.ExerciseFragment;
import com.ryanddawkins.gymapp.fragments.WorkoutFragment;

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

        Fragment fragment = null;
        if(itemText.equals(this.activity.getString(R.string.home))) {
            fragment = new DashFragment();
        }
        else if(itemText.equals(this.activity.getString(R.string.exercise))) {
            fragment = new ExerciseFragment();
        }
        else if(itemText.equals(this.activity.getString(R.string.workouts))) {
            fragment = new WorkoutFragment();
        }
        else {
            fragment = new DashFragment();
            itemText = this.activity.getString(R.string.home);
        }

        DrawerLayout mDrawerLayout;
        mDrawerLayout = (DrawerLayout) this.activity.findViewById(R.id.drawer_layout);
        mDrawerLayout.closeDrawers();

        ActionBar actionBar = this.activity.getSupportActionBar();
        actionBar.setTitle(itemText);

        this.activity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();
    }
}
