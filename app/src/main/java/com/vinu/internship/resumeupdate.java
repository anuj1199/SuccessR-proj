package com.vinu.internship;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class resumeupdate extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {
    EditText name,phone,email,address,skills,lang,edu_10,edu_12,edu_col,proj1n,proj2n,proj1d,proj2d,exp;
    Button button;
    private DatabaseReference mDatabaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumeupdate);
        name=(EditText)findViewById(R.id.name);
        phone=(EditText)findViewById(R.id.phone);
        email=(EditText)findViewById(R.id.emailp);
        address=(EditText)findViewById(R.id.addressp);
        skills=(EditText)findViewById(R.id.skills);
        lang=(EditText)findViewById(R.id.lang);
        edu_10=(EditText)findViewById(R.id.edu_10);
        edu_12=(EditText)findViewById(R.id.edu_12);
        edu_col=(EditText)findViewById(R.id.edu_col);
        proj1d=(EditText)findViewById(R.id.pro1d);
        proj1n=(EditText)findViewById(R.id.pro1n);
        proj2n=(EditText)findViewById(R.id.pro2n);
        proj2d=(EditText)findViewById(R.id.pro2d);
        exp=(EditText)findViewById(R.id.exp);
        button=(Button) findViewById(R.id.button);
        mDatabaseReference= FirebaseDatabase.getInstance().getReference();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value=name.getText().toString();
                String value1=phone.getText().toString();
                String value2=email.getText().toString();
                String value3=address.getText().toString();
                String value4=skills.getText().toString();
                String value5=lang.getText().toString();
                String value6=edu_10.getText().toString();
                String value7=edu_12.getText().toString();
                String value8=edu_col.getText().toString();
                String value9=proj1n.getText().toString();
                String value10=proj1d.getText().toString();
                String value11=proj2n.getText().toString();
                String value12=proj2d.getText().toString();
                String value13=exp.getText().toString();
                if( TextUtils.isEmpty(value)){
                    name.setError( "Please input your name" );
                    Intent i = new Intent(resumeupdate.this, resumeupdate.class);
                    startActivity(i);
                }
                else if( TextUtils.isEmpty(value1)){
                    phone.setError( "Please input your Phone number" );
                    Intent i = new Intent(resumeupdate.this, resumeupdate.class);
                    startActivity(i);
                }
                else if( TextUtils.isEmpty(value2)){
                    email.setError( "Please input your Mail" );
                    Intent i = new Intent(resumeupdate.this, resumeupdate.class);
                    startActivity(i);
                }
                else if( TextUtils.isEmpty(value3) ){
                    address.setError( "Please input your Address" );
                    Intent i = new Intent(resumeupdate.this, resumeupdate.class);
                    startActivity(i);
                }
                else if( TextUtils.isEmpty(value4) ){
                    skills.setError( "Please input your Skills" );
                    Intent i = new Intent(resumeupdate.this, resumeupdate.class);
                    startActivity(i);
                }
                else if( TextUtils.isEmpty(value5)){
                    lang.setError( "Please input your Languages known" );
                    Intent i = new Intent(resumeupdate.this, resumeupdate.class);
                    startActivity(i);
                }
                else if( TextUtils.isEmpty(value6) ){
                    edu_10.setError( "Please input your 10th Class CGPA" );
                    Intent i = new Intent(resumeupdate.this, resumeupdate.class);
                    startActivity(i);
                }
                else if( TextUtils.isEmpty(value7) ){
                    edu_12.setError( "Please input your 12th Class Percentage" );
                    Intent i = new Intent(resumeupdate.this, resumeupdate.class);
                    startActivity(i);
                }
                else if( TextUtils.isEmpty(value8)){
                    edu_col.setError( "Please input your College Current CGPA" );
                    Intent i = new Intent(resumeupdate.this, resumeupdate.class);
                    startActivity(i);
                }
                else {
                    Intent i = new Intent(resumeupdate.this, resume_preview.class);
                    i.putExtra("key", value);
                    i.putExtra("key1", value1);
                    i.putExtra("key2", value2);
                    i.putExtra("key3", value3);
                    i.putExtra("key4", value4);
                    i.putExtra("key5", value5);
                    i.putExtra("key6", value6);
                    i.putExtra("key7", value7);
                    i.putExtra("key8", value8);
                    i.putExtra("key9", value9);
                    i.putExtra("key10", value10);
                    i.putExtra("key11", value11);
                    i.putExtra("key12", value12);
                    i.putExtra("key13", value13);
                    startActivity(i);
                    users user= new users(name.getText().toString(), phone.getText().toString(),email.getText().toString(),address.getText().toString(),skills.getText().toString(),lang.getText().toString(),edu_10.getText().toString(),edu_12.getText().toString(),edu_col.getText().toString(),proj1n.getText().toString(),proj1d.getText().toString(),proj2n.getText().toString(),proj2d.getText().toString(),exp.getText().toString());
                    mDatabaseReference.child("users").child(phone.getText().toString()).setValue(user);
                    finish();
                }

            }
        });

    }
}
