package ru.startandroid.p0291_simpleactivityresult;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NameActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvName;
    private Button btnOk;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.name);

        tvName = findViewById(R.id.etName);
        btnOk = findViewById(R.id.btnOK);
        btnOk.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        intent.putExtra("name", tvName.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }
}