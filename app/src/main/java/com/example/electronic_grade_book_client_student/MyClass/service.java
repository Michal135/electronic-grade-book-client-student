package com.example.electronic_grade_book_client_student.MyClass;

import com.example.electronic_grade_book_client_student.model.Student;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface service {

    @GET("/student")
    Call<List<Student>> getStudents();

}
