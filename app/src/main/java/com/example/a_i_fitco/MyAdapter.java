package com.example.a_i_fitco;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    private Context context;
    private ArrayList date,calories,duration,workout;

    public MyAdapter(Context context, ArrayList date, ArrayList calories, ArrayList duration, ArrayList workout) {
        this.context = context;
        this.date = date;
        this.calories = calories;
        this.duration = duration;
        this.workout = workout;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.progressentry,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.date.setText(String.valueOf(date.get(position)));
        holder.calories.setText(String.valueOf(calories.get(position)));
        holder.duration.setText(String.valueOf(duration.get(position)));
        holder.workout.setText(String.valueOf(workout.get(position)));

    }

    @Override
    public int getItemCount() {
        return date.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView date,calories,duration,workout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.textdate);
            calories = itemView.findViewById(R.id.textcalories);
            duration = itemView.findViewById(R.id.textduration);
            workout = itemView.findViewById(R.id.textworkout);
        }
    }
}
