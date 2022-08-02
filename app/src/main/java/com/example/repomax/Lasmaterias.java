package com.example.repomax;

import java.util.ArrayList;

public class Lasmaterias {
    private static final ArrayList<Lasmaterias> lasmaterias = new ArrayList<>();
    private String name;
    private String telNr;

    public Lasmaterias(String name, String telNr) {
        this.name = name;
        this.telNr = telNr;
    }


    public static ArrayList<Lasmaterias> getLasmaterias() {






        return lasmaterias;
    }

    public static void setLasmateriasArrayList(Lasmaterias p) {
        Lasmaterias.lasmaterias.add(p);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelNr() {
        return telNr;
    }

    public void setTelNr(String telNr) {
        this.telNr = telNr;
    }
}
