package ru.startandroid.develop.p0121_logandmess;

import android.app.Activity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {

    private TextView text;
    private Button ok;
    private Button cancel;

    private static final String TAG = "MyLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "Найти информацию по id");
        text = findViewById(R.id.tvOut);
        ok = findViewById(R.id.btnOk);
        cancel = findViewById(R.id.btnCancel);

        Log.d(TAG, "Дать обработку кнопкам");
        ok.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Log.d(TAG, "Распределение по условию нажатия кнопок");
        if (view.getId() == R.id.btnOk) {
            text.setText("Нажата кнопка ОК");
            Toast.makeText(this, "Нажата кнопка ОК", Toast.LENGTH_LONG).show();
        } else if (view.getId() == R.id.btnCancel) {
            text.setText("Нажата кнопка Cancel");
            Toast.makeText(this, "Нажата кнопка Cancel", Toast.LENGTH_LONG).show();
        }
    }
}