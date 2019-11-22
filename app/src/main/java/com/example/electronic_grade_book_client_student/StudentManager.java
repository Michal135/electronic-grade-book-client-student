package com.example.electronic_grade_book_client_student;

import com.example.electronic_grade_book_client_student.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentManager {

    List<Student> students;

    public StudentManager() {
        students = new ArrayList<>();
}

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "allStudents{" +
                "students=" + students +
                '}';
    }
}
