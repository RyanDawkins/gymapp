package com.ryanddawkins.gymapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ryanddawkins.gymapp.R;
import com.ryanddawkins.gymapp.listeners.LoginButtonListener;

/**
 * Created by dawkins on 11/25/14.
 */
public class LoginFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        Button loginButton = (Button) rootView.findViewById(R.id.login_button);
        loginButton.setOnClickListener(new LoginButtonListener(getActivity()));

        return rootView;
    }

}