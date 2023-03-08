package ru.startandroid.develop.p0101_listener;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Button ok;
    private Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.tvOut);
        ok = findViewById(R.id.btnOk);
        cancel = findViewById(R.id.btnCancel);

        View.OnClickListener onClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.btnOk) {
                    textView.setText("Нажата кнопка ОК");
                } else if (view.getId() == R.id.btnCancel) {
                    textView.setText("Нажата кнопка Cancel");
                }
            }
        };
        ok.setOnClickListener(onClick);
        cancel.setOnClickListener(onClick);
    }
}