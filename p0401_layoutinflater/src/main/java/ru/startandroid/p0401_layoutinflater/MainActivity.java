package ru.startandroid.p0401_layoutinflater;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "myTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout linearLayout = findViewById(R.id.linLayout);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.text, linearLayout, true);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();

        Log.d(TAG, "Class of view: " + view.getClass().toString());
        Log.d(TAG, "LayoutParams of view is null: " + layoutParams.getClass());

        RelativeLayout relativeLayout = findViewById(R.id.relLayout);
        LayoutInflater inflater1 = getLayoutInflater();
        View view1 = inflater1.inflate(R.layout.text, relativeLayout, true);
        LinearLayout.LayoutParams layoutParams1 = (LinearLayout.LayoutParams) view1.getLayoutParams();

        Log.d(TAG, "Class of view: " + view1.getClass().toString());
        Log.d(TAG, "LayoutParams of view is null: " + layoutParams1.getClass());

    }
}