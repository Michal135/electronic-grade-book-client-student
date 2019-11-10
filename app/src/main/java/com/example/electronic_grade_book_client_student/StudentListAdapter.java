package com.example.electronic_grade_book_client_student;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.electronic_grade_book_client_student.model.Student;

import java.util.List;

public class StudentListAdapter extends ArrayAdapter<Student> {

    private Context context;
    private List<Student> students;

    public StudentListAdapter(Context context, List<Student> students){
        super(context,R.layout.row_in_students_list,students);
        this.context = context;
        this.students = students;

    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(R.layout.row_in_students_list,parent,false);

        Student student = students.get(position);
        TextView textView = (TextView) convertView.findViewById(R.id.textViewName);
        textView.setText(student.getName());

        return convertView;

    }
}
