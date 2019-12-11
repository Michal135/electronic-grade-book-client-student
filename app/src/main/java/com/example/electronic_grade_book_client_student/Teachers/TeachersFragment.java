package com.example.electronic_grade_book_client_student.Teachers;

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
import com.example.electronic_grade_book_client_student.model.Teacher;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TeachersFragment extends Fragment {

    public static String tekst;
    private ListView listViewProductList;

    RetroserviceConfig retroserviceConfig;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.teachers_fragment,container,false);
        final ListView listViewteachers = view.findViewById(R.id.listViewTeachersList);

        getActivity().setTitle("Teachers");

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new BasicAuthInterceptor(ConfigClass.getUser(),ConfigClass.getPassword())).build();
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://192.168.1.15:8080/").client(okHttpClient).addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        teachersService Retroservice = retrofit.create(teachersService.class);
        Call<List<Teacher>> call = Retroservice.getTeachers();
        System.out.println("CALL: " + call);
        System.out.println(Retroservice.getTeachers());
        call.enqueue(new Callback<List<Teacher>>() {
            @Override
            public void onResponse(Call<List<Teacher>> call, Response<List<Teacher>> response) {
                if(!response.isSuccessful()){
                    tekst=tekst+response.code();
                }
                if(response.code()==401){
                    System.out.println("nie masz dostepu");
                }
                List<Teacher> teachers = response.body();
                for(Teacher teacher:teachers){
                    String tekst = "ROLE: "+teacher.getROLE()
                            + "login: " +teacher.getLogin()
                            +" name: " +teacher.getName()
                            +" surname: " +teacher.getSurname()
                            +" id: " +teacher.getID()
                            +"pass: "+teacher.getPassword();

                    System.out.println(tekst);
                    listViewteachers.setAdapter(new TeachersListAdapter(getContext(),teachers));
                }
            }

            @Override
            public void onFailure(Call<List<Teacher>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });

        return view;
    }
}
