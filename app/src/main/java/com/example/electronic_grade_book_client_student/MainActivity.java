package com.example.electronic_grade_book_client_student;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.electronic_grade_book_client_student.MyClass.MyClassFragment;
import com.example.electronic_grade_book_client_student.Teachers.TeachersFragment;
import com.example.electronic_grade_book_client_student.grades.GradesFragment;
import com.example.electronic_grade_book_client_student.news.NewsFragment;
import com.example.electronic_grade_book_client_student.radarCharts.RadarChartsFragment;
import com.google.android.material.navigation.NavigationView;


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
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new NewsFragment()).commit();
        navigationView.setCheckedItem(R.id.News);
        }

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.Myclass:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new MyClassFragment()).commit();
                break;
            case R.id.Teachers:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new TeachersFragment()).commit();
                break;
            case R.id.News:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new NewsFragment()).commit();
                break;
            case R.id.Grades:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new GradesFragment()).commit();
                break;
            case R.id.Radar_charts:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new RadarChartsFragment()).commit();
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
