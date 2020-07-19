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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;

public class employeerlogin extends AppCompatActivity {
    Button employersignup_btn,login_btn;
    TextInputEditText email,password;
    ProgressBar pgb;
    FirebaseAuth firebaseAuth,mAuth;
    //a constant for detecting the login intent result
    private static final int RC_SIGN_IN = 234;

    //Tag for the logs optional
    private static final String TAG = "simplifiedcoding";

    //creating a GoogleSignInClient object
    GoogleSignInClient mGoogleSignInClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employeerlogin);
        mAuth = FirebaseAuth.getInstance();

        //Then we need a GoogleSignInOptions object
        //And we need to build it as below
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        //Then we will get the GoogleSignInClient object from GoogleSignIn class
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        //Now we will attach a click listener to the sign_in_button
        //and inside onClick() method we are calling the signIn() method that will open
        //google sign in intent
        findViewById(R.id.sign_in_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //if the requestCode is the Google Sign In code that we defined at starting
        if (requestCode == RC_SIGN_IN) {

            //Getting the GoogleSignIn Task
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                //Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);

                //authenticating with firebase
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                Toast.makeText(employeerlogin.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        //getting the auth credential
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);

        //Now using firebase we are signing in the user here
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            Toast.makeText(employeerlogin.this, "User Signed In", Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(employeerlogin.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }
    //this method is called on click
    private void signIn() {
        //getting the google signin intent
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();

        //starting the activity for result
        startActivityForResult(signInIntent, RC_SIGN_IN);
        Intent intent =new Intent(employeerlogin.this,employeer_success.class);
        startActivity(intent);
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