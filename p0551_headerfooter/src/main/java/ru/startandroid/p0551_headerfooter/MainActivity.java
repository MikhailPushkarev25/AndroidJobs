package ru.startandroid.p0551_headerfooter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.HeaderViewListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String MYTAG = "myTag";

    protected String[] data = {"one", "two", "three", "four", "five"};

    protected ListView lvMain;

    protected ArrayAdapter<String> adapter;

    protected View header1;
    protected View header2;

    protected View footer1;
    protected View footer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvMain = findViewById(R.id.lvMain);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);

        header1 = createHeader("header 1");
        header2 = createHeader("header 2");

        footer1 = createFooter("footer 1");
        footer2 = createFooter("footer 2");

        fillList();
    }

    public void fillList() {
        lvMain.addHeaderView(header1);
        lvMain.addHeaderView(header2, "Some text for header 2", false);
        lvMain.addFooterView(footer1);
        lvMain.addFooterView(footer2,  "Some text for footer 2", false);
        lvMain.setAdapter(adapter);
    }

    public void onClick(View view) {
       Object obj;
        HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) lvMain.getAdapter();
        obj = headerViewListAdapter.getItem(1);
        Log.d(MYTAG, "headerViewListAdapter.getItem(1) " + obj.toString());
        obj = headerViewListAdapter.getItem(4);
        Log.d(MYTAG, "headerViewListAdapter.getItem(4) " + obj.toString());

        ArrayAdapter<String> arrayAdapter = (ArrayAdapter<String>) headerViewListAdapter.getWrappedAdapter();
        obj = arrayAdapter.getItem(1);
        Log.d(MYTAG, "arrayAdapter.getItem(1) " + obj.toString());
        obj = arrayAdapter.getItem(4);
        Log.d(MYTAG, "arrayAdapter.getItem(4) " + obj.toString());
    }

    public View createHeader(String text) {
        View v = getLayoutInflater().inflate(R.layout.header, null);
        ((TextView) v.findViewById(R.id.tvText)).setText(text);
        return v;
    }

    public View createFooter(String text) {
        View v = getLayoutInflater().inflate(R.layout.footer, null);
        ((TextView) v.findViewById(R.id.tvText)).setText(text);
        return v;
    }

}