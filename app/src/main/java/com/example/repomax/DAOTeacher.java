package com.example.repomax;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class DAOTeacher {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    public DAOTeacher(){


        firebaseDatabase = SessionManager.getInstance().getFirebaseDatabase();
        databaseReference = firebaseDatabase.getReference(TeacherUser.class.getSimpleName());
    }

    public Task<Void> add (TeacherUser tea){

        return databaseReference.child(Objects.requireNonNull(SessionManager.getInstance().getmAuth().getCurrentUser()).getUid()).setValue(tea);

    }

}
