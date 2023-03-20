package ru.startandroid.p0481_simpleadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String ATRIBUTE_NAME_TEXT = "text";
    private static final String ATRIBUTE_NAME_CHECKED = "checked";
    private static final String ATRIBUTE_NAME_IMAGE = "image";

    private ListView lvSimple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] texts = {"someText 1", "someText 2", "someText 3", "someText 4", "someText 5"};
        boolean[] checked = {true, false, false, true, false};
        int img = R.drawable.ic_launcher_foreground;

        List<Map<String, Object>> data = new ArrayList<>(texts.length);

        Map<String, Object> m;

        for (int i = 0; i < texts.length; i++) {
            m = new HashMap<>();
            m.put(ATRIBUTE_NAME_TEXT, texts[i]);
            m.put(ATRIBUTE_NAME_CHECKED, checked[i]);
            m.put(ATRIBUTE_NAME_IMAGE, img);
            data.add(m);
        }

        String[] from = {ATRIBUTE_NAME_TEXT, ATRIBUTE_NAME_CHECKED, ATRIBUTE_NAME_IMAGE};
        int[] to = {R.id.tvText, R.id.tvCheck, R.id.ivImg, R.id.tvCheck};

        SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.item, from, to);
        lvSimple = findViewById(R.id.lvSimple);
        lvSimple.setAdapter(adapter);
    }
}