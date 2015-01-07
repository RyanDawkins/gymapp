package com.ryanddawkins.gymapp;

import com.orm.SugarRecord;

/**
 * Created by dawkins on 12/14/14.
 */
public class Workout extends SugarRecord<Workout> {

    private String name;

    public Workout() {
        this(null);
    }

    public Workout(String name) {
        this.setName(name);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
