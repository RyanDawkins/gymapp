package com.ryanddawkins.gymapp.fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.ryanddawkins.gymapp.Exercise;
import com.ryanddawkins.gymapp.R;
import com.ryanddawkins.gymapp.Workout;
import com.ryanddawkins.gymapp.activities.WorkoutEditActivity;
import com.ryanddawkins.gymapp.adapters.ExerciseListAdapter;
import com.ryanddawkins.gymapp.listeners.WorkoutAddExercises;
import com.ryanddawkins.gymapp.listeners.WorkoutCreateClickListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by dawkins on 12/18/14.
 */
public class CreateWorkoutFragment extends Fragment {

    private View rootView;
    private View.OnClickListener saveButtonListener;
    private String saveBtnText;
    private Workout workout;
    private ExerciseListAdapter exerciseAdapter;
    private long[] selected;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_create_workout, container, false);

        this.rootView = rootView;

        boolean hasSelected = ((WorkoutEditActivity) this.getActivity()).hasSelected();

        String workoutName = this.getActivity().getIntent().getStringExtra(WorkoutEditActivity.WORKOUT_NAME);
        long workoutId = this.getActivity().getIntent().getLongExtra(WorkoutEditActivity.WORKOUT_ID, -1);

        if(workoutId != -1) {
            this.workout = Workout.findById(Workout.class, workoutId);
        }

        Button saveBtn = (Button) this.rootView.findViewById(R.id.workout_save_btn);

        EditText name = (EditText) this.rootView.findViewById(R.id.workout_name);
        if(hasSelected) {
            InputMethodManager imm = (InputMethodManager) this.getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(name.getWindowToken(), 0);

        }

        boolean hasWorkout = false;
        if(this.workout != null) {
            this.setHasOptionsMenu(true);
            saveBtn.setVisibility(View.GONE);
            name.setText(this.workout.getName());
            hasWorkout = true;
        } else if(workoutName != null) {
            name.setText(workoutName);
        } else {
            this.workout = null;
        }

        ((WorkoutEditActivity) this.getActivity()).setWorkout(this.workout);

        Button addExercises = (Button) this.rootView.findViewById(R.id.add_exercises_btn);
        addExercises.setOnClickListener(new WorkoutAddExercises(this.getActivity(), this.workout));

        saveBtn.setOnClickListener(new WorkoutCreateClickListener(this.getActivity(), this.workout));

        final ListView listView = (ListView) rootView.findViewById(R.id.list_exercise);
        this.exerciseAdapter = new ExerciseListAdapter(this.getActivity(), null);
        if(this.workout != null) {
            this.exerciseAdapter.setListMode(false, this.workout.getId());
        }
        listView.setAdapter(this.exerciseAdapter);

        new UpdateExercises().execute();

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_exercise, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id) {
            case R.id.action_save:
                this.saveWorkout();
                ((WorkoutEditActivity)this.getActivity()).saveSelected();
                return true;
            case R.id.action_delete:
                this.workout.delete();
                NavUtils.navigateUpFromSameTask(this.getActivity());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void saveWorkout() {
        EditText nameEditText = (EditText) this.getActivity().findViewById(R.id.workout_name);
        this.workout.setName(nameEditText.getText().toString());

        this.workout.save();

        InputMethodManager imm = (InputMethodManager) this.getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(nameEditText.getWindowToken(), 0);

        ((WorkoutEditActivity) this.getActivity()).saveSelected();

        NavUtils.navigateUpFromSameTask(this.getActivity());
    }

    public void setSaveButtonListener(View.OnClickListener listener) {
        this.saveButtonListener = listener;
    }

    public long[] getSelected() {
        return selected;
    }

    public void setSelected(long[] selected) {
        this.selected = selected;
    }

    public void setSaveBtnNameText(String text) {
        this.saveBtnText = text;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    private class UpdateExercises extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {

            HashMap<Long, Exercise> exercises = ((WorkoutEditActivity) getActivity()).getWorkoutExercises();

            TextView exerciseLabel = (TextView) rootView.findViewById(R.id.exercises_label);
            View verticalBar = (View) rootView.findViewById(R.id.vertical_bar);

            if(exercises == null || exercises.size() == 0) {
                exerciseLabel.setVisibility(View.GONE);
                verticalBar.setVisibility(View.GONE);
                return null;
            } else {
                exerciseLabel.setVisibility(View.VISIBLE);
                verticalBar.setVisibility(View.VISIBLE);
            }

            ArrayList<Exercise> exerciseArrayList = new ArrayList<Exercise>();
            Iterator it = exercises.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                String name = (((Exercise) pair.getValue()).getName());
                exerciseArrayList.add((Exercise)pair.getValue());
                it.remove();
            }

            exerciseAdapter.setValues(exerciseArrayList.toArray(new Exercise[exerciseArrayList.size()]));
            exerciseAdapter.notifyDataSetChanged();

            return null;
        }
    }

}
