package com.example.repomax;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Timer;
import java.util.TimerTask;

public class Splashzone extends AppCompatActivity {

    ImageView bgapp, cloverimg;
    LinearLayout splashtext, hometext, accbtn;
    Animation frombottom;
    Timer timer;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

       checkifUserIsAuthOrNot();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashzone);






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

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                Intent intent = new Intent(Splashzone.this, LoginActivity.class);
                        startActivity(intent);
                        finish();

            }
        },2500);



    }

    private void checkifUserIsAuthOrNot() {

        if(SessionManager.getInstance().getmAuth().getCurrentUser()!=null) {
            Log.d("Test", "checkifUserIsAuthOrNot: "+ SessionManager.getInstance().getmAuth().getCurrentUser());
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
    }


}