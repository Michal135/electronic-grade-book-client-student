package com.example.electronic_grade_book_client_student.radarCharts;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.electronic_grade_book_client_student.R;

import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class RadarChartsFragment extends Fragment {

    private RadarChart chart;

    RadarData radarData;
    RadarDataSet radarDataSet;
    ArrayList radarEntries;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.radar_charts_fragment,container,false);
        chart = view.findViewById(R.id.radar_chart);

        getEntries();
        radarDataSet = new RadarDataSet(radarEntries, "");
        radarData = new RadarData(radarDataSet);
        chart.setData(radarData);
        radarDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        radarDataSet.setValueTextColor(Color.BLACK);
        radarDataSet.setValueTextSize(18f);

        return view;
    }
    private void getEntries() {
        radarEntries = new ArrayList<>();
        radarEntries.add(new RadarEntry(0));
        radarEntries.add(new RadarEntry(1));
        radarEntries.add(new RadarEntry(2));
        radarEntries.add(new RadarEntry(3));
        radarEntries.add(new RadarEntry(4));
        radarEntries.add(new RadarEntry(5));
    }
}