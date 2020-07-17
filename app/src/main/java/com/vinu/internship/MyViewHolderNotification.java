package com.vinu.internship;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class MyViewHolderNotification extends RecyclerView.ViewHolder {
    TextView textheadind,textdescription,datenotify;


    public MyViewHolderNotification(@NonNull View itemView) {
        super(itemView);
        textheadind = itemView.findViewById(R.id.title_single_view_notification);
        textdescription = itemView.findViewById(R.id.description_single_view_notification);
        datenotify = itemView.findViewById(R.id.date_single_view_notification);
    }

}
