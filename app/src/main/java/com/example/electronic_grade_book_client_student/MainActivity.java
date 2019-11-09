package com.example.electronic_grade_book_client_student;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.electronic_grade_book_client_student.model.Student;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    private TextView textView;
    public static String tekst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.listViewId);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://192.168.1.15:8080/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        service Retroservice = retrofit.create(service.class);

        Call<List<Student>> call = Retroservice.getStudents();
        call.enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                if(!response.isSuccessful()){
                    tekst=tekst+response.code();
//                    List<Flight> list = response.body();
//                    listView.setAdapter(new Ada));
                    return;
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
                    textView.append(tekst);
                }
//                System.out.println(students.toString());
            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });

    }


}
