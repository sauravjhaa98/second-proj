package com.example.admin.navidemo;

import android.Manifest;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView t1;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        t1=(TextView)findViewById(R.id.textView);
        ActivityCompat.requestPermissions(this,new  String[]{Manifest.permission.SEND_SMS,Manifest.permission.READ_SMS},1234);
        sharedPreferences=getSharedPreferences("login",MODE_PRIVATE);
        getSupportActionBar().setTitle("COSMOYOGAWORLD");



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (item.isChecked()) item.setChecked(false);
        else item.setChecked(true);
        switch (item.getItemId()) {
            case R.id.nav_home:
                Intent intent = new Intent(this, Home.class);
                this.startActivity(intent);
                break;
            case R.id.nav_login:
                Intent inte = new Intent(this, LoginActivity.class);
                this.startActivity(inte);
                break;
            case R.id.nav_services:
                Intent intm = new Intent(this, Services.class);
                this.startActivity(intm);
                break;
            case R.id.nav_apDetails:
                Intent intp = new Intent(this, Appointment_Details.class);
                this.startActivity(intp);
                break;

            default:
               //  myfm(new Apple());

               super.onOptionsItemSelected(item);

        }



        return true;
    }

    /* public void myfm(Fragment fmm){

         FragmentManager manager=getFragmentManager();
         FragmentTransaction tr=manager.beginTransaction();
         tr.replace(R.id.fmlayout,fmm);


         tr.commit();

     }
     */



}
