package ru.startandroid.develop.p0102_activitylistener;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {

    private TextView text;
    private Button ok;
    private Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.tvOut);
        ok = findViewById(R.id.btnOk);
        cancel = findViewById(R.id.btnCancel);

        ok.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnOk) {
            text.setText("Нажата кнопка ОК");
        } else if (view.getId() == R.id.btnCancel) {
            text.setText("Нажата кнопка Cancel");
        }
    }
}