package com.ryanddawkins.gymapp.listeners;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;

import com.ryanddawkins.gymapp.R;
import com.ryanddawkins.gymapp.Workout;
import com.ryanddawkins.gymapp.fragments.CreateWorkoutFragment;

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

        CreateWorkoutFragment fragment = new CreateWorkoutFragment();
        fragment.setSaveBtnNameText(this.activity.getResources().getString(R.string.workout_save_btn));
        fragment.setSaveButtonListener(new WorkoutCreateClickListener(this.activity, workout));
        fragment.setWorkout(workout);

        this.activity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();
    }
}
