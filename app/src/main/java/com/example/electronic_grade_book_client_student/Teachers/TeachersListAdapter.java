package com.example.electronic_grade_book_client_student.Teachers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.electronic_grade_book_client_student.R;
import com.example.electronic_grade_book_client_student.model.Teacher;

import java.util.List;

public class TeachersListAdapter extends ArrayAdapter<Teacher> {

    private Context context;
    private List<Teacher> teachers;

    public TeachersListAdapter(Context context, List<Teacher> teachers){
        super(context, R.layout.row_in_teachers_list,teachers);
        this.context = context;
        this.teachers = teachers;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View row = layoutInflater.inflate(R.layout.row_in_teachers_list,parent,false);

        Teacher teacher = teachers.get(position);

        TextView textView = (TextView) row.findViewById(R.id.textViewTeacherName);
        textView.setText(teacher.getName());

        TextView textView1 = (TextView) row.findViewById(R.id.textviewTeacherSurname);
        textView1.setText(teacher.getSurname());

        //trzeba to bedzie w przyszlosci jakos inaczej przerobic - inne obrazki to na pewno, a moze jakos inaczej je wczytywac?
        ImageView imageView = (ImageView) row.findViewById(R.id.teacherIcon);
        imageView.setImageResource(R.drawable.ic_launcher_background);

        return row;
    }
}