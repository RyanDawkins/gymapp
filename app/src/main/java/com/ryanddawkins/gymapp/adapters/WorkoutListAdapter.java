package com.ryanddawkins.gymapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ryanddawkins.gymapp.R;
import com.ryanddawkins.gymapp.Workout;

/**
 * Created by dawkins on 12/20/14.
 */
public class WorkoutListAdapter extends ArrayAdapter<Workout> {
    private final Context context;
    private final Workout[] values;

    public WorkoutListAdapter(Context context, Workout[] values) {
        super(context, R.layout.exercise_row, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.workout_row, parent, false);

        TextView name = (TextView) rowView.findViewById(R.id.name);
        name.setText(values[position].getName());

        return rowView;
    }
}
