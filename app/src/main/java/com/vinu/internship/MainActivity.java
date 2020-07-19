package com.vinu.internship;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static int splash_screen_time = 1800;
    Button skipbtn;
    ImageView logo;
    TextView welcome,tagline,developer;
    Animation bottomupanim,fade,leftinanim,rightinanim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        skipbtn = findViewById(R.id.skip_btn_main);
        logo = findViewById(R.id.imageView_logo);
        //welcome = findViewById(R.id.textViewwelcome);
        //tagline = findViewById(R.id.main_tagline);
        //developer = findViewById(R.id.textdeveloper);
        bottomupanim = AnimationUtils.loadAnimation(this,R.anim.bottom_upanim);
        fade = AnimationUtils.loadAnimation(this,R.anim.fade);
        leftinanim = AnimationUtils.loadAnimation(this,R.anim.left_in);
        rightinanim = AnimationUtils.loadAnimation(this,R.anim.right_in);
         logo.setAnimation(fade);
         //welcome.setAnimation(leftinanim);
         //tagline.setAnimation(rightinanim);
         //developer.setAnimation(leftinanim);
         skipbtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent a =  new Intent(MainActivity.this,OnBoardingScreen.class);
                 startActivity(a);
             }
         });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {
                Intent a = new Intent(MainActivity.this,OnBoardingScreen.class);
                startActivity(a);
            }
        },splash_screen_time);
    }
}