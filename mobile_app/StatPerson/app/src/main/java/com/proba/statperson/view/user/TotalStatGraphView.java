package com.proba.statperson.view.user;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.jjoe64.graphview.GraphView;
import com.proba.statperson.R;

public class TotalStatGraphView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_stat_graph_view);

        // Линейный график
        GraphViewSeries exampleSeries = new GraphViewSeries(
                new GraphViewData[]{new GraphViewData(1, 3.0d),
                        new GraphViewData(2, 1.5d), new GraphViewData(3, 2.5d),
                        new GraphViewData(4, 1.0d), new GraphViewData(5, 1.3d)});

        GraphView graphView = new LineGraphView(this, "График каких-то данных");
        graphView.addSeries(exampleSeries);

        LinearLayout layout = (LinearLayout) findViewById(R.id.total_stat_graph_view);
        layout.addView(graphView);

    }
}
