package com.ryanddawkins.gymapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.melnykov.fab.FloatingActionButton;
import com.orm.query.Select;
import com.ryanddawkins.gymapp.Exercise;
import com.ryanddawkins.gymapp.R;
import com.ryanddawkins.gymapp.activities.ExerciseActivity;
import com.ryanddawkins.gymapp.activities.ExerciseEditActivity;
import com.ryanddawkins.gymapp.activities.WorkoutEditActivity;
import com.ryanddawkins.gymapp.adapters.ExerciseListAdapter;
import com.ryanddawkins.gymapp.listeners.ExerciseFabListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dawkins on 12/6/14.
 */
public class ExerciseFragment extends Fragment {

    private Exercise[] items;
    private HashMap<Integer, Boolean> checkedItems;
    private ExerciseListAdapter exerciseAdapter;
    private boolean selectModeOn;
    private long[] preSelected;

    public ExerciseFragment() {
        this.selectModeOn = false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_exercise, container, false);

        this.checkedItems = new HashMap<Integer, Boolean>();
        this.setExerciseAdapter(new ExerciseListAdapter(getActivity(), this.checkedItems));
        loadListView(rootView);

        this.preSelected = this.getActivity().getIntent().getLongArrayExtra(ExerciseActivity.EXERCISES_SELECTED);

        if(this.selectModeOn) {
            this.setHasOptionsMenu(true);
        }

        return rootView;
    }

    public ListAdapter getExerciseAdapter() {
        return exerciseAdapter;
    }

    public void setExerciseAdapter(ExerciseListAdapter exerciseAdapter) {
        this.exerciseAdapter = exerciseAdapter;
    }

    public long[] getSelectedItems() {
        ArrayList<Long> selected = new ArrayList<Long>();
        for(Map.Entry<Integer, Boolean> entry : this.checkedItems.entrySet()){
            if(entry.getValue()) {
                selected.add(this.items[entry.getKey()].getId());
            }
        }

        Long[] arr = selected.toArray(new Long[selected.size()]);
        long[] toReturn = new long[arr.length];
        for(int i = 0; i < arr.length; i++) {
            toReturn[i] = arr[i];
        }
        return toReturn;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.exercise_select, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        ExerciseActivity exerciseActivity = (ExerciseActivity) this.getActivity();
        Intent intent = new Intent(this.getActivity(), WorkoutEditActivity.class);
        switch(id) {
            case R.id.action_select:
                long[] selected = this.getSelectedItems();
                intent.putExtra(WorkoutEditActivity.WORKOUT_EXERCISES, selected);
                intent.putExtra(WorkoutEditActivity.WORKOUT_ID, exerciseActivity.getWorkoutid());
                intent.putExtra(WorkoutEditActivity.WORKOUT_NAME, exerciseActivity.getWorkout_name());
                NavUtils.navigateUpTo(this.getActivity(), intent);
                return true;
            case android.R.id.home:
                intent.putExtra(WorkoutEditActivity.WORKOUT_ID, exerciseActivity.getWorkoutid());
                intent.putExtra(WorkoutEditActivity.WORKOUT_NAME, exerciseActivity.getWorkout_name());
                NavUtils.navigateUpTo(this.getActivity(), intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void loadListView(View rootView) {

        List<Exercise> exercises = Select.from(Exercise.class).orderBy("name").list();
        this.items = exercises.toArray(new Exercise[exercises.size()]);
        for(int i = 0; i < this.items.length; i++) {
            this.checkedItems.put(i, false);
        }

        final ListView listView = (ListView) rootView.findViewById(R.id.list_exercise);
        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.exercise_fab);
        fab.setOnClickListener(new ExerciseFabListener(getActivity()));
        fab.attachToListView(listView);

        if(this.selectModeOn) {
            fab.setVisibility(View.GONE);
        }

        if(this.items.length == 0) {
            listView.setVisibility(View.GONE);
            rootView.setBackgroundColor(getResources().getColor(R.color.blue_200));
        } else {
            TextView noDataView = (TextView) rootView.findViewById(R.id.no_exercises_message);
            noDataView.setVisibility(View.GONE);
            this.exerciseAdapter.setValues(this.items);
            this.exerciseAdapter.setSelectModeOn(this.selectModeOn);
            this.exerciseAdapter.setPreSelected(this.preSelected);
            listView.setAdapter(this.exerciseAdapter);
            listView.setOnItemClickListener(new ExerciseOnItemClickListener(this.items, this.getActivity()));
        }
    }

    public long[] getPreSelected() {
        return preSelected;
    }

    public void setPreSelected(long[] preSelected) {
        this.preSelected = preSelected;
    }

    public ExerciseFragment setSelectModeOn(boolean selectModeOn) {
        this.selectModeOn = selectModeOn;
        return this;
    }

    public boolean isSelectModeOn() {
        return this.selectModeOn;
    }

    private class ExerciseOnItemClickListener implements AdapterView.OnItemClickListener {

        private Exercise[] items;
        private FragmentActivity activity;

        public ExerciseOnItemClickListener(Exercise[] items, FragmentActivity activity) {
            this.items = items;
            this.activity = activity;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Exercise exercise = this.items[position];

            Intent intent = new Intent(this.activity, ExerciseEditActivity.class);
            intent.putExtra(ExerciseEditActivity.EXERCISE_ID, exercise.getId());
            startActivity(intent);
        }
    }

}
