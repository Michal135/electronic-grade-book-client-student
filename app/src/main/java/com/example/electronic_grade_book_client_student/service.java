package com.example.electronic_grade_book_client_student;

import com.example.electronic_grade_book_client_student.model.Student;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;


public interface service {

    @GET("/student")
    Call<List<Student>> getStudents();

}
