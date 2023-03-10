package ru.startandroid.develop.p0161_dynamiclayout;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @SuppressLint({"SetTextI18n", "RtlHardcoded"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams layoutParams =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setContentView(linearLayout, layoutParams);
        LinearLayout.LayoutParams lpView = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView view = new TextView(this);
        view.setText("TextView");
        view.setLayoutParams(lpView);
        linearLayout.addView(view);

        Button button = new Button(this);
        button.setText("Button");
        linearLayout.addView(button, layoutParams);

        LinearLayout.LayoutParams leftParams =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        leftParams.leftMargin = 50;

        Button button1 = new Button(this);
        button1.setText("Button1");
        linearLayout.addView(button1, leftParams);


        LinearLayout.LayoutParams rightParams =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rightParams.gravity = Gravity.RIGHT;

        Button button2 = new Button(this);
        button2.setText("Button2");
        linearLayout.addView(button2, rightParams);
    }
}