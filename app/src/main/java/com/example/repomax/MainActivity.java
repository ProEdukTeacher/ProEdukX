package com.example.repomax;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    Button goback;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button2 = findViewById(R.id.btn1);
        button2.setOnClickListener(v -> {
            Intent intent = new Intent(this, getstarted1.class);
            startActivity(intent);
        });

        Button goback = findViewById(R.id.gobacktoit);
        goback.setOnClickListener(v -> {
            Intent intent = new Intent(this, Splashzone.class);
            startActivity(intent);
        });




    }


}
