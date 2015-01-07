package com.ryanddawkins.gymapp;

/**
 * Created by dawkins on 11/25/14.
 */
public class FakeAuth implements Auth {


    @Override
    public boolean authorize(String username, String password) {
        return true;
    }

    @Override
    public boolean isLoggedin() {
        return false;
    }
}
