package com.vinu.internship;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class MyViewHolderJob extends RecyclerView.ViewHolder {
    TextView textfield,textqualification,textlastdate,textperson;
    View v;
    public MyViewHolderJob(@NonNull View itemView) {
        super(itemView);
        textfield = itemView.findViewById(R.id.field_single_view_job);
        textlastdate = itemView.findViewById(R.id.last_date_single_view_job);
        textperson = itemView.findViewById(R.id.vacancies_single_view_job);
        textqualification = itemView.findViewById(R.id.qualification_single_view_job);
        v = itemView;
    }
}