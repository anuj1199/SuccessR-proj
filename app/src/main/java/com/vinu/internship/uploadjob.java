package com.vinu.internship;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.textfield.TextInputEditText;

public class uploadjob extends AppCompatActivity {
    TextInputEditText field,lastdate,vacancies,qualification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadjob);
        field = findViewById(R.id.job_field);
        lastdate = findViewById(R.id.last_date_job);
        vacancies = findViewById(R.id.vacancies);
        qualification = findViewById(R.id.qualification);

    }
}