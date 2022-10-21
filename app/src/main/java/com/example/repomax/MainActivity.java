package com.example.repomax;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import javax.security.auth.login.LoginException;


public class MainActivity extends AppCompatActivity {

    Button goback;
    FirebaseAuth mAuth;
    EditText LogEmail, LogPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button2 = findViewById(R.id.btn1);
        button2.setOnClickListener(v -> {
            Intent intent = new Intent(this, getstarted1.class);
            startActivity(intent);
            loginUser();
        });

        Button goback = findViewById(R.id.gobacktoit);
        goback.setOnClickListener(v -> {
            Intent intent = new Intent(this, Splashzone.class);
            startActivity(intent);
        });

        EditText LogEmail = findViewById(R.id.LoginEmails);
        EditText LogPassword= findViewById(R.id.LoginPasswords);

        mAuth = FirebaseAuth.getInstance();

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
                    Toast.makeText(MainActivity.this, "Inicio de Sesion", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                }else {

                    Toast.makeText(MainActivity.this, "Error en Inicio de Sesion" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        });



        }
    }


}
