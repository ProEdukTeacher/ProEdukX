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

public class NotasAdapter extends RecyclerView.Adapter<NotasAdapter.NotasAdapterViewHolder> {
    private OnItemClickListener listener;
    private ArrayList<Lasnotasmat> lasnotasmat;
    private Context xcontext;
    private AttendanceRecyclerAdapter adapter;


    public interface OnItemClickListener {
        void OnItemClick(int position);

        void OnDeleteClick(int position);
    }
    //now a method

    public void SetOnItemClickListener(OnItemClickListener clickListener) {

        listener = clickListener;
    }


    public NotasAdapter(Context context, ArrayList<Lasnotasmat> list) {

        this.xcontext = context;
        this.lasnotasmat = list;


    }


    public void addNotasmat(Lasmateriasatt lasnotasmat) {


        notifyDataSetChanged();


    }


    public class NotasAdapterViewHolder extends RecyclerView.ViewHolder {



        TextView subjectsTxV;
        TextView classesTxV;


        public NotasAdapterViewHolder(@NonNull View itemviewmat, OnItemClickListener listener) {


            super(itemviewmat);




            subjectsTxV = itemviewmat.findViewById(R.id.classntv);
            classesTxV = itemviewmat.findViewById(R.id.subjectntv);


            itemviewmat.setOnClickListener(v -> {


            });


        }


    }


    @NonNull
    @Override
    public NotasAdapter.NotasAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroupmat, int i) {


        View viewmat = LayoutInflater.from(viewGroupmat.getContext())
                .inflate(R.layout.notas_cardview, viewGroupmat, false);


        return new NotasAdapterViewHolder(viewmat, listener);

    }

    @Override
    public void onBindViewHolder(@NonNull NotasAdapter.NotasAdapterViewHolder viewHoldernotas, int position) {

        viewHoldernotas.itemView.setTag(lasnotasmat.get(position));
        viewHoldernotas.classesTxV.setText(lasnotasmat.get(position).getThesubject());
        viewHoldernotas.subjectsTxV.setText(lasnotasmat.get(position).getThegrade());


    }


    @Override


    public int getItemCount() {

        return lasnotasmat.size();
    }


}