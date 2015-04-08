package com.ryanddawkins.gymapp.listeners;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;

import com.ryanddawkins.gymapp.Workout;
import com.ryanddawkins.gymapp.activities.WorkoutEditActivity;

/**
 * Created by dawkins on 12/20/14.
 */
public class WorkoutOnItemClickListener implements AdapterView.OnItemClickListener {

    private Workout[] workouts;
    private FragmentActivity activity;

    public WorkoutOnItemClickListener(Workout[] workouts, FragmentActivity activity) {
        this.activity = activity;
        this.workouts = workouts;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Workout workout = this.workouts[position];
        Intent intent = new Intent(this.activity, WorkoutEditActivity.class);
        intent.putExtra("workoutid", workout.getId());
        this.activity.startActivity(intent);
    }
}
