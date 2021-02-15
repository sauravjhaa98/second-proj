package com.example.admin.navidemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        Thread y=new Thread(){
            public void run(){
                try{
                    sleep(4000);
                }catch (Exception t){

                }
                finally {
                    {
                        Intent i=new Intent(Splash.this,MainActivity.class);
                        startActivity(i);
                        finish();
                    }
                }
            }
        };
        y.start();
    }
}



