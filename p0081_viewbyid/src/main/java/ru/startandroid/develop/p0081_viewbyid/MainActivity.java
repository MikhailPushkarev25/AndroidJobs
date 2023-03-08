package ru.startandroid.develop.p0081_viewbyid;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView myTextView = findViewById(R.id.myText);
        myTextView.setText("New text in TextView");

        Button button = (Button) findViewById(R.id.myBtn);
        button.setText("My button");
        button.setEnabled(false);

        CheckBox checkBox = findViewById(R.id.myCheck);
        checkBox.setChecked(true);
    }
}