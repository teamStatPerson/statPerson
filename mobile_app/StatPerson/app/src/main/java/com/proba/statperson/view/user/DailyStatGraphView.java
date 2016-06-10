package com.proba.statperson.view.user;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.proba.statperson.R;

import java.util.Calendar;
import java.util.Date;

public class DailyStatGraphView extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_stat_graph_view);

        Calendar calendar = Calendar.getInstance();
        Date d1 = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        Date d2 = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        Date d3 = calendar.getTime();

        GraphView graph = (GraphView) findViewById(R.id.graph);
        BarGraphSeries<DataPoint> series = new BarGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(d1, 6),
                new DataPoint(d2, 3),
                new DataPoint(d3, 1)
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

            // set date label formatter
            graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(this));
            graph.getGridLabelRenderer().setNumHorizontalLabels(3); // only 4 because of the space

// set manual x bounds to have nice steps
            graph.getViewport().setMinX(d1.getTime());
            graph.getViewport().setMaxX(d3.getTime());
            graph.getViewport().setXAxisBoundsManual(true);
            graph.setTitle(getResources().getString(R.string.putin));
            graph.setTitleTextSize(getResources().getDimension(R.dimen.head_text));
            graph.setTitleColor(getResources().getColor(R.color.colorAccent));
            graph.addSeries(series);
        }
        // draw values on top
        series.setDrawValuesOnTop(true);
        series.setValuesOnTopColor(Color.RED);
        series.setValuesOnTopSize(getResources().getDimension(R.dimen.normal_text));
    }
}
