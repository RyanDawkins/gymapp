package com.ryanddawkins.gymapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ryanddawkins.gymapp.R;

/**
 * Created by dawkins on 1/1/15.
 */
public class ExerciseSelectAdapter extends AbstractExerciseListAdapter {

    public ExerciseSelectAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.exercise_row, parent, false);


        TextView exercise_name = (TextView) rowView.findViewById(R.id.exercise_name);
        exercise_name.setText(values[position].getName());

        String[] exercise_items = getContext().getResources().getStringArray(R.array.exercise_type_list);
        String exercise_type_string = exercise_items[values[position].getType()];

        TextView exercise_type = (TextView) rowView.findViewById(R.id.exercise_type);
        exercise_type.setText(exercise_type_string);

        return rowView;
    }

}
