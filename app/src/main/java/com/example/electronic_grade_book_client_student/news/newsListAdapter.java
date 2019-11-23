package com.example.electronic_grade_book_client_student.news;


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
import com.example.electronic_grade_book_client_student.model.News;
import com.example.electronic_grade_book_client_student.model.Teacher;

import java.util.List;

public class newsListAdapter extends ArrayAdapter<News> {

    private Context context;
    private List<News> newsList;

    public newsListAdapter(Context context, List<News> newsList){
        super(context, R.layout.row_in_news_list,newsList);
        this.context = context;
        this.newsList = newsList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View row = layoutInflater.inflate(R.layout.row_in_news_list,parent,false);

        News oneNews = newsList.get(position);

        TextView textView = (TextView) row.findViewById(R.id.textViewNews);
        textView.setText(oneNews.getDescription());

        TextView textView1 = (TextView) row.findViewById(R.id.textViewNewsAuthor);
        textView1.setText("Autor: "+oneNews.getAuthor());

        return row;
    }
}
