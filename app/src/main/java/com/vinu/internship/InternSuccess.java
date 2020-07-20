package com.vinu.internship;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InternSuccess extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    static final float END_SCALE = 07f;
    RecyclerView notificationrecycler;
    DrawerLayout drawrerlayout;
    NavigationView navigationView;
    ImageView menuIcon;
    ConstraintLayout contentView;
    FirebaseUser user;
    FirebaseAuth firebaseAuth;
    DatabaseReference Dataref;
    FirebaseRecyclerOptions<Notification> options;
    FirebaseRecyclerAdapter<Notification,MyViewHolderNotification> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_intern_success);
        notificationrecycler = findViewById(R.id.notification_recycler_intern);
        notificationrecycler.setHasFixedSize(true);
        Dataref = FirebaseDatabase.getInstance().getReference().child("NOTIFICATION");
        notificationrecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        drawrerlayout = findViewById(R.id.drawer_layout_intern);
        menuIcon = findViewById(R.id.menu_icon);
        contentView = findViewById(R.id.constraintlayout);
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        View headerView = navigationView.getHeaderView(0);
        options = new FirebaseRecyclerOptions.Builder<Notification>().setQuery(Dataref,Notification.class).build();
        adapter = new FirebaseRecyclerAdapter<Notification, MyViewHolderNotification>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolderNotification myViewHolder, int i, @NonNull Notification event) {
                myViewHolder.textheadind.setText(event.getTitle());
                myViewHolder.textdescription.setText(event.getDescription());
                myViewHolder.datenotify.setText(event.getDate());
            }

            @NonNull
            @Override
            public MyViewHolderNotification onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_view_notification,parent,false);
                return new MyViewHolderNotification(v);
            }
        };
        adapter.startListening();
        notificationrecycler.setAdapter(adapter);
        navigationdrawrer();
    }
    private void navigationdrawrer()
    {
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_jobs_upload);
        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(drawrerlayout.isDrawerVisible(GravityCompat.START))
                {
                    drawrerlayout.closeDrawer(GravityCompat.START);
                }
                else
                {
                    drawrerlayout.openDrawer(GravityCompat.START);
                }
            }
        });
        animateNavigationDrawrer();
    }

    private void animateNavigationDrawrer()
    {
        drawrerlayout.setScrimColor(getResources().getColor(R.color.header_background));
        drawrerlayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener(){
                                            @Override
                                            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

                                                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                                                final float offsetScale = 1 - diffScaledOffset;
                                                contentView.setScaleX(offsetScale);
                                                contentView.setScaleY(offsetScale);
                                                final float xOffset = drawerView.getWidth() * slideOffset;
                                                final float xoffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                                                final float xTranslation = xOffset - xoffsetDiff;
                                                contentView.setTranslationX(xTranslation);
                                            }
                                        }
        );
    }
    @Override
    public void onBackPressed() {
        if(drawrerlayout.isDrawerVisible(GravityCompat.START))
        {
            drawrerlayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId())
        { case R.id.nav_all_logout:

            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(),employeerlogin.class));
            finish();
            Toast.makeText(InternSuccess.this,"Logged out Successfully",Toast.LENGTH_LONG).show();
            finish();
            break;

            case R.id.action_info:
                new AlertDialog.Builder(this)
                        .setTitle("VIT Bhopal")
                        .setCancelable(true)
                        .setMessage(
                                "Developer: Vineet Khola (VK)" +
                                        "\n" +
                                        "Contact No: +91-9468105937"+
                                        "\n" +
                                        "GitHub,LinkedIn,Facebook,Twitter")
                        .setPositiveButton("Close", null)
                        .setIcon(R.drawable.ic_info)

                        .show();
                break;
            case R.id.nav_notifications_intern:
                Intent not = new Intent(InternSuccess.this,uploadnotification.class);
                startActivity(not);
                break;
            case R.id.nav_jobs_intern:
                Intent job = new Intent(InternSuccess.this,HomeActivityJob.class);
                startActivity(job);
                break;
            case R.id.nav_internships_intern:
                Intent internships = new Intent(InternSuccess.this,uploadesinternships.class);
                startActivity(internships);
                break;
            case R.id.nav_resume:
                Intent resume= new Intent(InternSuccess.this, resume.class);
                startActivity(resume);
                break;
        }
        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.nav_all_logout)
        {


            finish();
        }
        if(item.getItemId() == R.id.nav_all_logout)
        {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(),Internlogin.class));
            finish();
        }
        if(item.getItemId() == R.id.nav_share)
        {
            Intent shareintent = new Intent();
            shareintent.setAction(Intent.ACTION_SEND);
            shareintent.putExtra(Intent.EXTRA_TEXT,"fhjfnvjknvkjbnkgtjvnckjvhjgnf");
            shareintent.setType("text/plain");
            startActivity(Intent.createChooser(shareintent,"Share via...."));
            finish();
        }
        return true;
    }
}