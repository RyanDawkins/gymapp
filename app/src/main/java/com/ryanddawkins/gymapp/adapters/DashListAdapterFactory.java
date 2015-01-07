package com.ryanddawkins.gymapp.adapters;

import android.content.Context;
import android.content.res.TypedArray;

import com.ryanddawkins.gymapp.DrawerItem;
import com.ryanddawkins.gymapp.R;

/**
 * Created by dawkins on 12/2/14.
 */
public class DashListAdapterFactory {

    private Context context;
    private int resource;

    public DashListAdapterFactory(Context context, int resource) {
        this.context = context;
        this.resource = resource;
    }

    public DashListAdapter dash() {
        TypedArray navMenuTitles = context.getResources().obtainTypedArray(R.array.drawer_items);
        TypedArray navMenuIcons = context.getResources().obtainTypedArray(R.array.drawer_icons);

        DrawerItem[] items = new DrawerItem[navMenuTitles.length()];
        for(int i = 0; i < navMenuTitles.length(); i++) {
            String title = navMenuTitles.getString(i);

            //int resource = navMenuIcons.getResources().getIdentifier("icon_"+title, "drawable", context.getPackageName());
            DrawerItem item = new DrawerItem();
            item.setText(title);

            items[i] = item;
        }

        DashListAdapter adapter = new DashListAdapter(context, resource, items);

        return adapter;
    }

}
