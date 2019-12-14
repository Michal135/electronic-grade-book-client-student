package com.example.electronic_grade_book_client_student.model;

public class News {

    private int ID;

    private String description;

    private String author;

    private String priority;

    public News() {
    }

    public News(String description, String author, String priority) {
        this.description = description;
        this.author = author;
        this.priority = priority;
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

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}

