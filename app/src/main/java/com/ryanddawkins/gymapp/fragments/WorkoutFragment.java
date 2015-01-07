package com.ryanddawkins.gymapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.melnykov.fab.FloatingActionButton;
import com.orm.query.Select;
import com.ryanddawkins.gymapp.R;
import com.ryanddawkins.gymapp.Workout;
import com.ryanddawkins.gymapp.adapters.WorkoutListAdapter;
import com.ryanddawkins.gymapp.listeners.WorkoutFabListener;
import com.ryanddawkins.gymapp.listeners.WorkoutOnItemClickListener;

import java.util.List;

/**
 * Created by dawkins on 12/18/14.
 */
public class WorkoutFragment extends Fragment {

    private Workout[] workouts;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_workout, container, false);

        this.loadListView(rootView);

        return rootView;
    }

    public void loadListView(View rootView) {

        List<Workout> workouts = Select.from(Workout.class).orderBy("name").list();
        this.workouts = workouts.toArray(new Workout[workouts.size()]);

        ListView listView = (ListView) rootView.findViewById(R.id.list_workouts);
        listView.setOnItemClickListener(new WorkoutOnItemClickListener(this.workouts, this.getActivity()));

        if(this.workouts.length == 0) {
            listView.setVisibility(View.GONE);
            rootView.setBackgroundColor(getResources().getColor(R.color.blue_200));
        } else {
            TextView noDataView = (TextView) rootView.findViewById(R.id.no_workout_message);
            noDataView.setVisibility(View.GONE);
            listView.setAdapter(new WorkoutListAdapter(getActivity(), this.workouts));
        }

        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.exercise_fab);
        fab.setOnClickListener(new WorkoutFabListener(getActivity()));

        fab.attachToListView(listView);
    }

}
