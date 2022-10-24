package com.example.repomax;

public class TeacherUser {

    private String name;
    private String lastname;
    private String email;
    private String academiclevel;
    public TeacherUser(){}

    public TeacherUser(String name, String lastname, String email, String academiclevel) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.academiclevel = academiclevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAcademiclevel() {
        return academiclevel;
    }

    public void setAcademiclevel(String academiclevel) {
        this.academiclevel = academiclevel;
    }
}
