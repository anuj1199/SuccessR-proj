package com.vinu.internship;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;

public class employeerlogin extends AppCompatActivity {
    Button employersignup_btn,login_btn;
    TextInputEditText email,password;
    ProgressBar pgb;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employeerlogin);
        login_btn = findViewById(R.id.loginbtn_employeer);
        employersignup_btn = findViewById(R.id.signup_employeer);
        email = findViewById(R.id.emailaddresslg_employeer);
        password = findViewById(R.id.passwordlg_employeer);
        final NotificationHelper notificationHelper = new NotificationHelper(this);
        pgb = findViewById(R.id.pbarlogin_employeer);
        employersignup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(employeerlogin.this, employeersignup.class) ;
                startActivity(a);
            }
        });
        firebaseAuth =FirebaseAuth.getInstance();
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                final String Emailid = email.getText().toString().trim();
                String Password = password.getText().toString().trim();

                if(TextUtils.isEmpty(Emailid))
                {
                    email.setError("Email-Address can't be empty");
                }
                else if(TextUtils.isEmpty(Password))
                {
                    password.setError("Password can't be empty");
                }

                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo wificonn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                NetworkInfo mobileconn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                if((((wificonn != null) && wificonn.isConnected()) || ((mobileconn != null) && mobileconn.isConnected())))
                {
                    if(!TextUtils.isEmpty(Emailid) && !TextUtils.isEmpty(Password)) {
                        final ACProgressFlower dialoglogin = new ACProgressFlower.Builder(employeerlogin.this)
                                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                                .themeColor(Color.WHITE)
                                .text("Logging In")
                                .fadeColor(Color.DKGRAY).build();
                        dialoglogin.show();
                        firebaseAuth.signInWithEmailAndPassword(Emailid, Password)
                                .addOnCompleteListener(employeerlogin.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        pgb.setVisibility(View.GONE);
                                        if (task.isSuccessful()) {
                                            if (firebaseAuth.getCurrentUser().isEmailVerified()) {
                                                dialoglogin.dismiss();
                                                Toast.makeText(employeerlogin.this, "Logged In successfully", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(employeerlogin.this, employeer_success.class);
                                                startActivity(intent);
                                                finish();

                                            }
                                            else if (!firebaseAuth.getCurrentUser().isEmailVerified()) {
                                                notificationHelper.sendHighPriorityNotification("Verify Email","Hello Employeer,Please verify Your Mail-id First\n Verification link has been sent to:\n"+ Emailid,employeerlogin.class);
                                                Toast.makeText(employeerlogin.this, "Please Verify your mailid first", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                        else
                                        {
                                            Toast.makeText(employeerlogin.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                        }
                                        dialoglogin.dismiss();
                                    }
                                });
                    }
                }
                else
                {
                    Toast.makeText(employeerlogin.this,"Please check Your Internet Connection",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}