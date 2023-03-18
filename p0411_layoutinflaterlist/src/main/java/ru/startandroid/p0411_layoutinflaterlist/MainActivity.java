package ru.startandroid.p0411_layoutinflaterlist;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String[] position_name = {"Mike", "Nik", "Vlad", "Roman", "Ksenia", "Oleg", "Vasy", "Koly",
            "Elena", "Vika", "Olga", "Oksana", "Vlada", "Org", "Petr", "Vova"};
    private String[] position_D = {"Programmer", "Director", "Meneger", "Ohrana", "Police", "Programmer", "Povar", "Programmer",
            "Programmer", "Director", "Meneger", "Ohrana", "Police", "Programmer", "Povar", "Programmer"};
    private int[] position_salary = {130000, 300000, 120000, 50000, 34000, 250000, 90000, 130000,
            130000, 300000, 120000, 50000, 34000, 250000, 90000, 130000};

    private int[] color = new int[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        color[0] = Color.parseColor("#559966CC");
        color[1] = Color.parseColor("#55336699");

        LinearLayout linearLayout = findViewById(R.id.linLayout);

        LayoutInflater inflater = getLayoutInflater();

        for (int i = 0; i < position_name.length; i++) {
            Log.d("Mytag", "i = " + i);
            View item = inflater.inflate(R.layout.item, linearLayout, false);
            TextView textView = item.findViewById(R.id.tvName);
            textView.setText(position_name[i]);
            TextView textView1 = item.findViewById(R.id.tvPosition);
            textView1.setText("Должность: " + position_D[i]);
            TextView textView2 = item.findViewById(R.id.tvSalary);
            textView2.setText("Довольствие: " + String.valueOf(position_salary[i]));
            item.getLayoutParams().width = LinearLayout.LayoutParams.MATCH_PARENT;
            item.setBackgroundColor(color[i % 2]);
            linearLayout.addView(item);
        }
    }
}