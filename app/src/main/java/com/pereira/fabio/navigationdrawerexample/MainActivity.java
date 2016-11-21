package com.pereira.fabio.navigationdrawerexample;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.left_drawer)
    ListView drawerListView;
    private ActionBarDrawerToggle drawerToggle;

    private String drawerTitle;
    private String title;
    DrawerListAdapter drawerListAdapter;

    List<DrawerItem> drawerItemsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        title = drawerTitle = getTitle().toString();
        //drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        initDataArrayList();
        initDrawerListAdapter();

        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        drawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                R.string.drawer_open,
                R.string.drawer_close) {
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(title);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                drawerView.bringToFront();
                getSupportActionBar().setTitle(title);
                invalidateOptionsMenu();
            }
        };

        drawerLayout.setDrawerListener(drawerToggle);

        if (savedInstanceState == null) {
            selectDrawerItem(0);
        }
    }

    private void initDataArrayList() {
        drawerItemsList = new ArrayList<>();
        drawerItemsList.add(new DrawerItem("Perfil", R.drawable.ic_person_black_48dp));
        drawerItemsList.add(new DrawerItem("Mapa", R.drawable.ic_person_black_48dp));
        drawerItemsList.add(new DrawerItem("Configurações", R.drawable.ic_person_black_48dp));
    }

    private void initDrawerListAdapter() {
        drawerListAdapter = new DrawerListAdapter(this, R.layout.nav_drawer_item, drawerItemsList);
        drawerListView.setAdapter(drawerListAdapter);
        drawerListView.setOnItemClickListener(new DrawerItemClickListener());
    }

    public void selectDrawerItem(int item) {
        Fragment fragment;

        switch (item) {
            case 0:
                fragment = new ProfileFragment();
                break;
            case 1:
                fragment = new MapFragment();
                break;
            case 2:
                fragment = new ConfigFragment();
                break;
            default:
                fragment = new MapFragment();
                break;
        }

        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        drawerListView.setItemChecked(item, true);
        setTitle(drawerItemsList.get(item).getItemName());
        drawerLayout.closeDrawer(drawerListView);
    }

    @Override
    public void setTitle(CharSequence title) {
        this.title = title.toString();
        getSupportActionBar().setTitle(this.title);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return false;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectDrawerItem(position);

        }
    }
}
