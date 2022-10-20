package com.example.repomax;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SessionManager {

    public static SessionManager mSessionManager;
    private FirebaseAuth mAuth;

    private SessionManager() {
        mAuth = FirebaseAuth.getInstance();

    }

    public FirebaseAuth getmAuth() {
        return mAuth;
    }

    public void  logoutFromSession(){
        mAuth.signOut();
    }


    public static SessionManager getInstance() {

        if (mSessionManager == null) {
            mSessionManager = new SessionManager();
        }

        return mSessionManager;
    }
}
