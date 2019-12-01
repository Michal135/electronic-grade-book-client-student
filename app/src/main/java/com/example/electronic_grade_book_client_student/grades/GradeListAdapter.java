package com.example.electronic_grade_book_client_student.grades;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.electronic_grade_book_client_student.R;
import com.example.electronic_grade_book_client_student.model.Grade;
import com.example.electronic_grade_book_client_student.model.Student;

import java.util.List;

public class GradeListAdapter extends ArrayAdapter<Grade> {

    private Context context;
    private List<Grade> grades;

    public GradeListAdapter(Context context, List<Grade> students){
        super(context, R.layout.row_in_grades_list,students);
        this.context = context;
        this.grades = students;

    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View row = layoutInflater.inflate(R.layout.row_in_grades_list,parent,false);

        Grade grade = grades.get(position);

        TextView textView = (TextView) row.findViewById(R.id.subjectName);
        String gradeString = Integer.toString(grade.getGrade());
        textView.setText(gradeString);

        TextView textView1 = (TextView) row.findViewById(R.id.grade);
        textView1.setText(grade.getSubject().getSubjectName());

//        TextView textView2 = (TextView) row.findViewById(R.id.textViewClass);
//        textView2.setText(student.getStudentClass());

        return row;

    }
}
