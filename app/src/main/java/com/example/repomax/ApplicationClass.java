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

        /* Todo: Load the data here from firebase */


        materias = new ArrayList<>();
        materias.add(new Lasmaterias("Español", "Noveno Grado"));
        materias.add(new Lasmaterias("Español", "Octavo Grado"));
        materias.add(new Lasmaterias("Español", "Séptimo Grado"));
        materias.add(new Lasmaterias("Español", "Sexto Grado"));
        materias.add(new Lasmaterias("Español", "Quinto Grado"));


    }

    }


