package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment selectedFragment = null;

            switch (item.getItemId()) {

                case R.id.nav_home:
                    selectedFragment = new home_fragment();
                    break;

//                case R.id.nav_avapeople:
//                    selectedFragment = new twoFragment();
//                    break;
//
//                case R.id.nav_selepeople:
//                    selectedFragment = new threeFragment();
//                    break;

                case R.id.nav_feedback:
                    selectedFragment = new feedbackFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, selectedFragment).addToBackStack("").commit();
            return true;
        }
    };

    boolean doubleBackToExitPressedOnce = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String email= getIntent().getExtras().getString("user").toString();
        TextView myuser=(TextView)findViewById(R.id.txtMessage);
        myuser.setText(email);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BottomNavigationView bottomnav = findViewById(R.id.Bottom_Activity);
        bottomnav.setOnNavigationItemSelectedListener(navListener);


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) ;
        {
            bottomnav.setSelectedItemId(R.id.nav_home);
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.profile) {

            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new ProfileFragment()).addToBackStack("").commit();
        }
        else if (id == R.id.inbox) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new InboxFragment()).addToBackStack("").commit();
        }
        else if (id == R.id.wallet) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new WalletFragment()).addToBackStack("").commit();
        }
        else if (id == R.id.refferearn) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new RefferFragmant()).addToBackStack("").commit();
        }
        else if (id == R.id.contactus) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new ContactusFragmant()).addToBackStack("").commit();
        }
        else if (id == R.id.logout) {

            startActivity(new Intent(MainActivity.this, Login_Activity.class));
            //getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new Admin_Login()).addToBackStack("").commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

