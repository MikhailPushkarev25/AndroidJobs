package ru.startandroid.p0541_customadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    protected List<Product> products = new ArrayList<>();
    protected BoxAdapter boxAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fillData();
        boxAdapter = new BoxAdapter(this, products);
        ListView listView = findViewById(R.id.lvMain);
        listView.setAdapter(boxAdapter);
    }

    protected void fillData() {
        for (int i = 1; i < 20; i++) {
            products.add(new Product("Product " + i, i * 100, R.drawable.ic_launcher_foreground, false));
        }
    }

    public void showResult(View view) {
        String str = "Товары в корзине";
        for (Product p : boxAdapter.getBox()) {
            if (p.box) {
                str += "\n" + p.name;

            }
            Toast.makeText(this, str, Toast.LENGTH_LONG).show();
        }
    }

}