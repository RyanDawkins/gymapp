package com.ryanddawkins.gymapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ryanddawkins.gymapp.R;
import com.ryanddawkins.gymapp.Workout;
import com.ryanddawkins.gymapp.listeners.WorkoutDeleteClickListener;

/**
 * Created by dawkins on 12/18/14.
 */
public class CreateWorkoutFragment extends Fragment {

    private View rootView;
    private View.OnClickListener saveButtonListener;
    private String saveBtnText;
    private Workout workout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_create_workout, container, false);

        this.rootView = rootView;

        Button saveBtn = (Button) this.rootView.findViewById(R.id.save_btn);
        if(this.saveButtonListener != null) {
            saveBtn.setOnClickListener(this.saveButtonListener);
        }
        saveBtn.setText(this.saveBtnText);

        TextView name = (TextView) this.rootView.findViewById(R.id.name);

        if(this.workout != null) {
            name.setText(this.workout.getName());

            Button deleteButton = (Button) this.rootView.findViewById(R.id.delete_btn);
            deleteButton.setVisibility(View.VISIBLE);
            deleteButton.setOnClickListener(new WorkoutDeleteClickListener(this.getActivity(), this.workout));
        }

        return rootView;
    }

    public void setSaveButtonListener(View.OnClickListener listener) {
        this.saveButtonListener = listener;
    }

    public void setSaveBtnNameText(String text) {
        this.saveBtnText = text;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

}
