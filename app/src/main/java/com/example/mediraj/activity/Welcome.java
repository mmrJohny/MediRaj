package com.example.mediraj.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.example.mediraj.R;

public class Welcome extends AppCompatActivity {

    private int Splash_Screen = 2000;
    private boolean flag = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        //check for login in shared preference or roomdb
        //if logged in then flag value stay default or change to false
        //above two for steps for story board if story board is implemented




        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (flag){
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }else {
                    Intent intent = new Intent(getApplicationContext(),Login.class);
                    startActivity(intent);
                    finish();
                }
            }
        },Splash_Screen);
    }
}