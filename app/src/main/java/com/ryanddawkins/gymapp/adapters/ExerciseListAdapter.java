package com.ryanddawkins.gymapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.ryanddawkins.gymapp.Exercise;
import com.ryanddawkins.gymapp.R;
import com.ryanddawkins.gymapp.WorkoutExercise;
import com.ryanddawkins.gymapp.activities.ExerciseEditActivity;
import com.ryanddawkins.gymapp.activities.WorkoutEditActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by dawkins on 12/29/14.
 */
public class ExerciseListAdapter extends AbstractExerciseListAdapter {

    private HashMap<Integer, Boolean> checkedItems;
    private boolean selectModeOn;
    private boolean listMode;
    private long workoutId;
    private long[] preSelected;

    public ExerciseListAdapter(Context context, HashMap<Integer, Boolean> checkedItems) {
        super(context);
        this.checkedItems = checkedItems;
        this.listMode = true;
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
        View rowView;
        if(!this.selectModeOn) {
            rowView = inflater.inflate(R.layout.exercise_card, parent, false);
        } else {
            rowView = inflater.inflate(R.layout.exercise_row, parent, false);
            CheckBox checkbox = (CheckBox) rowView.findViewById(R.id.checkbox);
            checkbox.setOnClickListener(new CheckboxListener(position));
            checkbox.setVisibility(View.VISIBLE);


            boolean found = false;
            if(this.preSelected != null) {
                for (int i = 0; i < this.preSelected.length; i++) {
                    if (this.preSelected[i] == values[position].getId()) {
                        found = true;
                        break;
                    }
                }
            }

            if(found) {
                this.checkedItems.put(position, true);
                checkbox.setChecked(true);
            } else{
                checkbox.setChecked(false);
            }
            checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    checkedItems.put(position, !isChecked);
                }
            });
        }

        TextView exercise_name = (TextView) rowView.findViewById(R.id.exercise_name);
        exercise_name.setText(values[position].getName());

        String[] exercise_items = getContext().getResources().getStringArray(R.array.exercise_type_list);
        String exercise_type_string = exercise_items[values[position].getType()];

        TextView exercise_type = (TextView) rowView.findViewById(R.id.exercise_type);
        exercise_type.setText(exercise_type_string);

        Button editBtn = (Button) rowView.findViewById(R.id.exercise_edit);
        if(editBtn != null && this.listMode) {
            editBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ExerciseEditActivity.class);
                    intent.putExtra(ExerciseEditActivity.EXERCISE_ID, values[position].getId());
                    context.startActivity(intent);
                }
            });
        } else if(editBtn != null && !this.listMode) {
            editBtn.setVisibility(View.GONE);
        }

        Button deleteBtn = (Button) rowView.findViewById(R.id.exercise_delete);
        if(deleteBtn != null && listMode){
            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    values[position].delete();
                    remove(values[position]);
                    notifyDataSetChanged();
                }
            });
        } else if(deleteBtn != null && !listMode){
            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Exercise exercise = values[position];

                    ArrayList<Exercise> exerciseArrayList = new ArrayList<Exercise>(Arrays.asList(values));
                    exerciseArrayList.remove(position);
                    setValues(exerciseArrayList.toArray(new Exercise[exerciseArrayList.size()]));

                    WorkoutExercise workoutExercise = WorkoutExercise.getByComposite(workoutId, exercise.getId());
                    if(workoutExercise != null) {
                        workoutExercise.delete();
                    }

                    ((WorkoutEditActivity) context).getWorkoutExercises().remove(new Long(exercise.getId()));
                    long[] longs = ((WorkoutEditActivity)context).getSelected();
                    Long[] longs2 = new Long[longs.length];
                    for(int i = 0; i < longs.length; i++) {
                        longs2[i] = new Long(longs[i]);
                    }
                    List<Long> exercisesList = new ArrayList<Long>(Arrays.asList(longs2));
                    exercisesList.remove(new Long(exercise.getId()));
                    longs = new long[exercisesList.size()];
                    for(int i = 0; i < exercisesList.size(); i++) {
                        longs[i] = exercisesList.get(i);
                    }
                    ((WorkoutEditActivity) context).setSelected(longs);

                    if(values.length == 0) {
                        ((TextView) ((WorkoutEditActivity) context).findViewById(R.id.exercises_label)).setVisibility(View.GONE);
                    }

                    notifyDataSetChanged();
                }
            });
        }

        return rowView;
    }

    public long[] getPreSelected() {
        return preSelected;
    }

    public void setPreSelected(long[] preSelected) {
        this.preSelected = preSelected;
    }

    public boolean isListMode() {
        return listMode;
    }

    public void setListMode(boolean listMode, long workoutId) {
        this.listMode = listMode;
        this.workoutId = workoutId;
    }

    private class CheckboxListener implements View.OnClickListener {

        private int position;

        public CheckboxListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            boolean value = checkedItems.get(this.position);
            Exercise exercise = values[position];
            checkedItems.put(position, !value);
        }
    }

}
