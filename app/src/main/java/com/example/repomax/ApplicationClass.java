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


    public SessionManager mySession;

    public static ArrayList<Lasmateriasatt> materiasatt;
    public static ArrayList<Lasnotasmat> lasnotasmat;


    @Override
    public void onCreate() {
        super.onCreate();
        mySession = SessionManager.getInstance();

        /* Todo: Load the data here from firebase */


        materiasatt = new ArrayList<>();
        materiasatt.add(new Lasmateriasatt("9","Ciencia"));
        lasnotasmat = new ArrayList<>();
        lasnotasmat.add(new Lasnotasmat("English", "Noveno Grado"));




    }


    }


