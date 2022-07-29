package com.example.repomax;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {


    private ArrayList<Lasmaterias> materias;
    private Context context;
    private PersonAdapter adapter;

    public PersonAdapter(Context context, ArrayList<Lasmaterias> list) {

        this.context = context;
        this.materias = list;


    }

    public void addMaterias(Lasmaterias lasmaterias) {


        notifyDataSetChanged();


    }




    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvGroup;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvGroup = itemView.findViewById(R.id.TvPass);


            itemView.setOnClickListener(v -> {


            });


        }


    }


    @NonNull
    @Override
    public PersonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.class_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonAdapter.ViewHolder viewHolder, int position) {

        viewHolder.itemView.setTag(materias.get(position));
        viewHolder.tvName.setText(materias.get(position).getName());
        viewHolder.tvGroup.setText(materias.get(position).getTelNr());







    }




    @Override


    public int getItemCount() {


        return materias.size();
    }
}


