package com.ryanddawkins.gymapp.listeners;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.ryanddawkins.gymapp.Auth;
import com.ryanddawkins.gymapp.AuthFactory;
import com.ryanddawkins.gymapp.R;
import com.ryanddawkins.gymapp.activities.DashActivity;

/**
 * Created by dawkins on 11/25/14.
 */
public class LoginButtonListener implements View.OnClickListener {

    private Activity activity;

    public LoginButtonListener(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {

        EditText usernameText = (EditText) this.activity.findViewById(R.id.login_username);
        EditText passwordText = (EditText) this.activity.findViewById(R.id.login_password);
        String username = usernameText.getText().toString();
        String password = passwordText.getText().toString();

        AuthFactory authFactory = new AuthFactory();
        Auth auth = authFactory.getAuth();
        if(auth.authorize(username, password)) {
            Intent dashIntent = new Intent(this.activity, DashActivity.class);
            this.activity.startActivity(dashIntent);
            this.activity.finish();
        } else {

        }
    }
}
