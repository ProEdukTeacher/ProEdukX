package com.example.repomax;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginFragment extends Fragment implements View.OnClickListener {

    Button loginBtn, registerBtn;
    EditText LogEmail, LogPassword;
    FirebaseAuth mAuth;
    //    GoogleSignInOptions gso;
//    GoogleSignInClient gsc;
    TextView resetLink;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        resetLink = view.findViewById(R.id.resetLink);
        LogEmail = view.findViewById(R.id.LoginEmails);
        LogPassword = view.findViewById(R.id.LoginPasswords);
        mAuth = SessionManager.getInstance().getmAuth();
        loginBtn = view.findViewById(R.id.loginButton);
        registerBtn = view.findViewById(R.id.registosan);
        loginBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
        resetLink.setOnClickListener(this);

        return view;

    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.registosan) {
            Intent intent = new Intent(requireContext(), RegisterFragment.class);
            startActivity(intent);
        } else if (view.getId() == R.id.loginButton) {
            loginUser();
        }else if(view.getId() == R.id.resetLink){
            resetfunction();
        }

    }


    private void resetfunction() {
        Log.d("TAG", "resetfunction: ");
        String loginFrag = String.valueOf(this.getClass().getName());
        getParentFragmentManager().beginTransaction()
                .replace(R.id.loginActivityContainer,new ResetPasswordFragment())
                .addToBackStack(loginFrag)
                .commit();


    }

    private void loginUser() {
        String email = LogEmail.getText().toString();
        String password = LogPassword.getText().toString();

        if (TextUtils.isEmpty(email)) {

            LogEmail.setError("Email cannot be empty");
            LogEmail.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            LogPassword.setError("Password cannot be empty");
            LogPassword.requestFocus();
        } else {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        FirebaseUser user = SessionManager.getInstance().getmAuth().getCurrentUser();

                        if (user.isEmailVerified()) {
                            Toast.makeText(requireContext(), "Inicio de Sesion", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(requireContext(), getstarted1.class));
                        } else {
                            Toast.makeText(requireContext(), "Inicio de Sesion", Toast.LENGTH_SHORT).show();
                            Toast.makeText(requireContext(), "Check Your Email to verify your account", Toast.LENGTH_SHORT).show();
                            user.sendEmailVerification();
                            startActivity(new Intent(requireContext(), getstarted1.class));


                        }


                        /** Shoould update a value to database to know if its run or not run
                         as the user wont be bothered to read the get started after each login */
//                    Intent intent = new Intent(this, getstarted1.class);
//                    startActivity(intent);


                    } else {

                        Toast.makeText(requireContext(), "Error en Inicio de Sesion" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
    }
}