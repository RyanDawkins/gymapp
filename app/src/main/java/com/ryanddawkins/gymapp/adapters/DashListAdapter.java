package com.ryanddawkins.gymapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ryanddawkins.gymapp.DrawerItem;
import com.ryanddawkins.gymapp.R;

/**
 * Created by dawkins on 11/30/14.
 */
public class DashListAdapter extends ArrayAdapter<DrawerItem> {

    private DrawerItem[] items;
    private Context context;
    private int resource;

    public DashListAdapter(Context context, int resource, DrawerItem[] items) {
        super(context, resource, items);
        this.context = context;
        this.items = items;
        this.resource = resource;
    }

    public DrawerItem[] getItems() {
        return items;
    }

    public void setItems(DrawerItem[] items) {
        this.items = items;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) this.context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(this.resource, parent, false);

        TextView textView = (TextView) rowView.findViewById(R.id.drawer_item_text);
        textView.setText(items[position].getText());
        return rowView;
    }

}
