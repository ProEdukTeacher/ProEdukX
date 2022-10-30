package com.example.repomax;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Timer;
import java.util.TimerTask;


public class SplashFragment extends Fragment {


    ImageView bgapp, cloverimg;
    LinearLayout splashtext, hometext, accbtn;
    Animation frombottom;
    Timer timer;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        checkifUserIsAuthOrNot();
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_splash, container, false);

        frombottom = AnimationUtils.loadAnimation(getActivity(), R.anim.frombottom);

        bgapp = view.findViewById(R.id.bgapp);
        cloverimg = view.findViewById(R.id.cloverimg);
        splashtext = view.findViewById(R.id.splashtext);
        hometext = view.findViewById(R.id.hometext);


        bgapp.animate().translationY(-1200).setDuration(2000).setStartDelay(300);
        cloverimg.animate().alpha(0).setDuration(2000).setStartDelay(600);
        splashtext.animate().translationY(140).alpha(0).setDuration(2000).setStartDelay(600);
        hometext.startAnimation(frombottom);


        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);


            }
        },2500);

        return view;
    }

    private void checkifUserIsAuthOrNot() {

        if(SessionManager.getInstance().getmAuth().getCurrentUser()!=null) {
            Log.d("Test", "checkifUserIsAuthOrNot: "+ SessionManager.getInstance().getmAuth().getCurrentUser());
            Intent intent = new Intent(getActivity(), HomeActivity.class);
            startActivity(intent);
        }
    }


}
