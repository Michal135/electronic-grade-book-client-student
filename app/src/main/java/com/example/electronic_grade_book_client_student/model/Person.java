package com.example.electronic_grade_book_client_student.model;

import com.google.gson.annotations.SerializedName;

public abstract class Person {

    @SerializedName("login")
    private String login;
    @SerializedName("password")
    private String password;
    @SerializedName("name")
    private String name;
    @SerializedName("surname")
    private String surname;
    @SerializedName("id")
    private int id;
    @SerializedName("role")
    private String role;

    public Person() {
    }

    public int getID() {
        return id;
    }

    public void setID(int ID) {
        this.id = ID;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getROLE() {
        return role;
    }

    public void setROLE(String ROLE) {
        this.role = ROLE;
    }

    @Override
    public String toString() {
        return "Person{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", ID=" + id +
                ", ROLE='" + role + '\'' +
                '}';
    }
}
