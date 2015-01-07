package com.ryanddawkins.gymapp;

/**
 * Created by dawkins on 11/25/14.
 */
public interface Auth {

    public boolean authorize(String username, String password);

    public boolean isLoggedin();

}
