package ru.startandroid.p0431_simplelistchoice;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.text.ChoiceFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final static String TAG = "MyTag";

    private ListView listView;
    private String[] names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.lstItem);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.names,
                android.R.layout.simple_list_item_multiple_choice
        );
        listView.setAdapter(adapter);

        Button btnChecked = findViewById(R.id.btnChecked);
        btnChecked.setOnClickListener(this);

        names = getResources().getStringArray(R.array.names);
    }

    @Override
    public void onClick(View view) {
        Log.d(TAG, "Checked: ");
        SparseBooleanArray sparseBooleanArray = listView.getCheckedItemPositions();
        for (int i = 0; i < sparseBooleanArray.size(); i++) {
            int key = sparseBooleanArray.keyAt(i);
            if (sparseBooleanArray.get(key)) {
                Log.d(TAG, names[key]);
            }
        }
    }
}