package com.example.repomax;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SessionManager {

    public static SessionManager mSessionManager;
    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;

    private SessionManager() {
        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

    }

    public FirebaseAuth getmAuth() {
        return mAuth;
    }

    public void logoutFromSession() {
        mAuth.signOut();
    }

    public FirebaseDatabase getFirebaseDatabase(){
        return firebaseDatabase;
    }


    public static SessionManager getInstance() {

        if (mSessionManager == null) {
            mSessionManager = new SessionManager();
        }

        return mSessionManager;
    }

}





