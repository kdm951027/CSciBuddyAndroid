package com.example.navigationtest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.navigationtest.DataTypes.Student;
import com.example.navigationtest.Databases.GroupDatabase;
import com.example.navigationtest.Databases.Keller1200SeatDatabase;
import com.example.navigationtest.Databases.LabDatabase;
import com.example.navigationtest.Databases.StudentDatabase;
import com.example.navigationtest.Pages.HomePage;
import com.example.navigationtest.Pages.GroupListPage;
import com.example.navigationtest.Pages.LabListPage;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    Fragment homePage, labListPage, groupListPage, profilePage;
    Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Instantiate the databases. NOTE: the order is important!
        // The student database MUST be instantiated before the group one.
        new StudentDatabase();
        new LabDatabase();
        new GroupDatabase();
        new Keller1200SeatDatabase();

        // Instantiate fragments.
        homePage = new HomePage();
        labListPage = new LabListPage();
        groupListPage = new GroupListPage();
        profilePage = StudentDatabase.getStudent(Student.currentX500).fragment;

        // Setup the drawer.
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // set the default fragment view.
        currentFragment = homePage;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, currentFragment);
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        currentFragment = null;

        // Set the appropriate fragment based on the menu selection.
        if (id == R.id.nav_homePage) {
            currentFragment = homePage;
        } else if (id == R.id.nav_labListPage) {
            currentFragment = labListPage;
        } else if (id == R.id.nav_groupListPage) {
            currentFragment = groupListPage;
        } else if (id == R.id.nav_profilePage) {
            currentFragment = profilePage;
        }

        // Replace the fragment.
        if (currentFragment != null){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, currentFragment);
            ft.addToBackStack(null);
            ft.commit();
        }

        // Close the drawer and switch the fragment AFTER the drawer has closed.
        // We switch it after to avoid the jerky/laggy animations.
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View view, float v) {

            }

            @Override
            public void onDrawerOpened(@NonNull View view) {

            }

            @Override
            public void onDrawerClosed(@NonNull View view) {
                if (currentFragment != null){
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.setCustomAnimations(
                            R.anim.fab_slide_in_from_left,
                            R.anim.fab_slide_out_to_right,
                            R.anim.fab_slide_in_from_right,
                            R.anim.fab_slide_out_to_left);
                    ft.replace(R.id.content_frame, currentFragment);
                    ft.addToBackStack(null);
                    ft.commit();
                }
            }

            @Override
            public void onDrawerStateChanged(int i) {

            }
        });
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
}
