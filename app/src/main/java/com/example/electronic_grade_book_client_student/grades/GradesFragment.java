package com.example.electronic_grade_book_client_student.grades;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.electronic_grade_book_client_student.BasicAuthInterceptor;
import com.example.electronic_grade_book_client_student.MyClass.StudentListAdapter;
import com.example.electronic_grade_book_client_student.MyClass.service;
import com.example.electronic_grade_book_client_student.R;
import com.example.electronic_grade_book_client_student.RetroserviceConfig;
import com.example.electronic_grade_book_client_student.config.ConfigClass;
import com.example.electronic_grade_book_client_student.model.Grade;
import com.example.electronic_grade_book_client_student.model.News;
import com.example.electronic_grade_book_client_student.model.Student;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GradesFragment extends Fragment {

    public static String tekst;
    private ListView listViewProductList;

    RetroserviceConfig retroserviceConfig;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.grades_fragment,container,false);
        final ListView listView = view.findViewById(R.id.studentGradesList);

        getActivity().setTitle(R.string.grades_history);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new BasicAuthInterceptor(ConfigClass.getUser(),ConfigClass.getPassword())).build();
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://192.168.1.15:8080/").client(okHttpClient).addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        gradesService Retroservice = retrofit.create(gradesService.class);

        Call<List<Grade>> call = Retroservice.getGradesForStudent(ConfigClass.getUser());
        System.out.println("CALL: " + call);
        System.out.println(Retroservice.getGradesForStudent(ConfigClass.getUser()));
        call.enqueue(new Callback<List<Grade>>() {
            @Override
            public void onResponse(Call<List<Grade>> call, Response<List<Grade>> response) {
                if(!response.isSuccessful()){
                    tekst=tekst+response.code();
                }
                if(response.code()==401){
                    System.out.println("nie masz dostepu");
                }
                List<Grade> allNews = response.body();
                for(Grade grade:allNews){
                    String tekst = "student: "+grade.getStudent()
                            + "date: " +grade.dateOfIssue;

                    System.out.println(tekst);
                    listView.setAdapter(new GradeListAdapter(getContext(),allNews));
                }
            }

            @Override
            public void onFailure(Call<List<Grade>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });

        return view;
    }
}