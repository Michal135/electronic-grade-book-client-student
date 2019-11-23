package com.example.electronic_grade_book_client_student.model;

public class News {

    private int ID;

    private String description;

    private String author;

    public News() {
    }

    public News(String description, String author) {
        this.description = description;
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

