package com.example.repomax;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;

import java.util.List;

public class ResetPasswordFragment extends Fragment implements View.OnClickListener {

    FirebaseAuth mAuth;
    EditText userEmail;
    Button resetEmail;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.passwordresetform, container, false);
        mAuth = SessionManager.getInstance().getmAuth();
        userEmail = view.findViewById(R.id.userEmail);
        resetEmail = view.findViewById(R.id.resetButton);
        resetEmail.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.resetButton){
            reset();
        }
    }

    private void reset() {
        String email = userEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {

            userEmail.setError("Email cannot be empty");
            userEmail.requestFocus();
        }else{
           mAuth.fetchSignInMethodsForEmail(email).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
               @Override
               public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {

                   if(task.isSuccessful()) {
                       SignInMethodQueryResult result = task.getResult();
                       List<String> signInMethods = result.getSignInMethods();

                       if(signInMethods.isEmpty())
                       {
                           Toast.makeText(requireContext(), "Email is invalid or unregistered", Toast.LENGTH_SHORT).show();

                   } else {

                           mAuth.sendPasswordResetEmail(email);
                           Toast.makeText(requireContext(),"Instruction to reset password is send over Email", Toast.LENGTH_SHORT).show();

                       }

                   }

               }
           });


        }
    }
}
