package com.example.repomax;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginActivity extends AppCompatActivity {

    Button goback, btn1Log;
    EditText LogEmail, LogPassword;
    FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button2 = findViewById(R.id.btn1);
        button2.setOnClickListener(v -> {

       loginUser();
        });

        Button goback = findViewById(R.id.gobacktoit);
        goback.setOnClickListener(v -> {
            Intent intent = new Intent(this, Splashzone.class);
            startActivity(intent);
        });

        LogEmail = findViewById(R.id.LoginEmails);
        LogPassword = findViewById(R.id.LoginPasswords);

        mAuth = SessionManager.getInstance().getmAuth();


    }
    private void loginUser(){

        String email = LogEmail.getText().toString();
        String password = LogPassword.getText().toString();

        if (TextUtils.isEmpty(email)){

            LogEmail.setError("Email cannot be empty");
            LogEmail.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            LogPassword.setError("Password cannot be empty");
            LogPassword.requestFocus();
    }else{
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "Inicio de Sesion", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));

                    /** Shoould update a value to database to know if its run or not run
                     as the user wont be bothered to read the get started after each login */
//                    Intent intent = new Intent(this, getstarted1.class);
//                    startActivity(intent);


                }else {

                    Toast.makeText(LoginActivity.this, "Error en Inicio de Sesion" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        });



        }
    }

}
