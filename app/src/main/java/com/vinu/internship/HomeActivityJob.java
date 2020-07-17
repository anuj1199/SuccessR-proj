package com.vinu.internship;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class HomeActivityJob extends AppCompatActivity {
    EditText inputsearchhm;
    RecyclerView recyclerViewhm;
    DatabaseReference Dataref;
    FirebaseRecyclerOptions<JOB> options;
    FirebaseRecyclerAdapter<JOB, MyViewHolderJob> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_job);
        inputsearchhm = findViewById(R.id.inputSearchjob);
        recyclerViewhm = findViewById(R.id.recyclerViewjob);
        recyclerViewhm.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerViewhm.setHasFixedSize(true);
        Dataref = FirebaseDatabase.getInstance().getReference().child("JOBS");
        LoadData("");
        inputsearchhm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString()!=null)
                {
                    LoadData(s.toString());
                }
                else
                {
                    LoadData("");
                }

            }
        });

    }

    private void LoadData(String data) {
        Query query =Dataref.orderByChild("NAME").startAt(data).endAt(data+"\uf8ff");
        options = new FirebaseRecyclerOptions.Builder<JOB>().setQuery(query,JOB.class).build();
        adapter = new FirebaseRecyclerAdapter<JOB, MyViewHolderJob>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolderJob myViewHolder, final int position, @NonNull JOB job) {
                myViewHolder.textfield.setText(job.getField());
                myViewHolder.textqualification.setText(job.getQUALIFICATION());
                myViewHolder.textlastdate.setText(job.getLASTDATE());
                myViewHolder.textperson.setText("Per. Req: "+ job.getPERSON());

            }

            @NonNull
            @Override
            public MyViewHolderJob onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_view_job,parent,false);
                return new MyViewHolderJob(v);
            }
        };
        adapter.startListening();
        recyclerViewhm.setAdapter(adapter);
    }
}