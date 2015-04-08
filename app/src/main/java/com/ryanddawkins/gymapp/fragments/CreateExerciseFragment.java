package com.ryanddawkins.gymapp.fragments;

import android.content.Context;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.ryanddawkins.gymapp.Exercise;
import com.ryanddawkins.gymapp.R;
import com.ryanddawkins.gymapp.listeners.ExerciseCreateClickListener;

/**
 * Created by dawkins on 12/13/14.
 */
public class CreateExerciseFragment extends Fragment {

    private View rootView;
    private View.OnClickListener saveButtonListener;
    private String saveBtnText;
    private Exercise exercise;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_create_exercise, container, false);

        this.rootView = rootView;

        // Setting up our spinner
        Spinner exerciseTypeSpinner = (Spinner) rootView.findViewById(R.id.exercise_type);
        ArrayAdapter<CharSequence> exerciseTypeAdapter = ArrayAdapter.createFromResource(
                getActivity(),
                R.array.exercise_type_list,
                android.R.layout.simple_spinner_item);
        exerciseTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        exerciseTypeSpinner.setAdapter(exerciseTypeAdapter);

        Button exerciseSaveBtn = (Button) this.rootView.findViewById(R.id.exercise_save_btn);
        exerciseSaveBtn.setOnClickListener(new ExerciseCreateClickListener(this.getActivity()));
        TextView exerciseName = (TextView) this.rootView.findViewById(R.id.exercise_name);

        if(this.exercise != null) {
            exerciseSaveBtn.setVisibility(View.GONE);
            exerciseTypeSpinner.setSelection(this.exercise.getType());
            exerciseName.setText(this.exercise.getName());
            setHasOptionsMenu(true);
        }

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
                this.saveExercise();
                return true;
            case R.id.action_delete:
                this.exercise.delete();
                NavUtils.navigateUpFromSameTask(this.getActivity());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setSaveButtonListener(View.OnClickListener listener) {
        this.saveButtonListener = listener;
    }

    public void setSaveBtnNameText(String text) {
        this.saveBtnText = text;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public void saveExercise() {
        EditText exerciseNameEditText = (EditText) this.getActivity().findViewById(R.id.exercise_name);
        this.exercise.setName(exerciseNameEditText.getText().toString());

        InputMethodManager imm = (InputMethodManager) this.getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(exerciseNameEditText.getWindowToken(), 0);

        Spinner exerciseTypeSpinner = (Spinner) this.getActivity().findViewById(R.id.exercise_type);
        switch(exerciseTypeSpinner.getSelectedItemPosition()) {
            case 0:
                this.exercise.setType(Exercise.AEROBIC);
                break;
            case 1:
                this.exercise.setType(Exercise.STRENGTH);
                break;
            case 2:
                this.exercise.setType(Exercise.Flexibility);
                break;
            case 3:
                this.exercise.setType(Exercise.BALANCE);
                break;
            default:
                this.exercise.setType(-1);
        }
        this.exercise.save();
        NavUtils.navigateUpFromSameTask(this.getActivity());
    }

}
