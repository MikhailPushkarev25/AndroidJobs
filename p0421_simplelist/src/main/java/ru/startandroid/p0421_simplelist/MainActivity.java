package ru.startandroid.p0421_simplelist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private final String[] names = {"Mike", "Liza", "Ksenia", "Kevin", "Nik", "Olga",
    "Petr", "Nikolos", "Maria", "Vasy", "Alex", "Stefania", "Luiza"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.lvMain);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.my_list_item, names);

        listView.setAdapter(adapter);
    }
}