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

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class uploadesinternships extends AppCompatActivity {
    EditText inputinternsearch;
    RecyclerView recyclerView;
    FirebaseRecyclerOptions<Intern> options;
    FirebaseRecyclerAdapter<Intern,MyViewHolderIntern> adapter;
    DatabaseReference DataRef,ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadesinternships);
        DataRef = FirebaseDatabase.getInstance().getReference().child("INTERNSHIP");
        inputinternsearch  = findViewById(R.id.inputSearchIntern);
        recyclerView = findViewById(R.id.recyclerViewIntern);
        ref = FirebaseDatabase.getInstance().getReference().child("INTERNSHIP");
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);
        LoadData("");
        inputinternsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable!= null)
                {
                    LoadData(editable.toString());
                }
                else
                {
                    LoadData("");
                }

            }
        });
    }

    private void LoadData(String data) {
        Query query =  DataRef.orderByChild("Field").startAt(data).endAt(data+"\uf8ff");

        options = new FirebaseRecyclerOptions.Builder<Intern>().setQuery(DataRef,Intern.class).build();
        adapter = new FirebaseRecyclerAdapter<Intern, MyViewHolderIntern>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolderIntern myViewHolderIntern, final int i, @NonNull Intern intern) {
                myViewHolderIntern.Field.setText("Field:"+intern.getField());
                myViewHolderIntern.lastdate.setText("Last Date:"+intern.getLastDate());
                myViewHolderIntern.perreq.setText("Vacancy:"+intern.getVacancy());
                myViewHolderIntern.qualific.setText("Qualification:"+intern.getQualification());
                myViewHolderIntern.uplotime.setText(intern.getUploadDate());
                myViewHolderIntern.v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent a = new Intent(uploadesinternships.this,ViewActivityIntern.class);
                        a.putExtra("InternKey",getRef(i).getKey());
                        startActivity(a);
                    }
                });
            }

            @NonNull
            @Override
            public MyViewHolderIntern onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v  = LayoutInflater.from(parent.getContext()).inflate(R.layout.singleviewintern,parent,false);
                return new MyViewHolderIntern(v);
            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }
}