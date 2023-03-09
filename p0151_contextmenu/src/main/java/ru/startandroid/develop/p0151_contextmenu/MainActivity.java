package ru.startandroid.develop.p0151_contextmenu;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Size;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {



    TextView tvColor, tvSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvColor = findViewById(R.id.tvColor);
        tvSize = findViewById(R.id.tvSize);

        tvColor.setOnCreateContextMenuListener(this);
        tvSize.setOnCreateContextMenuListener(this);
    }

    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo info) {

            MenuInflater menuInflater = getMenuInflater();

            if (v.getId() == R.id.tvColor) {
                menuInflater.inflate(R.menu.menus, menu);
            } else if (v.getId() == R.id.tvSize) {
                menuInflater.inflate(R.menu.menusize, menu);
            }

    }

    @SuppressLint("SetTextI18n")
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.red:
                tvColor.setBackgroundColor(Color.RED);
                tvColor.setText("Text color = red");
                break;
            case R.id.green:
                tvColor.setBackgroundColor(Color.GREEN);
                tvColor.setText("Text color = green");
                break;
            case R.id.blue:
                tvColor.setBackgroundColor(Color.BLUE);
                tvColor.setText("Text color = blue");
                break;
            case R.id.size22:
                tvColor.setTextSize(22);
                tvColor.setText("Text size = 22");
                break;
            case R.id.size26:
                tvColor.setTextSize(26);
                tvColor.setText("Text size = 26");
                break;
            case R.id.size30:
                tvColor.setTextSize(30);
                tvColor.setText("Text size = 30");
                break;
        }
        return super.onContextItemSelected(item);
    }
}