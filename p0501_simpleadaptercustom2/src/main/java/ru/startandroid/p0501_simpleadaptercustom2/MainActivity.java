package ru.startandroid.p0501_simpleadaptercustom2;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String ATTRIBUTE_NAME_TEXT = "text";
    private static final String ATTRIBUTE_NAME_PB = "pb";
    private static final String ATTRIBUTE_NAME_LL = "ll";

    private ListView lvSimple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int[] load = {41, 48, 22, 35, 30, 67, 51, 88};

        List<Map<String, Object>> data = new ArrayList<>(load.length);
        Map<String, Object> m;
        for (int i = 0; i < load.length; i++) {
            m = new HashMap<>();
            m.put(ATTRIBUTE_NAME_TEXT, "Day " + (i + 1) + ". Load: " + load[i] + "%");
            m.put(ATTRIBUTE_NAME_PB, load[i]);
            m.put(ATTRIBUTE_NAME_LL, load[i]);
            data.add(m);
        }

        String[] from = new String[] {ATTRIBUTE_NAME_TEXT, ATTRIBUTE_NAME_PB, ATTRIBUTE_NAME_LL};
        int[] to = new int[] {R.id.tvLoad, R.id.pbLoad, R.id.lload};

        SimpleAdapter binder = new SimpleAdapter(this, data, R.layout.item, from, to);

        binder.setViewBinder(new MyViewBinder());

        lvSimple = findViewById(R.id.lvSimple);
        lvSimple.setAdapter(binder);
    }

    class MyViewBinder implements SimpleAdapter.ViewBinder {

        int red = getResources().getColor(R.color.Red);
        int orange = getResources().getColor(R.color.Orange);
        int green = getResources().getColor(R.color.Green);

        @Override
        public boolean setViewValue(View view, Object o, String s) {

            int i = 0;
            switch (view.getId()) {
                case R.id.lload:
                    i = ((Integer) o).intValue();
                    if (i < 40) {
                        view.setBackgroundColor(green);
                    } else if (i < 70) {
                        view.setBackgroundColor(orange);
                    }
                        view.setBackgroundColor(red);
                    return true;
                case R.id.pbLoad:
                    i = ((Integer) o).intValue();
                    ((ProgressBar) view).setProgress(i);
                    return true;
            }
            return false;
        }
    }
}