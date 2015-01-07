package com.ryanddawkins.gymapp;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.ryanddawkins.gymapp.adapters.DashListAdapterFactory;
import com.ryanddawkins.gymapp.fragments.DashFragment;
import com.ryanddawkins.gymapp.listeners.NavigationDrawerListener;

/**
 * Created by dawkins on 11/28/14.
 */
public class DashActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_dash);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content_frame, new DashFragment())
                    .commit();
        }


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if(toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setElevation(4);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            new ActionBarDrawerToggle(this, drawerLayout,
                    toolbar, // Nav drawer Icon
                    R.string.app_name, // Nav drawer open - description for accessibility
                    R.string.app_name // Nav drawer close
            ) {
                @Override
                public void onDrawerOpened(View drawerView) {
                    getSupportActionBar().setTitle(getTitle());
                    invalidateOptionsMenu();
                }

                @Override
                public void onDrawerClosed(View drawerView) {
                    getSupportActionBar().setTitle(getTitle());
                    invalidateOptionsMenu();
                }
            };
        }

        ListView drawerList = (ListView) findViewById(R.id.left_drawer);
        drawerList.setOnItemClickListener(new NavigationDrawerListener(this));

        DashListAdapterFactory dashListAdapterFactory = new DashListAdapterFactory(this, R.layout.drawer_item);
        drawerList.setAdapter(dashListAdapterFactory.dash());
    }

}
