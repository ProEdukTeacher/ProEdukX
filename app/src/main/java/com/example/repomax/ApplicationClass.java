package com.example.repomax;

import android.app.Application;

import java.util.ArrayList;

public class ApplicationClass extends Application {


    public static ArrayList<Lasmaterias> materias;




    @Override
    public void onCreate() {
        super.onCreate();

        materias = new ArrayList<>();






    }
}
