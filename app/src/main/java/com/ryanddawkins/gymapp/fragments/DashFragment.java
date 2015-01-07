package com.ryanddawkins.gymapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ryanddawkins.gymapp.R;

/**
 * Created by dawkins on 11/28/14.
 */
public class DashFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dash, container, false);
        return rootView;
    }

}
