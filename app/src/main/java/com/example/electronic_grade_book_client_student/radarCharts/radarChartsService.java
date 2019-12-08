package com.example.electronic_grade_book_client_student.radarCharts;


import com.example.electronic_grade_book_client_student.additionalClasses.GradesAverage;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface radarChartsService {

    @GET("/gradesAverageListFromAllSubjects/{login}")
    Call<List<GradesAverage>> getGradesAverageForStudent(@Path("login") String login);
}
