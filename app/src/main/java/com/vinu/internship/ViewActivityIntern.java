package com.vinu.internship;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewActivityIntern extends AppCompatActivity {
    TextView viewfield,viewlastdate,viewpersonneeded,viewqualification;
    Button delete_btn;
    DatabaseReference ref,DataRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_intern);
        ref = FirebaseDatabase.getInstance().getReference().child("INTERNSHIP");
        viewfield = findViewById(R.id.field_view_activity_intern);
        viewlastdate = findViewById(R.id.lastdate_view_activity_intern);
        viewpersonneeded = findViewById(R.id.personneeded_view_activity_intern);
        viewqualification = findViewById(R.id.Qualification_view_activity_intern);
        delete_btn = findViewById(R.id.delete_view_activity_intern);
        String InternKey = getIntent().getStringExtra("InternKey");
        ref.child(InternKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    String field = dataSnapshot.child("Field").getValue().toString();
                    String lastDate = dataSnapshot.child("LastDate").getValue().toString();
                    String qualification = dataSnapshot.child("Qualification").getValue().toString();
                    String vacancy = dataSnapshot.child("Vacancy").getValue().toString();
                    viewfield.setText(field);
                    viewlastdate.setText(lastDate);
                    viewpersonneeded.setText(vacancy);
                    viewqualification.setText(qualification);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        DataRef = FirebaseDatabase.getInstance().getReference().child("INTERNSHIP").child(InternKey);
        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataRef.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(ViewActivityIntern.this,"Internship deleted successfully",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(),uploadesinternships.class));

                    }
                });

            }
        });

    }
}