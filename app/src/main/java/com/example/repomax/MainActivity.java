package com.example.repomax;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;


public class MainActivity extends AppCompatActivity {


    FirebaseAuth mAuth;
    EditText loge, logp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        Button button2 = findViewById(R.id.btn1);
        button2.setOnClickListener(view -> loginUser());

        Button goback = findViewById(R.id.gobacktoit);
        goback.setOnClickListener(v -> {
            Intent intent = new Intent(this, Splashzone.class);
            startActivity(intent);
        });

        EditText loge = findViewById(R.id.LoginEmails);
        EditText logp= findViewById(R.id.LoginPasswords);



    }
    private void loginUser(){

        String email = loge.getText().toString();
        String password = logp.getText().toString();

        if (TextUtils.isEmpty(email)){

            loge.setError("Email cannot be empty");
            loge.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            logp.setError("Password cannot be empty");
            logp.requestFocus();
    }else{
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                Toast.makeText(MainActivity.this, "Inicio de Sesion", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
            }else {

                Toast.makeText(MainActivity.this, "Error en Inicio de Sesion" + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();

            }
        });



        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user==null){
            startActivity(new Intent(MainActivity.this,HomeActivity.class));
        }
    }
}
