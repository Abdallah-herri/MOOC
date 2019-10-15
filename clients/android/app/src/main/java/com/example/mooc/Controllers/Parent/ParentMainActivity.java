package com.example.mooc.Controllers.Parent;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.mooc.Controllers.Guest.GuestMainActivity;
import com.example.mooc.Controllers.Parent.Fragments.ParentChildren;
import com.example.mooc.Controllers.Parent.Fragments.ParentProfile;
import com.example.mooc.Others.BasicAlertDialog;
import com.example.mooc.Others.SPHandler;
import com.example.mooc.R;

public class ParentMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private Toolbar toolbar;
    private NavigationView navigationView;
    FragmentManager fragmentManager = getFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parent_activity_main);
        toolbar = findViewById(R.id.parent_toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.parent_drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.parent_nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Fragment fd = new ParentProfile();
        fragmentManager.beginTransaction().replace(R.id.parent_frame,fd).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.parent_drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            finish();
            moveTaskToBack(true);
            System.exit(0);
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment fragment = null;
        Class fragmentClass;

        int id = item.getItemId();

        switch (id)
        {
            case R.id.parent_nav_profile :
                fragmentClass = ParentProfile.class;
                break;

            case R.id.parent_nav_children :
                fragmentClass = ParentChildren.class;
                break;


            case R.id.parent_nav_disconnect :

                BasicAlertDialog builder = new BasicAlertDialog(ParentMainActivity.this, getResources().getString(R.string.alert_disconnect)) {
                    @Override
                    public void OnPositive() {
                        SPHandler.clearSharedPreferences(ParentMainActivity.this, SPHandler.RESSOURCES);
                        Intent intent = new Intent(ParentMainActivity.this, GuestMainActivity.class);
                        startActivity(intent);
                    }
                };

                AlertDialog dialog = builder.create();
                dialog.show();
                return true;

            default:
                fragmentClass = null;
                break;
        }

        try
        {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        fragmentManager.beginTransaction().replace(R.id.parent_frame,fragment).commit();

        item.setChecked(true);
        setTitle(item.getTitle());

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
