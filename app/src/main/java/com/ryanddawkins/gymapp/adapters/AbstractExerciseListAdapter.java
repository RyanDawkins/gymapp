package com.ryanddawkins.gymapp.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.ryanddawkins.gymapp.Exercise;
import com.ryanddawkins.gymapp.R;

/**
 * Created by dawkins on 12/15/14.
 */
public abstract class AbstractExerciseListAdapter extends ArrayAdapter<Exercise> {
    protected final Context context;
    protected Exercise[] values;

    public AbstractExerciseListAdapter(Context context) {
        super(context, R.layout.exercise_row);
        this.context = context;
    }

    public void setValues(Exercise[] values) {
        this.clear();
        this.values = values;
        for(int i = 0; i < values.length; i++) {
            this.add(values[i]);
        }
    }

    public Exercise[] getValues() {
        return this.values;
    }

}
