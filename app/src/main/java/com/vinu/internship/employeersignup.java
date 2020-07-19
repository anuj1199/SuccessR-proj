package com.vinu.internship;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;

public class employeersignup extends AppCompatActivity {
    Button login,Signup;
    TextInputLayout firstname,lastname,emaddress,phoneno,password,confirmpswd;
    private FirebaseAuth firebaseAuth;
    DatabaseReference reference;
    FirebaseDatabase rootnode;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employeersignup);
        login = findViewById(R.id.loginsignup_employee);
        Signup = findViewById(R.id.signupbutton_employer_signup);
        firstname = findViewById(R.id.firstname_employeer);
        lastname = findViewById(R.id.lastname_employeer);
        emaddress = findViewById(R.id.emailaddress_employeer);
        phoneno = findViewById(R.id.phonenosg_employeer);
        password = findViewById(R.id.passwordsignup_employeer);
        confirmpswd = findViewById(R.id.cnfrmpassword_employeer);
        progressBar = findViewById(R.id.signupprogress_employeer);
        firebaseAuth = FirebaseAuth.getInstance();
        final NotificationHelper notificationHelper = new NotificationHelper(this);
        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootnode = FirebaseDatabase.getInstance();
                reference = rootnode.getReference("EMPLOYEERS");
                String passwordVal = //"^" +
                        //"(?=.*[0-9])" +         //at least 1 digit
                        //"(?=.*[a-z])" +         //at least 1 lower case letter
                        //"(?=.*[A-Z])" +         //at least 1 upper case letter
                        //"(?=.*[a-zA-Z])" +      //any letter
                        //"(?=.*[@#$%^&+=])" +    //at least 1 special character
                        //"(?=\\S+$)" +           //no white spaces
                        // "$" +
                        ".{6,}" ;
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                final String Firstname = firstname.getEditText().getText().toString().trim();
                final String Lastname = lastname.getEditText().getText().toString().trim();
                final String Phoneno = phoneno.getEditText().getText().toString().trim();
                final String Emailid = emaddress.getEditText().getText().toString().trim();
                final String Password = password.getEditText().getText().toString().trim();
                final String confirmpassword = confirmpswd.getEditText().getText().toString().trim();
                if (Firstname.isEmpty()) {
                    firstname.setError("FirstName cannot be empty");
                }
                else if (Lastname.isEmpty()) {
                    lastname.setError("LastName cannot be empty");
                }
                else if (Emailid.isEmpty()) {
                    emaddress.setError("Email Address cannot be empty");
                } else if (!Emailid.matches(emailPattern)) {
                    emaddress.setError("Please use valid email address only");
                }
                else if (Phoneno.isEmpty()) {
                    phoneno.setError("Phone No cannot be empty");
                }
                else if (Password.isEmpty()) {
                    password.setError("Password cannot be empty");

                } else if (!Password.matches(passwordVal)) {
                    confirmpswd.setError("Password is too weak!!It should contain atleast 4 characters,1 digit , 1 lowercase letter,1 uppercase letter,1 special character and should not contain anyspace");
                }
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo wificonn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                NetworkInfo mobileconn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                if((((wificonn != null) && wificonn.isConnected()) || ((mobileconn != null) && mobileconn.isConnected())))
                {
                    if(!Firstname.isEmpty() && !Lastname.isEmpty() && !Phoneno.isEmpty() && !Emailid.isEmpty() && !Password.isEmpty() && !confirmpassword.isEmpty())
                    {
                        final ACProgressFlower dialog = new ACProgressFlower.Builder(employeersignup.this)
                                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                                .themeColor(Color.WHITE)
                                .text("Creating Account")
                                .fadeColor(Color.DKGRAY).build();
                        dialog.show();
                        firebaseAuth.createUserWithEmailAndPassword(Emailid, Password)
                                .addOnCompleteListener(employeersignup.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {

                                        if (task.isSuccessful()) {
                                            dialog.dismiss();
                                            FirebaseUser fuser = firebaseAuth.getCurrentUser();
                                            fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Toast.makeText(employeersignup.this,"Verification mail Has been sent to:"+Emailid,Toast.LENGTH_SHORT).show();

                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Log.d("TAG","onFailure:Email not sent " +e.getMessage());

                                                }
                                            });
                                            Employeer information = new Employeer(
                                                    Firstname,
                                                    Lastname,
                                                    Emailid,
                                                    Phoneno,
                                                    Password
                                            );
                                            FirebaseDatabase.getInstance().getReference("EMPLOYEER")
                                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                    .setValue(information).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    notificationHelper.sendHighPriorityNotification("Sign Up successful","Hello User,Welcome To successr Please Sign In to Continue",employeersignup.class);
                                                    Toast.makeText(employeersignup.this,"Registration Successful",Toast.LENGTH_LONG).show();
                                                }
                                            });
                                            startActivity(new Intent(getApplicationContext(),employeerlogin.class));
                                            Toast.makeText(employeersignup.this, "Registration Successful", Toast.LENGTH_LONG).show();
                                        }
                                        else
                                        {    dialog.dismiss();
                                            if(task.getException() instanceof FirebaseAuthUserCollisionException)
                                            {
                                                Toast.makeText(employeersignup.this, "You are already registered! Please SignIn", Toast.LENGTH_LONG).show();
                                            }
                                            else
                                            {
                                                Toast.makeText(employeersignup.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }
                                });
                    }
                }
                else
                {
                    Toast.makeText(employeersignup.this,"Please check Your Internet Connection",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}