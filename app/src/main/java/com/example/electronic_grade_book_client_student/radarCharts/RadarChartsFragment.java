package com.example.electronic_grade_book_client_student.radarCharts;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.electronic_grade_book_client_student.BasicAuthInterceptor;
import com.example.electronic_grade_book_client_student.R;

import com.example.electronic_grade_book_client_student.additionalClasses.GradesAverage;
import com.example.electronic_grade_book_client_student.config.ConfigClass;

import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RadarChartsFragment extends Fragment {

    private RadarChart chart;

    private RadarData radarData;
    private RadarDataSet radarDataSet;
    private ArrayList radarEntries;

    private static final float Max = 6, Min = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.radar_charts_fragment, container, false);
        chart = view.findViewById(R.id.radar_chart);

        getActivity().setTitle(R.string.grades_average);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new BasicAuthInterceptor(ConfigClass.getUser(), ConfigClass.getPassword())).build();

        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://192.168.1.15:8080/").client(okHttpClient).addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        radarChartsService Retroservice = retrofit.create(radarChartsService.class);

        Call<List<GradesAverage>> call = Retroservice.getGradesAverageForStudent(ConfigClass.getUser());
        System.out.println("CALL: " + call);
        System.out.println(Retroservice.getGradesAverageForStudent(ConfigClass.getUser()));
        call.enqueue(new Callback<List<GradesAverage>>() {
            @Override
            public void onResponse(Call<List<GradesAverage>> call, Response<List<GradesAverage>> response) {
                if (!response.isSuccessful()) {
                    System.out.println(response.code());
                }
                if (response.code() == 401) {
                    System.out.println("nie masz dostepu");
                }
                List<GradesAverage> allAverages = response.body();
                addAllEntries(allAverages);

                YAxis yAxis = chart.getYAxis();
                yAxis.setAxisMinimum(Min);
                yAxis.setAxisMaximum(Max);//niby ok, ale ustawia mi jeszcze 1 linie po 6, mozna wylaczyc opis, ale jest widoczna
                yAxis.setDrawTopYLabelEntry(false);//nie zaznacza ostatniej linii

                XAxis xAxis = chart.getXAxis();
                xAxis.setTextSize(14f);
                xAxis.setYOffset(0);
                xAxis.setXOffset(0);
                xAxis.setValueFormatter(new IAxisValueFormatter() {
                    private String subjects[] = new String[]{"Polski", "Angielski", "Matematyka", "Fizyka", "Biologia", "Chemia"};

                    @Override
                    public String getFormattedValue(float value, AxisBase axis) {
                        return subjects[(int) value % subjects.length];
                    }
                });

                radarDataSet = new RadarDataSet(radarEntries, "Wykres radarowy średnich arytmetycznych");
                radarDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
                radarDataSet.setValueTextColor(Color.BLACK);
                radarDataSet.setColor(Color.RED);
                radarDataSet.setFillColor(Color.RED);
                radarDataSet.setDrawFilled(true);
                radarDataSet.setDrawHighlightIndicators(false);

                radarData = new RadarData(radarDataSet);
                radarData.setDrawValues(false);

                chart.setData(radarData);
                chart.invalidate();

            }

            @Override
            public void onFailure(Call<List<GradesAverage>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });

        return view;
    }

    private void addAllEntries(List<GradesAverage> allAverages) {
        radarEntries = new ArrayList<>();
        for (GradesAverage averageFromOneSubject : allAverages) {
            radarEntries.add(new RadarEntry((float) averageFromOneSubject.getAverage(), averageFromOneSubject.getSubjectName()));
        }
    }


}