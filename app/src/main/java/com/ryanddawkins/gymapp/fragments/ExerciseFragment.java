package com.ryanddawkins.gymapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.melnykov.fab.FloatingActionButton;
import com.orm.query.Select;
import com.ryanddawkins.gymapp.Exercise;
import com.ryanddawkins.gymapp.R;
import com.ryanddawkins.gymapp.adapters.ExerciseListAdapter;
import com.ryanddawkins.gymapp.listeners.ExerciseCreateClickListener;
import com.ryanddawkins.gymapp.listeners.ExerciseFabListener;

import java.util.HashMap;
import java.util.List;

/**
 * Created by dawkins on 12/6/14.
 */
public class ExerciseFragment extends Fragment {

    private Exercise[] items;
    private HashMap<Integer, Boolean> checkedItems;
    private ExerciseListAdapter exerciseAdapter;
    private boolean selectModeOn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_exercise, container, false);

        this.checkedItems = new HashMap<Integer, Boolean>();
        this.selectModeOn = false;
        this.setExerciseAdapter(new ExerciseListAdapter(getActivity(), this.checkedItems));
        loadListView(rootView);

        return rootView;
    }

    public ListAdapter getExerciseAdapter() {
        return exerciseAdapter;
    }

    public void setExerciseAdapter(ExerciseListAdapter exerciseAdapter) {
        this.exerciseAdapter = exerciseAdapter;
    }

    public void loadListView(View rootView) {

        List<Exercise> exercises = Select.from(Exercise.class).orderBy("name").list();
        this.items = exercises.toArray(new Exercise[exercises.size()]);
        for(int i = 0; i < this.items.length; i++) {
            this.checkedItems.put(i, false);
        }

        ListView listView = (ListView) rootView.findViewById(R.id.list_exercise);

        if(this.items.length == 0) {
            listView.setVisibility(View.GONE);
            rootView.setBackgroundColor(getResources().getColor(R.color.blue_200));
        } else {
            TextView noDataView = (TextView) rootView.findViewById(R.id.no_exercises_message);
            noDataView.setVisibility(View.GONE);
            this.exerciseAdapter.setValues(this.items);
            listView.setAdapter(this.exerciseAdapter);
            listView.setOnItemClickListener(new ExerciseOnItemClickListener(this.items, this.getActivity()));
            listView.setOnItemLongClickListener(new OnLongItemClick());
        }

        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.exercise_fab);
        fab.setOnClickListener(new ExerciseFabListener(getActivity()));

        fab.attachToListView(listView);
    }

    private class OnLongItemClick implements AdapterView.OnItemLongClickListener {

        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

            if(!selectModeOn) {
                boolean value = checkedItems.get(position);
                checkedItems.put(position, !value);
                CheckBox checkbox = (CheckBox) view.findViewById(R.id.checkbox);
                checkbox.setChecked(!value);
                checkbox.setVisibility(View.VISIBLE);
            }

            return false;
        }
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

            CreateExerciseFragment createExerciseFragment = new CreateExerciseFragment();
            createExerciseFragment.setSaveBtnNameText(this.activity.getString(R.string.exercise_save_btn));
            createExerciseFragment.setSaveButtonListener(new ExerciseCreateClickListener(this.activity, exercise));
            createExerciseFragment.setExercise(exercise);

            this.activity.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_frame, createExerciseFragment)
                    .commit();
        }
    }

}
