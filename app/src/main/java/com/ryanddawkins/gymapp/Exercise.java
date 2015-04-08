package com.ryanddawkins.gymapp;

import com.orm.SugarRecord;

/**
 * Created by dawkins on 12/13/14.
 */

public class Exercise extends SugarRecord<Exercise> {

    public static int AEROBIC = 0;
    public static int STRENGTH = 1;
    public static int Flexibility = 2;
    public static int BALANCE = 3;

    public String name;
    public int type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}