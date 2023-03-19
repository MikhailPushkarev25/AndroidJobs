package ru.startandroid.p0441_simplelistevents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private static final String Tag = "myLogs";

    private ListView lstMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstMain = findViewById(R.id.lvMain);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.names,
                android.R.layout.simple_expandable_list_item_1
        );

        lstMain.setAdapter(adapter);

        lstMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(Tag, "itemClick: position = " + i + ", id = " + l);
            }
        });

        lstMain.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(Tag, "itemSelectedClick: position = " + i + ", id = " + l);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Log.d(Tag, "itemSelectedClick: nothing");
            }
        });

        lstMain.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                Log.d(Tag, "scroll: scrollState = " + i);
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                //Log.d(Tag, "scroll: firstVisibleItem = " + i + ", i1 = " + i1 + " i2 = " + i2);
            }
        });
    }
}