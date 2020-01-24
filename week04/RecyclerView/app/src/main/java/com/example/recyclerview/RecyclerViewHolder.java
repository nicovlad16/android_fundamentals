package com.example.recyclerview;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    public final TextView textViewName;
    public final TextView textViewSurname;
    public final Button button;


    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewName = itemView.findViewById(R.id.textview_name);
        textViewSurname = itemView.findViewById(R.id.text_view_surname);
        button = itemView.findViewById(R.id.button);
    }
}
