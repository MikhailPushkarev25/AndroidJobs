package ru.startandroid.p0491_simpleadaptercustom1;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String ATTR_NAME_TEXT = "text";
    private static final String ATTR_NAME_VALUE = "value";
    private static final String ATTR_NAME_IMAGE = "image";

    private int positive = android.R.drawable.arrow_up_float;
    private int negative = android.R.drawable.arrow_down_float;

    private ListView lvView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int[] values = {8, 4, -3, 2, -5, 0, 3, -6, 1, -1};

        List<Map<String, Object>> data = new ArrayList<>(values.length);
        Map<String, Object> m;
        int img = 0;

        for (int i = 0; i < values.length; i++) {
            m = new HashMap<>();
            m.put(ATTR_NAME_TEXT, " day " + (i + 1));
            m.put(ATTR_NAME_VALUE, values[i]);
            if (values[i] == 0) img = 0; else
                img = (values[i] > 0) ? positive : negative;
            m.put(ATTR_NAME_IMAGE, img);
            data.add(m);
        }

        String[] from = new String[] {ATTR_NAME_TEXT, ATTR_NAME_VALUE, ATTR_NAME_IMAGE};

        int[] to = new int[] {R.id.tvText, R.id.tvValue, R.id.ivImg};

        MySimpleAdapter adapter = new MySimpleAdapter(
                this,
                data,
                R.layout.item,
                from,
                to
        );

        lvView = findViewById(R.id.lvSimple);
        lvView.setAdapter(adapter);

    }

    class MySimpleAdapter extends SimpleAdapter {

        public MySimpleAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
            super(context, data, resource, from, to);
        }

        @Override
        public void setViewText(TextView v, String text) {
            super.setViewText(v, text);
            if (v.getId() == R.id.tvValue) {
                int i = Integer.parseInt(text);
                if (i < 0) {
                    v.setTextColor(Color.RED);
                } else if (i > 0) {
                    v.setTextColor(Color.GREEN);
                }
            }
        }

        @Override
        public void setViewImage(ImageView v, int value) {
            super.setViewImage(v, value);
            if (value == negative) {
                v.setBackgroundColor(Color.RED);
            } else if (value == positive) {
                v.setBackgroundColor(Color.GREEN);
            }
        }
    }
}