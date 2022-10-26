package com.example.repomax;

import java.util.ArrayList;

public class Lasnotasmat {
    private static final ArrayList<Lasnotasmat> lasnotasmat = new ArrayList<>();
    private String thesubject;
    private String thegrade;

    public Lasnotasmat(String subjects, String grades) {
        this.thesubject = subjects;
        this.thegrade = grades;
    }


    public static ArrayList<Lasnotasmat> getLasnotasmat() {






        return lasnotasmat;
    }

    public static void setLasnotasmatArrayList(Lasnotasmat c) {
        Lasnotasmat.lasnotasmat.add(c);
    }

    public String getThesubject() {
        return thesubject;
    }

    public void setThesubject(String thesubject) {
        this.thesubject = thesubject;
    }

    public String getThegrade() {
        return thegrade;
    }

    public void setGrade(String Thegrade) {
        this.thegrade = thegrade;
    }
}
