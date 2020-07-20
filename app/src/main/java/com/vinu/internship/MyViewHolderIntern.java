package com.vinu.internship;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

public class MyViewHolderIntern extends ViewHolder {
    TextView Field,lastdate,perreq,qualific,uplotime;
    View v;
    public MyViewHolderIntern(@NonNull View itemView) {
        super(itemView);
        Field = itemView.findViewById(R.id.field_single_view_intern);
        lastdate = itemView.findViewById(R.id.last_date_single_view_intern);
        perreq = itemView.findViewById(R.id.vacancies_single_view_intern);
        qualific = itemView.findViewById(R.id.qualification_single_view_intern);
        uplotime = itemView.findViewById(R.id.uploadtime_single_view_intern);
        v = itemView;
    }
}
