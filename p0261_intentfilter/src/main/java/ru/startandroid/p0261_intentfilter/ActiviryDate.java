package ru.startandroid.p0261_intentfilter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ActiviryDate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date);

        TextView textView = findViewById(R.id.tvDate);

        SimpleDateFormat sdf = new SimpleDateFormat("dd:MM:yyyy");
        String date = sdf.format(new Date(System.currentTimeMillis()));
        textView.setText(date);
    }
}