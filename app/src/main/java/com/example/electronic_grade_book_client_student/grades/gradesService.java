package com.example.electronic_grade_book_client_student.grades;

import com.example.electronic_grade_book_client_student.model.Grade;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface gradesService {

    @GET("/studentGradesByLogin/{login}")
    Call<List<Grade>> getGradesForStudent(@Path("login") String login);

    @GET("/allGrades")
    Call<List<Grade>> getAllGrades();

}
