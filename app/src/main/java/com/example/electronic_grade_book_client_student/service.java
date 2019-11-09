package com.example.electronic_grade_book_client_student;

import com.example.electronic_grade_book_client_student.model.Student;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;



public interface service {

    @GET("/allStudents")
    Call<List<Student>> getStudents();

}
