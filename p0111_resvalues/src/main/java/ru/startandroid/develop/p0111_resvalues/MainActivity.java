package ru.startandroid.develop.p0111_resvalues;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout linearLayout = findViewById(R.id.llBottom);
        TextView textView = findViewById(R.id.tvBottom);
        Button button = findViewById(R.id.btnBottom);

        linearLayout.setBackgroundResource(R.color.llBottomColor);
        textView.setText(R.string.tvBottomText);
        button.setText(R.string.btnBottomText);
    }
}