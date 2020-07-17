package com.vinu.internship;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class uploadnotification extends AppCompatActivity {
    TextInputEditText title,description,date;
    Button uploadnotificationbtn;
    DatabaseReference DataRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadnotification);
        title = findViewById(R.id.titlemainnotification);
        description = findViewById(R.id.descriptionmainnotification);
        uploadnotificationbtn = findViewById(R.id.uploadnotification);
        DataRef = FirebaseDatabase.getInstance().getReference().child("NOTIFICATION");
        uploadnotificationbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String TITLE = title.getText().toString();
                final String DESCRIPTION = description.getText().toString();
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE, dd-MMM-yyyy,hh:mm:ss a");
                final String DATE = simpleDateFormat.format(calendar.getTime());
                if(TITLE.isEmpty())
                {
                    title.setError("Please Enter The Title of Notification");
                }
                if(DESCRIPTION.isEmpty())
                {
                    description.setError("Please enter the description of notification");
                }
                if(!TITLE.isEmpty()&& !DESCRIPTION.isEmpty() && !DATE.isEmpty())
                {
                    uploadnotificationdata(TITLE,DESCRIPTION,DATE);
                }

            }
        });
    }
    private  void uploadnotificationdata(final String TITLE,final String DESCRIPTION,final String DATE)
    {
        final String key = DataRef.push().getKey();
        HashMap hashMap = new HashMap();
        hashMap.put("Title",TITLE);
        hashMap.put("Description",DESCRIPTION);
        hashMap.put("Date",DATE);
        DataRef.child(key).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                startActivity(new Intent(getApplicationContext(),employeer_success.class));
                Toast.makeText(uploadnotification.this,"Notification Uploaded Successfully",Toast.LENGTH_LONG).show();
            }
        });
    }
}