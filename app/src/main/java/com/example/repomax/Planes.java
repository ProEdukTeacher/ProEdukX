package com.example.repomax;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class Planes extends Fragment {

    private static final String SP_KEY= "user_list_sp";

    private static final String TAG = "Planes fragment";

    SharedPreferences sharedPreferences;
    Context mContext;
    ArrayList<Lasmaterias> materias;
    RecyclerView recyclerView;
    RecyclerView.Adapter myAdapter;
    RecyclerView.LayoutManager layoutManager;
    View view;
    Button fab;


    public Planes() {


        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        return inflater.inflate(R.layout.fragment_planes, container, false);
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        materias = new ArrayList<>();
        recyclerView = view.findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);

        myAdapter = new PersonAdapter(getContext(), ApplicationClass.materias);


        recyclerView.setAdapter(myAdapter);


        fab = view.findViewById(R.id.fab);


        fab.setOnClickListener(v -> {
            Log.d(TAG, "onClick: Abriendo Ventanilla");


            Binary dialog = new Binary((PersonAdapter) myAdapter,requireContext());
            dialog.setTargetFragment(Planes.this, 1);
            dialog.show(getParentFragmentManager(), "Binary");

        });

        loadData();


    }


    private void loadData() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String json_string=sharedPreferences.getString(SP_KEY, null);
        Gson gson = new Gson();
        TypeToken typeToken= new TypeToken<ArrayList<Lasmaterias>>() {};
        ArrayList<Lasmaterias> materias_local = gson.fromJson(json_string, typeToken.getType());

        if (materias == null) {
            materias = new ArrayList<>();
        }

    }


}

