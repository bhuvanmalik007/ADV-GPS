package com.abdroid.wps2;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

import java.util.List;


@SuppressLint("NewApi")
public class Splash extends Activity {

    // private Camera camera;



    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar bar = getActionBar();
        bar.hide();


        setContentView(R.layout.activity_splash);




        new Thread()
        {
            public void run()
            {
                try
                {
                    Thread.sleep(3000);
                    Intent i= new Intent (Splash.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }
                catch(InterruptedException e)
                {
                }

            }
        }.start();
    }
}
