package ru.startandroid.develop.p0131_menusimple;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Menu1");
        menu.add("Menu2");
        menu.add("Menu3");
        menu.add("Menu4");
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menu) {
        Toast.makeText(this, menu.getTitle(), Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(menu);
    }
}