package com.example.repomax;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Splashzone extends AppCompatActivity {

    ImageView bgapp, cloverimg;
    LinearLayout splashtext, hometext, accbtn;
    Animation frombottom;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashzone);
        Button btnre = findViewById(R.id.registobtn);
        btnre.setOnClickListener(v -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);

        });

        mAuth = FirebaseAuth.getInstance();

        Button accessbnn = findViewById(R.id.accessbnn);
        accessbnn.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

        });

        frombottom = AnimationUtils.loadAnimation(this, R.anim.frombottom);

        bgapp = findViewById(R.id.bgapp);
        cloverimg = findViewById(R.id.cloverimg);
        splashtext = findViewById(R.id.splashtext);
        hometext = findViewById(R.id.hometext);
        accbtn = findViewById(R.id.accbtn);

        bgapp.animate().translationY(-1200).setDuration(2000).setStartDelay(300);
        cloverimg.animate().alpha(0).setDuration(2000).setStartDelay(600);
        splashtext.animate().translationY(140).alpha(0).setDuration(2000).setStartDelay(600);
        hometext.startAnimation(frombottom);
        accbtn.startAnimation(frombottom);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user==null){
            startActivity(new Intent(Splashzone.this,RegisterActivity.class));
        }
    }
}