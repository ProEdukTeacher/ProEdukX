package com.example.repomax;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Collections;

public class RegisterActivity extends AppCompatActivity {


    AutoCompleteTextView tvDay;
    TextView tvDat, tvgrads, tvclis;
    EditText etemail, etpass;
    Button btnreg;
    FirebaseAuth mAuth;
    ArrayAdapter<String> adapterItems;
    boolean[] selectedClis;
    boolean[] selectedDay;
    boolean[] selectedGrad;
    ArrayList<Integer> clisList = new ArrayList<>();
    ArrayList<Integer> gradList = new ArrayList<>();
    ArrayList<Integer> dayList = new ArrayList<>();
    String[] clisArray = {"Español", "Inglés", "Matemáticas", "Ciencias", "Estudios Sociales"};
    String[] dayArray = {"Escuela Elemental", "Escuela Intermedia", "Escuela Superior"};
    String[] gradArray = {"Kindergarten", "(1)Primer Grado", "(2)Segundo Grado", "(3)Tercer Grado", "(4)Cuarto Grado",
            "(5)Quinto Grado", "(6)Sexto Grado"};
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        tvgrads = findViewById(R.id.tvgrad);
        tvclis = findViewById(R.id.tvcli);
        tvDat = findViewById(R.id.muestrame);
        etemail = findViewById(R.id.getEmail);
        etpass = findViewById(R.id.getPass);
        btnreg = findViewById(R.id.btnregist);
        mAuth= FirebaseAuth.getInstance();
        btnreg.setOnClickListener(view -> {
            createUser();

        });

        //Initialize selected day array
        selectedGrad = new boolean[gradArray.length];
        selectedClis = new boolean[clisArray.length];
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
                        } else {

                            dayList.remove(0);
                        }
                    }
                });

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringBuilder stringBuilder = new StringBuilder();

                        for (int j = 0; j < dayList.size(); j++) {

                            stringBuilder.append(dayArray[dayList.get(j)]);

                            if (j != dayList.size() - 1) {

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

                        for (int j = 0; j < selectedDay.length; j++) {

                            selectedDay[j] = false;
                            dayList.clear();
                            tvDat.setText("");
                        }

                    }
                });

                builder.show();
            }
        });

        tvgrads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(


                        RegisterActivity.this
                );

                builder.setTitle("Registrar Grados");
                builder.setCancelable(false);
                builder.setMultiChoiceItems(gradArray, selectedGrad, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                        //check conditions

                        if (isChecked) {

                            gradList.add(which);

                            Collections.sort(gradList);
                        } else {

                            gradList.remove(0);
                        }

                    }
                });
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        StringBuilder stringBuilder = new StringBuilder();
                        for (int g = 0; g < gradList.size(); g++) {
                            stringBuilder.append((gradArray[gradList.get(g)]));
                            if (g != gradList.size() - 1) {
                                stringBuilder.append(", ");
                            }
                        }
                        tvgrads.setText(stringBuilder.toString());

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

                        for (int g = 0; g < selectedGrad.length; g++) {

                            selectedGrad[g] = false;
                            gradList.clear();
                            tvgrads.setText("");
                        }

                    }
                });

                builder.show();

            }
        });

        tvclis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(


                        RegisterActivity.this
                );

                builder.setTitle("Registrar Clases");
                builder.setCancelable(false);
                builder.setMultiChoiceItems(clisArray, selectedClis, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                        //check conditions

                        if (isChecked) {

                            clisList.add(which);

                            Collections.sort(clisList);
                        } else {

                            clisList.remove(0);
                        }

                    }
                });
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        StringBuilder stringBuilder = new StringBuilder();
                        for (int c = 0; c < clisList.size(); c++) {
                            stringBuilder.append((clisArray[clisList.get(c)]));
                            if (c != clisList.size() - 1) {
                                stringBuilder.append(", ");
                            }
                        }
                        tvclis.setText(stringBuilder.toString());

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

                        for (int c = 0; c < selectedClis.length; c++) {

                            selectedClis[c] = false;
                            clisList.clear();
                            tvclis.setText("");
                        }

                    }
                });

                builder.show();

            }
        });


        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

private void createUser(){

        String email = etemail.getText().toString();
        String password = etpass.getText().toString();

        if (TextUtils.isEmpty(email)){

            etemail.setError("Email cannot be empty");
            etemail.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            etpass.setError("Password cannot be empty");
            etpass.requestFocus();
        }else{
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(RegisterActivity.this, "Usuario Registrado", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, HomeActivity.class));
                    }else{
                        Toast.makeText(RegisterActivity.this, "Error en registro" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            });
    }
}
}


