package com.vinu.internship;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class MainActivityInternUpload extends AppCompatActivity {

    private TextInputEditText field,dateup,qualification,vacancy;
    Button upload_job;
    DatabaseReference DataRef;
    String monthstring="";
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_intern_upload);
        field = findViewById(R.id.intern_field);
        dateup = findViewById(R.id.last_date_internship);
        qualification = findViewById(R.id.qualification);
        vacancy = findViewById(R.id.vacancies);
        upload_job = findViewById(R.id.upload_intern_btn);
        DataRef = FirebaseDatabase.getInstance().getReference().child("INTERNSHIP");
        dateup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        MainActivityInternUpload.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                Date newdate = cal.getTime();
                dialog.getDatePicker().setMinDate(newdate.getTime()-(newdate.getTime()%(24*60*60*1000)));
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                if(month==1) {
                    monthstring = "Jan";
                    String date = day + "  " + monthstring + " " + year;
                    dateup.setText(date);
                }
                if(month==2) {
                    monthstring = "Feb";
                    String date = day + "  " + monthstring + " " + year;
                    dateup.setText(date);
                }
                if(month==3) {
                    monthstring = "March";
                    String date = day + "  " + monthstring + " " + year;
                    dateup.setText(date);
                }
                if(month==4) {
                    monthstring = "April";
                    String date = day + "  " + monthstring + " " + year;
                    dateup.setText(date);
                }
                if(month==5) {
                    monthstring = "May";
                    String date = day + "  " + monthstring + " " + year;
                    dateup.setText(date);
                }
                if(month==6) {
                    monthstring = "June";
                    String date = day + "  " + monthstring + " " + year;
                    dateup.setText(date);
                }
                if(month==7) {
                    monthstring = "July";
                    String date = day + "  " + monthstring + " " + year;
                    dateup.setText(date);
                }
                if(month==8) {
                    monthstring = "Aug";
                    String date = day + "  " + monthstring + " " + year;
                    dateup.setText(date);
                }
                if(month==9) {
                    monthstring = "Sep";
                    String date = day + "  " + monthstring + " " + year;
                    dateup.setText(date);
                }
                if(month==10) {
                    monthstring = "Oct";
                    String date = day + "  " + monthstring + " " + year;
                    dateup.setText(date);
                }
                if(month==11) {
                    monthstring = "Nov";
                    String date = day + "  " + monthstring + " " + year;
                    dateup.setText(date);
                }
                if(month==12) {
                    monthstring = "Dec";
                    String date = day + "  " + monthstring + " " + year;
                    dateup.setText(date);
                }


            }
        };
        upload_job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE, dd-MMM-yyyy,hh:mm:ss a");
                final String DATE = simpleDateFormat.format(calendar.getTime());
                final String FIELD = field.getText().toString();
                final String LASTDATE = dateup.getText().toString();
                final String QUALIFICATION = qualification.getText().toString();
                final String VACANCY = vacancy.getText().toString();
                if(FIELD.isEmpty())
                {
                    field.setError("Please Enter the Internship Field");
                }
                if(LASTDATE.isEmpty())
                {
                    dateup.setError("Please enter The last Date to apply");
                }
                if(QUALIFICATION.isEmpty())
                {
                    qualification.setError("Please Enter the required qualification");
                }
                if(VACANCY.isEmpty())
                {
                    vacancy.setError("Please Enter the no of vacancies");
                }
                if(!FIELD.isEmpty() && !LASTDATE.isEmpty() && !QUALIFICATION.isEmpty() && !VACANCY.isEmpty())
                {
                    uploadintern(FIELD,LASTDATE,QUALIFICATION,VACANCY,DATE);
                }

            }
        });
    }
    private  void uploadintern(final String FIELD,final String LASTDATE,final String QUALIFICATION,final String VACANCY,final String DATE)
    {
        final String key = DataRef.push().getKey();
        HashMap hashMap = new HashMap();
        hashMap.put("Field",FIELD);
        hashMap.put("LastDate",LASTDATE);
        hashMap.put("Qualification",QUALIFICATION);
        hashMap.put("Vacancy",VACANCY);
        hashMap.put("UploadDate",DATE);
        DataRef.child(key).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                startActivity(new Intent(getApplicationContext(),uploadesinternships.class));
                Toast.makeText(MainActivityInternUpload.this,"Internship Uploaded Successfully",Toast.LENGTH_LONG).show();
            }
        });
    }
}