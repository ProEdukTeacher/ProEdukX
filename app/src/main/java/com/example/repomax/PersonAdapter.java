package com.example.repomax;

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

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.LasmateriasViewHolder> {
    private OnItemClickListener listener;
    private ArrayList<Lasmaterias> materias;
    private Context context;
    private PersonAdapter adapter;
    private View view;

    public interface OnItemClickListener {
        void OnItemClick(int position);

        void OnDeleteClick(int position);
    }
    //now a method

    public void SetOnItemClickListener(OnItemClickListener clickListener) {

        listener = clickListener;
    }


    public PersonAdapter(Context context, ArrayList<Lasmaterias> list) {

        this.context = context;
        this.materias = list;


    }


    public void addMaterias(Lasmaterias lasmaterias) {


        notifyDataSetChanged();



    }


    public class LasmateriasViewHolder extends RecyclerView.ViewHolder {


        TextView tvName;
        TextView tvGroup;
        Button thebtn;

        public LasmateriasViewHolder(@NonNull View itemView, OnItemClickListener listener) {


            super(itemView);



            thebtn = itemView.findViewById(R.id.image_delete);
            thebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnItemClick(getBindingAdapterPosition());

                }
            });
            tvName = itemView.findViewById(R.id.tvName);
            tvGroup = itemView.findViewById(R.id.TvPass);


            itemView.setOnClickListener(v -> {


            });


        }


    }


    @NonNull
    @Override
    public PersonAdapter.LasmateriasViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.class_item, viewGroup, false);


        return new LasmateriasViewHolder(view, listener);

    }

    @Override
    public void onBindViewHolder(@NonNull PersonAdapter.LasmateriasViewHolder viewHolder, int position) {

        viewHolder.itemView.setTag(materias.get(position));
        viewHolder.tvName.setText(materias.get(position).getName());
        viewHolder.tvGroup.setText(materias.get(position).getTelNr());



    }


    @Override


    public int getItemCount() {

        return materias.size();
    }


   
}