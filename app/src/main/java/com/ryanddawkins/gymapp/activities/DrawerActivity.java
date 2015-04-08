package com.ryanddawkins.gymapp.activities;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ListView;

import com.ryanddawkins.gymapp.R;
import com.ryanddawkins.gymapp.adapters.DashListAdapterFactory;
import com.ryanddawkins.gymapp.listeners.NavigationDrawerListener;

/**
 * Created by dawkins on 11/28/14.
 */
public class DrawerActivity extends ActionBarActivity {

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_dash);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setElevation(4);
            getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);

            this.mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                    toolbar, R.string.app_name, R.string.app_name) {

                /** Called when a drawer has settled in a completely closed state. */
                public void onDrawerClosed(View view) {
                    super.onDrawerClosed(view);
                    invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
                }

                /** Called when a drawer has settled in a completely open state. */
                public void onDrawerOpened(View drawerView) {
                    super.onDrawerOpened(drawerView);
                    invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
                }
            };

            // Set the drawer toggle as the DrawerListener
            mDrawerLayout.setDrawerListener(mDrawerToggle);
            mDrawerToggle.syncState();
        }

        ListView drawerList = (ListView) findViewById(R.id.left_drawer);
        drawerList.setOnItemClickListener(new NavigationDrawerListener(this));

        DashListAdapterFactory dashListAdapterFactory = new DashListAdapterFactory(this, R.layout.drawer_item);
        drawerList.setAdapter(dashListAdapterFactory.dash());
    }

    public void closeDrawer() {
        this.mDrawerLayout.closeDrawer(Gravity.LEFT);
    }

}
