package com.example.repomax;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;

public class RegisterActivity extends AppCompatActivity {


    AutoCompleteTextView tvDay;
    TextView tvDat;
    ArrayAdapter<String> adapterItems;
    boolean [] selectedDay;
    ArrayList<Integer> dayList = new ArrayList<>();
    String[] dayArray = {"Escuela Elemental", "Escuela Intermedia", "Escuela Superior"};
    RecyclerView recyclerView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        tvDat = findViewById(R.id.muestrame);

        //Initialize selected day array
        recyclerView= findViewById(R.id.registerRV);
        selectedDay = new boolean[dayArray.length];
        tvDat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Initialize alert dialog

                AlertDialog.Builder builder = new AlertDialog.Builder(

                        RegisterActivity.this

                );
                //Set title
                builder.setTitle("Seleccione Nivel");
                //Set dialog non cancellable
                builder.setCancelable(false);

                builder.setMultiChoiceItems(dayArray, selectedDay, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i, boolean b) {

                        //check conditions

                        if (b) {

                            dayList.add(i);

                            Collections.sort(dayList);
                        }else {

                            dayList.remove(i);
                        }
                    }
                });

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringBuilder stringBuilder = new StringBuilder();

                        for (int j=0; j<dayList.size(); j++) {

                            stringBuilder.append(dayArray[dayList.get(j)]);

                            if (j !=dayList.size()-1) {

                                stringBuilder.append(", ");
                            }
                        }

                        tvDat.setText(stringBuilder.toString());
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.dismiss();

                    }
                });

                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        for(int j= 0; j<selectedDay.length;j++){

                            selectedDay[j] = false;
                            dayList.clear();
                            tvDat.setText("");
                        }

                    }
                });

                builder.show();
            }
        });




        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}


