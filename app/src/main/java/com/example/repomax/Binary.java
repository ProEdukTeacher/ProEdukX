package com.example.repomax;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Objects;


public class Binary extends AppCompatDialogFragment {

    private Context mContext;
    private PersonAdapter adapter;



    private static final String TAG = "Binary Dialog";

    public Binary(PersonAdapter adapter, Context context) {
        this.adapter = adapter;
        this.mContext = context;




    }



    private void onClick(View v) {
        if (classtup.getText().toString().isEmpty() || Sectiones.getText().toString().isEmpty()) {

            Toast.makeText(getContext(), "Llene todos los campos", Toast.LENGTH_LONG).show();


        } else {

            ApplicationClass.materias.add(new Lasmaterias(classtup.getText().toString().trim(),
                    Sectiones.getText().toString().trim()));

            adapter.addMaterias(new Lasmaterias(classtup.getText().toString().trim(),
                    Sectiones.getText().toString().trim()));
            Gson gson = new Gson();

            String jsonString = gson.toJson(materias);
            SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString("SP_KEY", jsonString);
            editor.apply();


            Toast.makeText(getContext(), "Clase Registrada", Toast.LENGTH_SHORT).show();

            classtup.setText(null);
            Sectiones.setText(null);




            dismiss();


        } 



    }







    public interface OnInputSelected {
        void SendInput(String input);


    }


    public OnInputSelected monInputSelected;
    //Widgets

    private

    SharedPreferences sharedPreferences;
    ArrayList<ApplicationClass> materias;
    RecyclerView.Adapter myAdapter;
    TextView Title;
    EditText classtup, Sectiones;
    Button addud;
    Planes planes;
    FragmentManager fragmentManager;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_binary, container, false);

        myAdapter = new PersonAdapter(getContext(), ApplicationClass.materias);
        classtup = view.findViewById(R.id.classtup);
        Sectiones = view.findViewById(R.id.Sectiones);
        fragmentManager = getParentFragmentManager();
        planes = (Planes) fragmentManager.findFragmentById(R.id.Planes);
        addud = view.findViewById(R.id.addud);
        addud.setOnClickListener(this::onClick);





        return view;
    }

}





