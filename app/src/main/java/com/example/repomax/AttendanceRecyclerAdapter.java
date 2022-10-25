package com.example.repomax;

import android.app.ActionBar;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class AttendanceRecyclerAdapter extends RecyclerView.Adapter<AttendanceRecyclerAdapter.LasmateriasattViewHolder> {
    private OnItemClickListener listener;
    private ArrayList<Lasmateriasatt> materiasatt;
    private Context zcontext;
    private AttendanceRecyclerAdapter adapter;


    public interface OnItemClickListener {
        void OnItemClick(int position);

        void OnDeleteClick(int position);
    }
    //now a method

    public void SetOnItemClickListener(OnItemClickListener clickListener) {

        listener = clickListener;
    }


    public AttendanceRecyclerAdapter(Context context, ArrayList<Lasmateriasatt> list) {

        this.zcontext = context;
        this.materiasatt = list;


    }


    public void addMateriasatt(Lasmateriasatt lasmateriasatt) {


        notifyDataSetChanged();


    }


    public class LasmateriasattViewHolder extends RecyclerView.ViewHolder {



        TextView tvsubjects;
        TextView tvcourses;


        public LasmateriasattViewHolder(@NonNull View itemviewatt, OnItemClickListener listener) {


            super(itemviewatt);




            tvsubjects = itemviewatt.findViewById(R.id.tvsubject);
            tvcourses = itemviewatt.findViewById(R.id.Tvcourse);


            itemviewatt.setOnClickListener(v -> {


            });


        }


    }


    @NonNull
    @Override
    public AttendanceRecyclerAdapter.LasmateriasattViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroupatt, int i) {


        View viewatt = LayoutInflater.from(viewGroupatt.getContext())
                .inflate(R.layout.attendance_cardview, viewGroupatt, false);


        return new LasmateriasattViewHolder(viewatt, listener);

    }

    @Override
    public void onBindViewHolder(@NonNull AttendanceRecyclerAdapter.LasmateriasattViewHolder viewHolderatt, int position) {

        viewHolderatt.itemView.setTag(materiasatt.get(position));
        viewHolderatt.tvsubjects.setText(materiasatt.get(position).getClasses());
        viewHolderatt.tvcourses.setText(materiasatt.get(position).getGrades());


    }


    @Override


    public int getItemCount() {

        return materiasatt.size();
    }


}
