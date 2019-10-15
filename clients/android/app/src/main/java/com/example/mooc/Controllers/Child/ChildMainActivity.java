package com.example.mooc.Controllers.Child;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;



import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.mooc.Controllers.Child.Fragments.ChildCourses;
import com.example.mooc.Controllers.Child.Fragments.ChildHistory;
import com.example.mooc.Controllers.Child.Fragments.ChildMarks;
import com.example.mooc.Controllers.Child.Fragments.ChildPlanning;
import com.example.mooc.Controllers.Child.Fragments.ChildProfile;

import com.example.mooc.Controllers.Guest.GuestMainActivity;
import com.example.mooc.Others.BasicAlertDialog;
import com.example.mooc.Others.SPHandler;
import com.example.mooc.R;

public class ChildMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private FragmentManager fragmentManager = getFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.child_activity_main);
        toolbar = findViewById(R.id.child_toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.child_drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.child_nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Fragment fd = new ChildProfile();
        fragmentManager.beginTransaction().replace(R.id.child_frame,fd).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.child_drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
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
            case R.id.child_nav_profile :
                    fragmentClass = ChildProfile.class;
            break;

            case R.id.child_nav_courses :
                    fragmentClass = ChildCourses.class;
                break;

            case R.id.child_nav_marks :
                    fragmentClass = ChildMarks.class;
                break;

//            case R.id.child_nav_planning :
//                    fragmentClass = ChildPlanning.class;
//                break;

            case R.id.child_nav_history :
                    fragmentClass = ChildHistory.class;
                break;

            case R.id.child_nav_disconnect :

                BasicAlertDialog builder = new BasicAlertDialog(this, getResources().getString(R.string.alert_disconnect)) {
                    @Override
                    public void OnPositive() {
                        SPHandler.clearSharedPreferences(ChildMainActivity.this, SPHandler.RESSOURCES);
                        Intent intent = new Intent(ChildMainActivity.this, GuestMainActivity.class);
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

        fragmentManager.beginTransaction().replace(R.id.child_frame,fragment).commit();

        item.setChecked(true);
        setTitle(item.getTitle());

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
