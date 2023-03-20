package ru.startandroid.p0461_expandablelistevents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private AndroidHelper ah;
    private ExpandableListView expandableListView;
    private SimpleExpandableListAdapter simpleExpandableListAdapter;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.tvInfo);

        ah = new AndroidHelper(this);
        simpleExpandableListAdapter = ah.getAdapter();

        expandableListView = findViewById(R.id.elvMain);
        expandableListView.setAdapter(simpleExpandableListAdapter);

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                Log.d("MyTag", "onChildClik groupPosition: " + i + ","
                + "child " + i1 + ", id " + l);
                textView.setText(ah.getGroupElement(i, i1));
                return false;
            }
        });

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                Log.d("MyTag", "onGroupClik groupPosition: " + i + ", id " + l);
                if (i == 1) return true;
                return false;
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int i) {
                Log.d("MyTag", "onGroupCollapse groupPosition: " + i);
                textView.setText("Свернули: " + ah.getGroupText(i));
            }
        });

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int i) {
                Log.d("MyTag", "setOnGroupExpandListener groupPosition: " + i);
                textView.setText("Развернули: " + ah.getGroupText(i));
            }
        });
        expandableListView.expandGroup(2);
    }
}