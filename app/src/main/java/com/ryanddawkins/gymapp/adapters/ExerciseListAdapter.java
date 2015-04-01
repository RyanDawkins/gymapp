package com.ryanddawkins.gymapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.ryanddawkins.gymapp.R;

import java.util.HashMap;

/**
 * Created by dawkins on 12/29/14.
 */
public class ExerciseListAdapter extends AbstractExerciseListAdapter {

    private HashMap<Integer, Boolean> checkedItems;
    private boolean selectModeOn;

    public ExerciseListAdapter(Context context, HashMap<Integer, Boolean> checkedItems) {
        super(context);
        this.checkedItems = checkedItems;
    }

    public boolean isSelectModeOn() {
        return selectModeOn;
    }

    public void setSelectModeOn(boolean selectModeOn) {
        this.selectModeOn = selectModeOn;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.exercise_row, parent, false);

        CheckBox checkbox = (CheckBox) rowView.findViewById(R.id.checkbox);
        checkbox.setOnClickListener(new CheckboxListener(position));
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkedItems.put(position, isChecked);
            }
        });
        if(this.isSelectModeOn()) {
            checkbox.setVisibility(View.VISIBLE);
            checkbox.setChecked(true);
        }

        TextView exercise_name = (TextView) rowView.findViewById(R.id.exercise_name);
        exercise_name.setText(values[position].getName());

        String[] exercise_items = getContext().getResources().getStringArray(R.array.exercise_type_list);
        String exercise_type_string = exercise_items[values[position].getType()];

        TextView exercise_type = (TextView) rowView.findViewById(R.id.exercise_type);
        exercise_type.setText(exercise_type_string);

        return rowView;
    }

    private class CheckboxListener implements View.OnClickListener {

        private int position;

        public CheckboxListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            boolean value = checkedItems.get(this.position);
            checkedItems.put(this.position, !value);
        }
    }

}
