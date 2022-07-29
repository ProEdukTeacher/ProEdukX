package com.example.repomax;

public class Lasmaterias {

    private String name;
    private String telNr;

    public Lasmaterias(String name, String telNr) {
        this.name = name;
        this.telNr = telNr;
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
