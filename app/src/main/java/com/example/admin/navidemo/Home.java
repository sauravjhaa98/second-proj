package com.example.admin.navidemo;

import android.content.BroadcastReceiver;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.widget.TextView;

public class Home extends AppCompatActivity {
    private BroadcastReceiver mNetworkReceiver;
    static Thread thread;
    static TextView tv_check_connection;
    static Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setTitle(" COSMOYOGAWORLD");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ViewPager mViewPager = (ViewPager) findViewById(R.id.viewPage);
        ImageAdapter adapterView = new ImageAdapter(this);
        mViewPager.setAdapter(adapterView);
    }

}
