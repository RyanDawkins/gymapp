package com.ryanddawkins.gymapp;

/**
 * Created by dawkins on 11/25/14.
 */
public class AuthFactory {

    public Auth getAuth() {
        return new FakeAuth();
    }

}
