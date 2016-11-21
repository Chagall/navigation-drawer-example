package com.pereira.fabio.navigationdrawerexample;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by fabiopereira on 11/19/16.
 */

public class DrawerListAdapter extends ArrayAdapter<DrawerItem> {

    private Context context;
    private int layoutResID;
    private List<DrawerItem> drawerItemList;

    public DrawerListAdapter(Context context, int layoutResourceID, List<DrawerItem> listItems) {
        super(context, layoutResourceID, listItems);
        this.context = context;
        this.drawerItemList = listItems;
        this.layoutResID = layoutResourceID;
    }

    private static class DrawerItemHolder {
        TextView itemNameTextView;
        ImageView iconImageView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        DrawerItemHolder drawerHolder;

        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            drawerHolder = new DrawerItemHolder();

            convertView = inflater.inflate(layoutResID, parent, false);
            drawerHolder.itemNameTextView = (TextView) convertView.findViewById(R.id.nav_drawer_item_text);
            drawerHolder.iconImageView = (ImageView) convertView.findViewById(R.id.nav_drawer_item_image);

            convertView.setTag(drawerHolder);

        } else {
            drawerHolder = (DrawerItemHolder) convertView.getTag();
        }

        DrawerItem drawerItem = drawerItemList.get(position);

        drawerHolder.iconImageView.setImageDrawable(convertView.getResources().getDrawable(drawerItem.getImageResID()));
        drawerHolder.itemNameTextView.setText(drawerItem.getItemName());

        return convertView;
    }


}
