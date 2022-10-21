package com.example.repomax;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Splashzone extends AppCompatActivity {

    ImageView bgapp, cloverimg;
    LinearLayout splashtext, hometext, accbtn;
    Animation frombottom;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

       checkifUserIsAuthOrNot();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashzone);
        Button btnre = findViewById(R.id.registobtn);
        btnre.setOnClickListener(v -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);

        });

        Button abtn = findViewById(R.id.abtn);
        abtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
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

    private void checkifUserIsAuthOrNot() {

        if(SessionManager.getInstance().getmAuth().getCurrentUser()!=null) {
            Log.d("Test", "checkifUserIsAuthOrNot: "+ SessionManager.getInstance().getmAuth().getCurrentUser());
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
    }


}