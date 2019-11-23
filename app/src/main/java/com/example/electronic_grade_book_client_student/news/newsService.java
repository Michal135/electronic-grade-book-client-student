package com.example.electronic_grade_book_client_student.news;

import com.example.electronic_grade_book_client_student.model.News;
import com.example.electronic_grade_book_client_student.model.Teacher;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface newsService {


    @GET("/allNews")
    Call<List<News>> getNews();
}
