package com.example.electronic_grade_book_client_student.model;


import java.time.LocalDateTime;

public class Grade {


    private long ID;

    public int grade;

    public String dateOfIssue;

    public Student student;

    public Subject subject;

    public Grade() {
    }

    public Grade(int grade, String dateOfIssue, Student student, Subject subject) {
        this.grade = grade;
        this.dateOfIssue = dateOfIssue;
        this.student = student;
        this.subject = subject;
    }

    public long getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(String dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
