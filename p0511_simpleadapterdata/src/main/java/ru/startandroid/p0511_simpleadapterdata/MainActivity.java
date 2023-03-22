package ru.startandroid.p0511_simpleadapterdata;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;

public class MainActivity extends AppCompatActivity {

    private static final int CM_DELETE_ID = 1;

    private static final String ATTRIBUTE_TEXT = "text";
    private static final String ATTRIBUTE_IMG = "img";

    private ListView lvSimple;
    private SimpleAdapter adapter;
    private List<Map<String, Object>> data;
    private Map<String, Object> m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            m = new HashMap<>();
            m.put(ATTRIBUTE_TEXT, "SomeText " + i);
            m.put(ATTRIBUTE_IMG, R.drawable.ic_launcher_foreground);
            data.add(m);
        }


        String[] from = new String[] {ATTRIBUTE_TEXT, ATTRIBUTE_IMG};
        int[] to = new int[] {R.id.tvText, R.id.ivImg};

        adapter = new SimpleAdapter(this, data, R.layout.item, from, to);

        lvSimple = findViewById(R.id.lvSimple);
        lvSimple.setAdapter(adapter);

        registerForContextMenu(lvSimple);
    }

    public void onButtonClick(View v) {

            m = new HashMap<>();
            m.put(ATTRIBUTE_TEXT, "SomeText " + (data.size() + 1));
            m.put(ATTRIBUTE_IMG, R.drawable.ic_launcher_foreground);
            data.add(m);
            adapter.notifyDataSetChanged();
    }



    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, CM_DELETE_ID, 0, "Удалить запись");
        menu.add(0, adapter.getCount(), 0, "Редактировать запись");
    }



    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == CM_DELETE_ID) {
            AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            data.remove(acmi.position);
            adapter.notifyDataSetChanged();
            return true;
        }

        return super.onContextItemSelected(item);
    }
}