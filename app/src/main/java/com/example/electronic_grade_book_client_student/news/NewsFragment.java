package com.example.electronic_grade_book_client_student.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.electronic_grade_book_client_student.BasicAuthInterceptor;
import com.example.electronic_grade_book_client_student.MyClass.StudentListAdapter;
import com.example.electronic_grade_book_client_student.MyClass.service;
import com.example.electronic_grade_book_client_student.R;
import com.example.electronic_grade_book_client_student.RetroserviceConfig;
import com.example.electronic_grade_book_client_student.config.ConfigClass;
import com.example.electronic_grade_book_client_student.model.News;
import com.example.electronic_grade_book_client_student.model.Student;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsFragment extends Fragment {

    public static String tekst;
    private ListView listViewProductList;

    RetroserviceConfig retroserviceConfig;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_fragment,container,false);
        final ListView listView = view.findViewById(R.id.newsList);

        getActivity().setTitle("News");

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new BasicAuthInterceptor(ConfigClass.getUser(),ConfigClass.getPassword())).build();

        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://192.168.1.15:8080/").client(okHttpClient).addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        newsService Retroservice = retrofit.create(newsService.class);

        Call<List<News>> call = Retroservice.getNews();
        System.out.println("CALL: " + call);
        System.out.println(Retroservice.getNews());
        call.enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                if(!response.isSuccessful()){
                    tekst=tekst+response.code();
                }
                if(response.code()==401){
                    System.out.println("nie masz dostepu");
                }
                List<News> allNews = response.body();
                for(News news:allNews){
                    String tekst = "description: "+news.getDescription()
                            + "login: " +news.getAuthor();

                    System.out.println(tekst);
                    listView.setAdapter(new newsListAdapter(getContext(),allNews));
                }
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });

        return view;
    }
}