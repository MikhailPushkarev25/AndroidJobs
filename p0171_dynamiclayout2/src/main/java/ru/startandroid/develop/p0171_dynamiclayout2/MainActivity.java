package ru.startandroid.develop.p0171_dynamiclayout2;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout linearLayout;
    private RadioGroup radioGroup;
    private EditText editText;
    private Button create;
    private Button clear;

    private int wrapContent = LinearLayout.LayoutParams.WRAP_CONTENT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = findViewById(R.id.llMain);
        radioGroup = findViewById(R.id.rgGravity);
        editText = findViewById(R.id.etName);

        create = findViewById(R.id.btnCreate);
        create.setOnClickListener(this);

        clear = findViewById(R.id.btnClear);
        clear.setOnClickListener(this);
    }

    @SuppressLint({"NonConstantResourceId", "RtlHardcoded"})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCreate:
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(wrapContent, wrapContent);
                @SuppressLint("RtlHardcoded") int btnGrafity = Gravity.LEFT;

                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.rbLeft:
                        btnGrafity = Gravity.LEFT;
                        break;
                    case R.id.rbCenter:
                        btnGrafity = Gravity.CENTER_HORIZONTAL;
                        break;
                    case R.id.rbRight:
                        btnGrafity = Gravity.RIGHT;
                        break;
                }
                layoutParams.gravity = btnGrafity;

                Button button = new Button(this);
                button.setText(editText.getText().toString());
                linearLayout.addView(button, layoutParams);
                break;
            case R.id.btnClear:
                linearLayout.removeAllViews();
                Toast.makeText(this, "Удалено", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}