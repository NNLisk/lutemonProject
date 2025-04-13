package com.example.lutemonnikomatei.GUI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lutemonnikomatei.LutemonClasses.Lutemon;
import com.example.lutemonnikomatei.R;

import java.util.List;








public class LutemonAdapter extends RecyclerView.Adapter<LutemonViewHolder> {

        private List<Lutemon> lutemonList;
        private OnLutemonClickListener listener;

        // Interface for click events
        public interface OnLutemonClickListener {
            void onLutemonClick(Lutemon lutemon);
        }

        // Constructor
        public LutemonAdapter(List<Lutemon> lutemonList, OnLutemonClickListener listener) {
            this.lutemonList = lutemonList;
            this.listener = listener;
        }

        @NonNull
        @Override
        public LutemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            // Inflate the item layout
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_lutemon, parent, false);
            return new LutemonViewHolder(view);
        }
        @Override
        public void onBindViewHolder(@NonNull LutemonViewHolder holder, int position) {
            Lutemon lutemon = lutemonList.get(position);

            holder.lutemonName.setText(lutemon.getName());
            holder.lutemonType.setText("Type: " + lutemon.getType());
            holder.lutemonStats.setText("Stamina " + lutemon.getStamina()  + " | HP: " + lutemon.getMaxHp());




            if (listener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onLutemonClick(lutemon);
                    }
                });
            }
        }


        @Override
        public int getItemCount() {
            return lutemonList.size();
        }

        // Update data method
        public void updateData(List<Lutemon> newLutemons) {
            this.lutemonList = newLutemons;
            notifyDataSetChanged();
        }
}
