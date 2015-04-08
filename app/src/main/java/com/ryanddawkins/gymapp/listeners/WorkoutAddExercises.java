package com.ryanddawkins.gymapp.listeners;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.EditText;

import com.ryanddawkins.gymapp.R;
import com.ryanddawkins.gymapp.Workout;
import com.ryanddawkins.gymapp.activities.ExerciseActivity;
import com.ryanddawkins.gymapp.activities.WorkoutEditActivity;
import com.ryanddawkins.gymapp.activities.WorkoutSelectExerciseActivity;

/**
 * Created by dawkins on 4/5/15.
 */
public class WorkoutAddExercises implements View.OnClickListener {

    private FragmentActivity activity;
    private Workout workout;

    public WorkoutAddExercises(FragmentActivity activity, Workout workout) {
        this.activity = activity;
        this.workout = workout;
    }

    @Override
    public void onClick(View v) {

        EditText workoutEditText =  (EditText) this.activity.findViewById(R.id.workout_name);
        String workoutName = workoutEditText.getText().toString();

//        HashMap<Long, Exercise> exercisesByWorkout = WorkoutExercise.getExercisesByWorkout(workout.getId());
//        Iterator it = exercisesByWorkout.entrySet().iterator();
//        long[] exercisePks = new long[exercisesByWorkout.size()];
//        int i = 0;
//        while (it.hasNext()) {
//            Map.Entry pair = (Map.Entry)it.next();
//            exercisePks[i] = ((Exercise) pair.getValue()).getId();
//            it.remove();
//            i++;
//        }

        long[] exercisePks = ((WorkoutEditActivity) activity).getSelected();

        Intent intent = new Intent(this.activity, WorkoutSelectExerciseActivity.class);
        intent.putExtra(ExerciseActivity.SELECT_MODE_ON, true);
        intent.putExtra(ExerciseActivity.EXERCISES_SELECTED, exercisePks);
        if(workout != null) {
            intent.putExtra(WorkoutSelectExerciseActivity.WORKOUT_ID, workout.getId());
        }
        intent.putExtra(WorkoutSelectExerciseActivity.WORKOUT_NAME, workoutName);
        this.activity.startActivity(intent);

    }
}
