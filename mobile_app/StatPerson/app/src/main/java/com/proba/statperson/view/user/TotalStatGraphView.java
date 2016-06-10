package com.proba.statperson.view.user;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.proba.statperson.R;

public class TotalStatGraphView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_stat_graph_view);

        GraphView graph = (GraphView) findViewById(R.id.graph);
        graph.setTitle(getResources().getString(R.string.total_stat));
        graph.setTitleTextSize(getResources().getDimension(R.dimen.head_text));
        graph.setTitleColor(getResources().getColor(R.color.colorAccent));

        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
        staticLabelsFormatter.setHorizontalLabels(new String[] {
        getResources().getString(R.string.putin),
        getResources().getString(R.string.medvedev),
        getResources().getString(R.string.navalny),
        });
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);

        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 6),
                new DataPoint(2, 3),
                new DataPoint(4, 1)
        });
        graph.addSeries(series);

    }
}
