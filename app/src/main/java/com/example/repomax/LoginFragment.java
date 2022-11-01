package com.example.repomax;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.BeginSignInResult;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginFragment extends Fragment implements View.OnClickListener {

    Button loginBtn, registerBtn;
    EditText LogEmail, LogPassword;
    FirebaseAuth mAuth;
    LinearLayout googleSignIn;
    private SignInClient oneTapClient;
    private BeginSignInRequest signInRequest;
    TextView resetLink;
    final String loginFrag = String.valueOf(this.getClass().getName());
    ActivityResultLauncher<IntentSenderRequest> resultLauncher = registerForActivityResult(new ActivityResultContracts.StartIntentSenderForResult(), result -> {

        SignInCredential credential = null;

        try {
            credential = oneTapClient.getSignInCredentialFromIntent(result.getData());
            String idToken = credential.getGoogleIdToken();
            String username = credential.getId();
            String password = credential.getPassword();
            if (idToken != null) {
//                            return idToken
                Log.d("TAG", "Got ID token.");
            } else if (password != null) {
                // Got a saved username and password. Use them to authenticate
                // with your backend.
                Log.d("TAG", "Got password.");
            }
        } catch (ApiException e) {
            switch (e.getStatusCode()) {
                case CommonStatusCodes.CANCELED:
                    Log.d("TAG", "One-tap dialog was closed.");
                    // Don't re-prompt the user.
                    boolean showOneTapUI = false;
                    break;
                case CommonStatusCodes.NETWORK_ERROR:
                    Log.d("TAG", "One-tap encountered a network error.");
                    // Try again or just ignore.
                    break;
                default:
                    Log.d("TAG", "Couldn't get credential from result."
                            + e.getLocalizedMessage());
                    break;
            }
        }

    });

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        return view;

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        resetLink = view.findViewById(R.id.resetLink);
        LogEmail = view.findViewById(R.id.LoginEmails);
        LogPassword = view.findViewById(R.id.LoginPasswords);
        mAuth = SessionManager.getInstance().getmAuth();
        loginBtn = view.findViewById(R.id.loginButton);
        registerBtn = view.findViewById(R.id.registosan);
        googleSignIn = view.findViewById(R.id.googleSignIn);

        loginBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
        resetLink.setOnClickListener(this);
        googleSignIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.registosan) {
            registrationStart();
        } else if (view.getId() == R.id.loginButton) {
            loginUser();
        } else if (view.getId() == R.id.resetLink) {
            resetfunction();
        } else if (view.getId() == R.id.googleSignIn) {
            googleSignInFun();
        }

    }

    private void googleSignInFun() {

        oneTapClient = Identity.getSignInClient(requireContext());
        signInRequest = BeginSignInRequest.builder()
                .setPasswordRequestOptions(BeginSignInRequest
                        .PasswordRequestOptions.builder()
                        .setSupported(true).build())
                .setGoogleIdTokenRequestOptions(BeginSignInRequest
                        .GoogleIdTokenRequestOptions.builder()
                        .setSupported(true)
                        .setServerClientId(getString(R.string.web_client_id))
                        .setFilterByAuthorizedAccounts(true)
                        .build())
                .setAutoSelectEnabled(true)
                .build();

        oneTapClient.beginSignIn(signInRequest).addOnSuccessListener(requireActivity(), new OnSuccessListener<BeginSignInResult>() {
            @Override
            public void onSuccess(BeginSignInResult beginSignInResult) {
            resultLauncher.launch(new IntentSenderRequest.Builder(beginSignInResult.getPendingIntent().getIntentSender()).build());


    }

});


        }

private void registrationStart(){
        Log.d("TAG","resetfunction: ");

        getParentFragmentManager().beginTransaction()
        .replace(R.id.loginActivityContainer,new RegisterFragment())
        .addToBackStack(loginFrag)
        .commit();
        }


private void resetfunction(){
        Log.d("TAG","resetfunction: ");
        getParentFragmentManager().beginTransaction()
        .replace(R.id.loginActivityContainer,new ResetPasswordFragment())
        .addToBackStack(loginFrag)
        .commit();


        }

private void loginUser(){
        String email=LogEmail.getText().toString();
        String password=LogPassword.getText().toString();

        if(TextUtils.isEmpty(email)){

        LogEmail.setError("Email cannot be empty");
        LogEmail.requestFocus();
        }else if(TextUtils.isEmpty(password)){
        LogPassword.setError("Password cannot be empty");
        LogPassword.requestFocus();
        }else{
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>(){
@Override
public void onComplete(@NonNull Task<AuthResult> task){
        if(task.isSuccessful()){

        FirebaseUser user=SessionManager.getInstance().getmAuth().getCurrentUser();

        if(user.isEmailVerified()){
        Toast.makeText(requireContext(),"Inicio de Sesion",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(requireContext(),getstarted1.class));
        }else{
        Toast.makeText(requireContext(),"Inicio de Sesion",Toast.LENGTH_SHORT).show();
        Toast.makeText(requireContext(),"Check Your Email to verify your account",Toast.LENGTH_SHORT).show();
        user.sendEmailVerification();
        startActivity(new Intent(requireContext(),getstarted1.class));


        }


        /** Shoould update a value to database to know if its run or not run
         as the user wont be bothered to read the get started after each login */
//                    Intent intent = new Intent(this, getstarted1.class);
//                    startActivity(intent);


        }else{

        Toast.makeText(requireContext(),"Error en Inicio de Sesion"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();

        }
        }
        });
        }
        }
        }