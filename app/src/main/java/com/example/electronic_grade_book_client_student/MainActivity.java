package com.example.electronic_grade_book_client_student;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.electronic_grade_book_client_student.model.Student;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
//    private TextView textView;
    public static String tekst;
    private ListView listViewStudentsList;
//    private List<Student> students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawyer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.open_navigation_drawer,R.string.close_navigation_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState==null){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new MyClassFragment()).commit();
        navigationView.setCheckedItem(R.id.Myclass);
        }


//        textView = (TextView)findViewById(R.id.listViewId);

//        listViewStudentsList = findViewById(R.id.listViewProductList);
//
//        Retrofit.Builder builder = new Retrofit.Builder()
//                .baseUrl("http://192.168.1.15:8080/")
//                .addConverterFactory(GsonConverterFactory.create());
//        Retrofit retrofit = builder.build();
//        service Retroservice = retrofit.create(service.class);
//
//        Call<List<Student>> call = Retroservice.getStudents();
//        call.enqueue(new Callback<List<Student>>() {
//            @Override
//            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
//                if(!response.isSuccessful()){
//                    tekst=tekst+response.code();
//                    return;
//                }
//                List<Student> students = response.body();
//                for(Student student:students){
//                    String tekst = "ROLE: "+student.getROLE()
//                            + "login: " +student.getLogin()
//                            +" name: " +student.getName()
//                            +" surname: " +student.getSurname()
//                            +" id: " +student.getID()
//                            +"pass: "+student.getPassword();
//
//                    System.out.println(tekst);
//                    listViewStudentsList.setAdapter(new StudentListAdapter(getApplicationContext(),students));
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Student>> call, Throwable t) {
//                System.out.println(t.getMessage());
//            }
//        });

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.Myclass:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new MyClassFragment()).commit();
                break;
            case R.id.Teachers:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new NewsFragment()).commit();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.isDrawerOpen(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
}
