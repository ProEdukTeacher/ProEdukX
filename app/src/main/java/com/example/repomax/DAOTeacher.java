package com.example.repomax;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOTeacher {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    public DAOTeacher(){


        firebaseDatabase = SessionManager.getInstance().getFirebaseDatabase();
        databaseReference = firebaseDatabase.getReference(TeacherUser.class.getSimpleName());
    }

    public Task<Void> add (TeacherUser tea){

        return databaseReference.push().setValue(tea);

    }

}
