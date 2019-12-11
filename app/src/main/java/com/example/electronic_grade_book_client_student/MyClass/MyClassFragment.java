package com.example.electronic_grade_book_client_student.MyClass;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.electronic_grade_book_client_student.BasicAuthInterceptor;
import com.example.electronic_grade_book_client_student.R;
import com.example.electronic_grade_book_client_student.RetroserviceConfig;
import com.example.electronic_grade_book_client_student.config.ConfigClass;
import com.example.electronic_grade_book_client_student.model.Student;


import java.util.List;


import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyClassFragment extends Fragment {

    public static String tekst;
    private ListView listViewProductList;

    RetroserviceConfig retroserviceConfig;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_class_fragment,container,false);
        final ListView listView = view.findViewById(R.id.myClassAllPeopleList);

        getActivity().setTitle("MyClass");

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new BasicAuthInterceptor(ConfigClass.getUser(),ConfigClass.getPassword())).build();

        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://192.168.1.15:8080/").client(okHttpClient).addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        service Retroservice = retrofit.create(service.class);
//        service Retroservice = new RetroserviceConfig().init();
        Call<List<Student>> call = Retroservice.getStudents();
        System.out.println("CALL: " + call);
        System.out.println(Retroservice.getStudents());
        call.enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                if(!response.isSuccessful()){
                    tekst=tekst+response.code();
                }
                if(response.code()==401){
                    System.out.println("nie masz dostepu");
                }
                List<Student> students = response.body();
                for(Student student:students){
                    String tekst = "ROLE: "+student.getROLE()
                            + "login: " +student.getLogin()
                            +" name: " +student.getName()
                            +" surname: " +student.getSurname()
                            +" id: " +student.getID()
                            +"pass: "+student.getPassword();

                    System.out.println(tekst);
                    listView.setAdapter(new StudentListAdapter(getContext(),students));
                }
            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });

        return view;
    }
}
