package com.example.electronic_grade_book_client_student.model;

public class Student extends Person {

    private String studentClass;

//    public Student(int ID, String login, String password, String name, String surname, String studentClass) {
//        super(ID, login, password, name, surname);
//        this.studentClass = studentClass;
//        setROLE("STUDENT");
//    }

    public Student() {
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentClass='" + studentClass + '\'' +
                '}';
    }
}

