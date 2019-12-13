package com.example.electronic_grade_book_client_student.MyClass;

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
import com.example.electronic_grade_book_client_student.model.Student;

import java.util.List;

public class StudentListAdapter extends ArrayAdapter<Student> {

    private Context context;
    private List<Student> students;

    public StudentListAdapter(Context context, List<Student> students){
        super(context, R.layout.row_in_students_list,students);
        this.context = context;
        this.students = students;

    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View row = layoutInflater.inflate(R.layout.row_in_students_list,parent,false);

        Student student = students.get(position);

        TextView textView = (TextView) row.findViewById(R.id.textViewName);
        textView.setText(student.getName());

        TextView textView1 = (TextView) row.findViewById(R.id.teviewSurname);
        textView1.setText(student.getSurname());

//        TextView textView2 = (TextView) row.findViewById(R.id.textViewClass);
//        textView2.setText(student.getStudentClass());

        if(student.getSEX().equals("MALE")){
        ImageView imageView = (ImageView) row.findViewById(R.id.userIcon);
        imageView.setImageResource(R.drawable.ic_male_user_icon);
        }
        else{
            ImageView imageView = (ImageView) row.findViewById(R.id.userIcon);
            imageView.setImageResource(R.drawable.ic_female_user_icon);
        }
        return row;
    }
}
