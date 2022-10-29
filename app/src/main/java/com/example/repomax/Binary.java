package com.example.repomax;

import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
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

           /* Todo: Save the data here to firebase */

            Toast toast = Toast.makeText(getContext(),"Clase Registrada", Toast.LENGTH_SHORT);
            toast.show();


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
    TextView Title, tvgrads, tvclis, classtup, Sectiones;
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
        ArrayAdapter<String> adapterItems;
        boolean[] selectedClis;
        boolean[] selectedDay;
        boolean[] selectedGrad;
        ArrayList<Integer> clisList = new ArrayList<>();
        ArrayList<Integer> gradList = new ArrayList<>();
        ArrayList<Integer> dayList = new ArrayList<>();
        String[] clisArray = {"Español", "Inglés", "Matemáticas", "Ciencias", "Estudios Sociales", "Salud"};
        String[] dayArray = {"Escuela Elemental", "Escuela Intermedia", "Escuela Superior"};
        String[] gradArray = {"Kindergarten", "(1)Primer Grado", "(2)Segundo Grado", "(3)Tercer Grado", "(4)Cuarto Grado",
                "(5)Quinto Grado", "(6)Sexto Grado", "Séptimo Grado", "Noveno Grado", "Décimo Grado", "Undécimo Grado", "Duodécimo Grado"};


        selectedGrad = new boolean[gradArray.length];
        selectedClis = new boolean[clisArray.length];
        selectedDay = new boolean[dayArray.length];






        Sectiones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Initialize alert dialog

                androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(

                        requireActivity()
                );
                //Set Custom View for the Dialog



                //Set title
                builder.setTitle("Seleccione grupo");
                //Set dialog non cancellable
                builder.setCancelable(false);

                builder.setSingleChoiceItems(gradArray, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if (!gradList.isEmpty()) {
                            gradList.clear();
                        }else {
                            gradList.add(i);
                        }


                    }
                });
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringBuilder stringBuilder = new StringBuilder();

                        for (int j = 0; j < gradList.size(); j++) {

                            stringBuilder.append(gradArray[gradList.get(j)]);

                            if (j != gradList.size() - 1) {

                                stringBuilder.append(", ");
                            }
                        }

                        Sectiones.setText(stringBuilder.toString());
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.dismiss();

                    }
                });



                builder.show();
            }
        });









        classtup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Initialize alert dialog

                androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(

                        requireActivity()

                );
                //Set title
                builder.setTitle("Seleccione Clase");
                //Set dialog non cancellable
                builder.setCancelable(false);

                builder.setSingleChoiceItems(clisArray, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if (!clisList.isEmpty()) {
                            clisList.clear();
                        }else {
                            clisList.add(i);
                        }

                    }
                });
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringBuilder stringBuilder = new StringBuilder();

                        for (int j = 0; j < clisList.size(); j++) {

                            stringBuilder.append(clisArray[clisList.get(j)]);

                            if (j != clisList.size() - 1) {

                                stringBuilder.append(", ");
                            }
                        }

                        classtup.setText(stringBuilder.toString());
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.dismiss();

                    }
                });



            builder.show();
        }



    });


                return view;
    }

}





