package com.vinu.internship;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.content.ActivityNotFoundException;
import android.content.Context;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RelativeLayout;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class resume_preview extends AppCompatActivity {
    TextView namep, phonep, emailp, addressp, skillsp, langp, edup, pro1np, pro2np, pro1dp, pro2dp, expp;
    ConstraintLayout constraintLayout11;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume_preview);
        constraintLayout11=findViewById(R.id.constrainlayout11);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("key");
            String value1 = extras.getString("key1");
            String value2 = extras.getString("key2");
            String value3 = extras.getString("key3");
            String value4 = extras.getString("key4");
            String value5 = extras.getString("key5");
            String value6 = extras.getString("key6");
            String value7 = extras.getString("key7");
            String value8 = extras.getString("key8");
            String value9 = extras.getString("key9");
            String value10 = extras.getString("key10");
            String value11 = extras.getString("key11");
            String value12 = extras.getString("key12");
            String value13 = extras.getString("key13");
            namep = findViewById(R.id.namep);
            phonep = findViewById(R.id.phonep);
            emailp = findViewById(R.id.emailp);
            addressp = findViewById(R.id.addressp);
            skillsp = findViewById(R.id.skillsp);
            langp = findViewById(R.id.langp);
            pro1dp = findViewById(R.id.pro1dp);
            pro1np = findViewById(R.id.pro1np);
            pro2np = findViewById(R.id.pro2np);
            edup = findViewById(R.id.edup);
            pro2dp = findViewById(R.id.pro2dp);
            expp = findViewById(R.id.expp);
            namep.setText(value);
            phonep.setText(value1);
            emailp.setText(value2);
            addressp.setText(value3);
            skillsp.setText(value4);
            langp.setText(value5);
            edup.setText("class 10th cgpa is " + value6 + "\n" + "class 12th percentage is " + value7 + "%" + "\n" + "cgpa in college is " + value8);
            pro1np.setText(value9);
            pro1dp.setText(value10);
            pro2np.setText(value11);
            pro2dp.setText(value12);
            expp.setText(value13);
        }
    }

}
