package ru.startandroid.p0331_sharedpreferences;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etText;
    private Button btnSave, btnLoad;
    private SharedPreferences sPref;

    private static final String SAVED_TEXT = "saved_text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etText = findViewById(R.id.etText);

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);

        btnLoad = findViewById(R.id.btnLoad);
        btnLoad.setOnClickListener(this);

        loadText();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnSave:
                saveText();
                break;
            case R.id.btnLoad:
                loadText();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        Log.d("TAG", "destroy");
        super.onDestroy();
        saveText();
    }

    private void saveText() {
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        editor.putString(SAVED_TEXT, etText.getText().toString());
        editor.apply();
        Toast.makeText(this, "Text saved", Toast.LENGTH_SHORT).show();
    }

    private void loadText() {
        sPref = getPreferences(MODE_PRIVATE);
        String savedText = sPref.getString(SAVED_TEXT, "");
        etText.setText(savedText);
        Toast.makeText(this, "Text loaded", Toast.LENGTH_SHORT).show();
    }


}