package com.ryanddawkins.gymapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.ryanddawkins.gymapp.Exercise;
import com.ryanddawkins.gymapp.R;
import com.ryanddawkins.gymapp.listeners.ExerciseDeleteOnClickListener;

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
        exerciseSaveBtn.setOnClickListener(this.saveButtonListener);
        exerciseSaveBtn.setText(this.saveBtnText);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        

        TextView exerciseName = (TextView) this.rootView.findViewById(R.id.exercise_name);

        if(this.exercise != null) {
            exerciseTypeSpinner.setSelection(this.exercise.getType());
            exerciseName.setText(this.exercise.getName());

            Button deleteButton = (Button) this.rootView.findViewById(R.id.exercise_delete_btn);
            deleteButton.setVisibility(View.VISIBLE);
            deleteButton.setOnClickListener(new ExerciseDeleteOnClickListener(this.getActivity(), this.exercise));
        }

        return rootView;
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

}
