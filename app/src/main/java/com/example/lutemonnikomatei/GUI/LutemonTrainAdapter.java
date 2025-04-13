package com.example.lutemonnikomatei.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lutemonnikomatei.GUI.LutemonViewHolder;
import com.example.lutemonnikomatei.LutemonClasses.Lutemon;
import com.example.lutemonnikomatei.R;

import java.util.ArrayList;

public class LutemonTrainAdapter extends RecyclerView.Adapter<LutemonViewHolder> {
    private ArrayList<Lutemon> lutemons;
    private OnLutemonClickListener listener;

    // Interface for click events
    public interface OnLutemonClickListener {
        void onLutemonClick(int position);
    }

    public LutemonTrainAdapter(ArrayList<Lutemon> lutemons, OnLutemonClickListener listener) {
        this.lutemons = lutemons;
        this.listener = listener;
    }

    @NonNull
    @Override
    public LutemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lutemon_list_item, parent, false);
        return new LutemonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LutemonViewHolder holder, int position) {
        Lutemon lutemon = lutemons.get(position);
        holder.lutemonName.setText(lutemon.getName());
        holder.lutemonType.setText(lutemon.getType().toString());
        holder.lutemonStats.setText(String.format("HP: %d/%d | Speed: %d | Stamina: %d/%d",
                lutemon.getHp(), lutemon.getMaxHp(),
                lutemon.getSpeed(),
                lutemon.getStamina(), lutemon.getMaxStamina()));

        // Set click listener for the item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    int pos = holder.getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        listener.onLutemonClick(pos);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return lutemons.size();
    }
}