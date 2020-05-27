package com.example.reservas_gym;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private ArrayList<RecyclerViewClass> myRecycleViewList;
    public static class RecyclerViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView imgV;
        public TextView txtStudentName, txtReservationTime;
        public RecyclerViewHolder(View itemView)
        {
            super(itemView);
            imgV = itemView.findViewById(R.id.RecyclerImg);
            txtStudentName = itemView.findViewById(R.id.txtStudentNameRecycle);
            txtReservationTime = itemView.findViewById(R.id.txtHoraReservaRecycle);
        }
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_layout, parent, false);
        return new RecyclerViewHolder(v);
    }

    public RecyclerViewAdapter(ArrayList<RecyclerViewClass> RecyclerList)
    {
        myRecycleViewList = RecyclerList;
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position)
    {
        RecyclerViewClass currentItem = myRecycleViewList.get(position);
        holder.imgV.setImageResource(currentItem.getImageResource());
        holder.txtStudentName.setText(currentItem.getStudentId());
        holder.txtReservationTime.setText(currentItem.getReservationTime());
    }

    @Override
    public int getItemCount()
    {
        return myRecycleViewList.size();
    }



}
