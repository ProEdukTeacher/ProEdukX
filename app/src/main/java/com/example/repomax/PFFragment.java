package com.example.repomax;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class PFFragment extends Fragment {

    DatabaseReference mDatabaeReference;
    FirebaseUser mUser;
    EditText nameET,academicsET,emailET;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Initialize view

        View view = inflater.inflate(R.layout.fragment_p_f, container, false);


        nameET= view.findViewById(R.id.nameDisplay);
        academicsET = view.findViewById(R.id.academicDisplay);
        emailET = view.findViewById(R.id.emailDisplay);
        mUser = SessionManager.getInstance().getmAuth().getCurrentUser();
        mDatabaeReference = SessionManager.getInstance().getFirebaseDatabase().getReference(TeacherUser.class.getSimpleName());

        mDatabaeReference.child(mUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                TeacherUser user = snapshot.getValue(TeacherUser.class);
                if(user!=null){
                    String fullName = user.getName();
                    String email = user.getEmail();
                    String academicLevel = user.getAcademiclevel();

                    nameET.setText(fullName);
                    emailET.setText(email);
                    academicsET.setText(academicLevel);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        return view;

    }




}