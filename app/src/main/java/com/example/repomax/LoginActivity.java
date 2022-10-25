package com.example.repomax;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity {
    Button sin;
    EditText LogEmail, LogPassword;
    FirebaseAuth mAuth;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    TextView resetLink;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Google Sign in Options//

        gso =  new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);
        sin = findViewById(R.id.SIBTN);
        resetLink = findViewById(R.id.resetLink);

//        resetPassword();
        sin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

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

//    private void resetPassword() {
//        String email="user@example.com";
//        resetLink.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mAuth.sendPasswordResetEmail(email)
//
//            }
//        });
//    }

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

                    FirebaseUser user = SessionManager.getInstance().getmAuth().getCurrentUser();
                    if(user.isEmailVerified()) {
                        Toast.makeText(LoginActivity.this, "Inicio de Sesion", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, getstarted1.class));
                    }else{
                        Toast.makeText(LoginActivity.this, "Inicio de Sesion", Toast.LENGTH_SHORT).show();
                        Toast.makeText(LoginActivity.this, "Check Your Email to verify your account", Toast.LENGTH_SHORT).show();
                        user.sendEmailVerification();
                        startActivity(new Intent(LoginActivity.this, getstarted1.class));


                    }


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
    void signIn(){

        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent, 1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1000){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                navigatetoHomeActivity();
            } catch (ApiException e) {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }


        }


    }
    void navigatetoHomeActivity(){
        finish();
        Intent intent= new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);

    }
}
