package com.proba.statperson.view.user;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.proba.statperson.R;
import com.proba.statperson.util.TotalStatHashMap;

import java.util.ArrayList;

public class TotalStatGraphView extends AppCompatActivity {

    String[] labels;
    ArrayList<TotalStatHashMap> totalStatList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_stat_graph_view);

        GraphView graph = (GraphView) findViewById(R.id.graph);
        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
        staticLabelsFormatter.setHorizontalLabels(
                new String[]{
                        getResources().getString(R.string.putin),
                        getResources().getString(R.string.medvedev),
                        getResources().getString(R.string.navalny)
                });
        BarGraphSeries<DataPoint> series = new BarGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(0, 6),
                new DataPoint(1, 3),
                new DataPoint(2, 1)
        });
        series.setSpacing(10);
        // styling
        series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {
                return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
            }
        });
        if (graph != null) {
            graph.setTitle(getResources().getString(R.string.total_stat));
            graph.setTitleTextSize(getResources().getDimension(R.dimen.head_text));
            graph.setTitleColor(getResources().getColor(R.color.colorAccent));
            graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
            graph.addSeries(series);
            graph.getViewport().setXAxisBoundsManual(true);
            graph.getViewport().setMinX(0);
            graph.getViewport().setMaxX(2);
        }
        // draw values on top
        series.setDrawValuesOnTop(true);
        series.setValuesOnTopColor(Color.RED);
        series.setValuesOnTopSize(getResources().getDimension(R.dimen.normal_text));
    }
}
