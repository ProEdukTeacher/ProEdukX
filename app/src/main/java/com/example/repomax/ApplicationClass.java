package com.example.repomax;

import android.app.Application;

import java.util.ArrayList;

public class ApplicationClass extends Application {


    public static ArrayList<Lasmaterias> materias;

    @Override
    public void onCreate() {
        super.onCreate();

        materias = new ArrayList<Lasmaterias>();
        materias.add(new Lasmaterias("Español", "9no grado"));
        materias.add(new Lasmaterias("Inglés", "9no grado"));
        materias.add(new Lasmaterias("Estudios Sociales", "11mo grado"));
        materias.add(new Lasmaterias("Ciencia", "11mo grado"));
        materias.add(new Lasmaterias("Matemáticas", "9no grado"));

    }
}
