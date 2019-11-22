package com.example.electronic_grade_book_client_student.Teachers;

import com.example.electronic_grade_book_client_student.model.Teacher;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface teachersService {

    @GET("/allTeachers")
    Call<List<Teacher>> getTeachers();
}
