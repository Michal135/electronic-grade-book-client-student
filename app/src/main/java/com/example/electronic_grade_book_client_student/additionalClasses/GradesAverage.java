package com.example.electronic_grade_book_client_student.additionalClasses;

public class GradesAverage {
    private double average;
    private String subjectName;

    public GradesAverage() {
    }

    public GradesAverage(double average, String subjectName) {
        this.average = average;
        this.subjectName = subjectName;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
