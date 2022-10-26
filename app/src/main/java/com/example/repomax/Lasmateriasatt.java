package com.example.repomax;

import java.util.ArrayList;

public class Lasmateriasatt {
    private static final ArrayList<Lasmateriasatt> lasmateriasatt = new ArrayList<>();
    private String grades;
    private String classes;

    public Lasmateriasatt(String grades, String classes) {
        this.grades = grades;
        this.classes = classes;
    }


    public static ArrayList<Lasmateriasatt> getLasmateriasatt() {






        return lasmateriasatt;
    }

    public static void setLasmateriasattArrayList(Lasmateriasatt c) {
        Lasmateriasatt.lasmateriasatt.add(c);
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getGrades() {
        return grades;
    }

    public void setGrade(String grades) {
        this.grades = grades;
    }
}
