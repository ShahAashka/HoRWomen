package com.horwomen;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

public class NavActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

   SharedPreferences pref;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);



        session = new Session(this);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        pref = getSharedPreferences(LoginActivity.MYPREF,MODE_PRIVATE);

        this.setTitle(pref.getString("name","sry"));

    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        Fragment fragment = null;

        if(id == R.id.nav_complaint){
            startActivity(new Intent(this, ComplainActivity.class));
        }
        else if(id == R.id.nav_feed){
            startActivity(new Intent(this, FeedbackActivity.class));
        }
        else if(id == R.id.nav_hl){
            startActivity(new Intent(this, HostelLeavingActivity.class));
        }
        else if(id == R.id.nav_logout){
            finish();

session.logoutUser();

            startActivity(new Intent(this, LoginActivity.class));
        }

        return false;
    }
}
