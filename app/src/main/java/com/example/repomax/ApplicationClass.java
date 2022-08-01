package com.example.repomax;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.function.ToDoubleBiFunction;

public class ApplicationClass extends Application {


    public static ArrayList<Lasmaterias> materias;


    @Override
    public void onCreate() {
        super.onCreate();

        materias = new ArrayList<>();







    }

}
