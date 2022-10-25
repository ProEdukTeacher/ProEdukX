package com.example.repomax;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import android.view.WindowManager;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.ArrayList;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {


    TextView tvDat;
    EditText etemail, etpass, etpassc, uname, ulastname;
    Button btnreg;
    FirebaseAuth mAuth;
    ArrayList<Integer> dayList = new ArrayList<>();
    DAOTeacher daoTeacher;

    String[] dayArray = {"Escuela Elemental", "Escuela Intermedia", "Escuela Superior"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        uname = findViewById(R.id.username);
        ulastname = findViewById(R.id.lastnames);
        tvDat = findViewById(R.id.muestrame);
        etemail = findViewById(R.id.getEmail);
        etpass = findViewById(R.id.getPass);
        etpassc = findViewById(R.id.getPassc);
        btnreg = findViewById(R.id.btnregist);
        mAuth = SessionManager.getInstance().getmAuth();
        daoTeacher = new DAOTeacher();
        btnreg.setOnClickListener(view -> createUser());

        tvDat.setOnClickListener(v -> {
            //Initialize alert dialog

            AlertDialog.Builder builder = new AlertDialog.Builder(

                    RegisterActivity.this

            );
            //Set title
            builder.setTitle("Seleccione Nivel");
            //Set dialog non cancellable
            builder.setCancelable(false);

            builder.setSingleChoiceItems(dayArray, -1, (dialogInterface, i) -> {

                if (!dayList.isEmpty()) {
                    dayList.clear();
                } else {
                    dayList.add(i);
                }

            });

            builder.setPositiveButton("Ok", (dialog, which) -> tvDat.setText(dayArray[dayList.get(0)]));
            builder.setNegativeButton("Cancel", (dialogInterface, i) -> dialogInterface.dismiss());


            builder.show();
        });


        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void createUser() {

        String email = etemail.getText().toString();
        String password = etpass.getText().toString();
        String passwordc = etpassc.getText().toString();
        String firstName = uname.getText().toString();
        String lastName = ulastname.getText().toString();

        if (TextUtils.isEmpty(firstName)) {

            uname.setError("Name cannot be empty");
            uname.requestFocus();
        } else if (TextUtils.isEmpty(lastName)) {

            ulastname.setError("Lname cannot be empty");
            ulastname.requestFocus();
        } else if (TextUtils.isEmpty(email)) {

            etemail.setError("Email cannot be empty");
            etemail.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            etpass.setError("Password cannot be empty");
            etpass.requestFocus();

        } else if (TextUtils.isEmpty(passwordc)) {
            etpassc.setError("Confirm your password");
            etpassc.requestFocus();
        } else if (!password.equals(passwordc)) {

            etpassc.setError("Passwords do not match");
            etpassc.requestFocus();

        } else {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {

                    TeacherUser tea = new TeacherUser(firstName,
                            lastName, email, tvDat.getText().toString());
                    daoTeacher.add(tea).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, "Usuario Registrado", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterActivity.this, getstarted1.class));
                            }else {
                                Toast.makeText(RegisterActivity.this, "Please Retry", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                } else {
                    Toast.makeText(RegisterActivity.this, "Error en registro" + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        }
    }
}


