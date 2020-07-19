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

public class Internlogin extends AppCompatActivity {
     Button internsignup_btn,login_btn;
     TextInputEditText email,password;
    ProgressBar pgb;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internlogin);
        login_btn = findViewById(R.id.loginbtn_intern);
        internsignup_btn = findViewById(R.id.signup_intern_loginpage);
        email = findViewById(R.id.emailaddresslg_intern);
        password = findViewById(R.id.passwordlg_intern);
        final NotificationHelper notificationHelper = new NotificationHelper(this);
       pgb = findViewById(R.id.pbarlogin_intern);
        internsignup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(Internlogin.this, internsignup.class) ;
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
                else if(!Emailid.matches(emailPattern))
                {
                    email.setError("Please enter a valid Email-Address");
                }
                    ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo wificonn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                    NetworkInfo mobileconn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                if((((wificonn != null) && wificonn.isConnected()) || ((mobileconn != null) && mobileconn.isConnected())))
                {
                    if(!TextUtils.isEmpty(Emailid) && !TextUtils.isEmpty(Password) && Emailid.matches(emailPattern)) {
                        final ACProgressFlower dialoglogin = new ACProgressFlower.Builder(Internlogin.this)
                                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                                .themeColor(Color.WHITE)
                                .text("Logging In")
                                .fadeColor(Color.DKGRAY).build();
                        dialoglogin.show();
                        firebaseAuth.signInWithEmailAndPassword(Emailid, Password)
                                .addOnCompleteListener(Internlogin.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        pgb.setVisibility(View.GONE);
                                        if (task.isSuccessful()) {
                                            if (firebaseAuth.getCurrentUser().isEmailVerified()) {
                                                dialoglogin.dismiss();
                                                Toast.makeText(Internlogin.this, "Logged In successfully", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(Internlogin.this, InternSuccess.class);
                                                startActivity(intent);
                                                finish();

                                            }
                                            else if (!firebaseAuth.getCurrentUser().isEmailVerified()) {
                                                notificationHelper.sendHighPriorityNotification("Verify Email","Hello User,Please verify Your Mail-id First\n Verification link has been sent to:\n"+ Emailid,Internlogin.class);
                                                Toast.makeText(Internlogin.this, "Please Verify your mailid first", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                        else
                                            {
                                            Toast.makeText(Internlogin.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                        }
                                        dialoglogin.dismiss();
                                    }
                                });
                    }
                }
                else
                {
                    Toast.makeText(Internlogin.this,"Please check Your Internet Connection",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
