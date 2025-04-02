package com.example.lutemonnikomatei.GUI;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lutemonnikomatei.R;


public  class LutemonViewHolder extends RecyclerView.ViewHolder {

        public TextView lutemonName;
        public TextView lutemonType;
        public TextView lutemonStats;

        public LutemonViewHolder(@NonNull View itemView) {
            super(itemView);

            lutemonName = itemView.findViewById(R.id.lutemonName);
            lutemonType = itemView.findViewById(R.id.lutemonType);
            lutemonStats = itemView.findViewById(R.id.lutemonStats);
        }
    }


