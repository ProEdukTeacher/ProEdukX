package com.example.repomax;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class UserProfile extends AppCompatActivity {


    DatabaseReference mDatabaeReference;
    FirebaseUser mUser;
    EditText nameET,academicsET,emailET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
    nameET= findViewById(R.id.nameDisplay);
    academicsET = findViewById(R.id.academicDisplay);
    emailET = findViewById(R.id.emailDisplay);
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

    }

}