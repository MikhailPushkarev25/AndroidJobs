package ru.startandroid.p0361_sqlitequery;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final static String TAG = "MY LOG";

    private String name[] = {
            "Китай", "США", "Бразилия", "Россия", "Япония",
            "Германия", "Египет", "Италия", "Франция", "Канада"
    };

    int people[] = {1400, 311, 195, 142, 128, 82, 80, 60, 66, 35};

    String[] region = {
            "Азия", "Америка", "Америка", "Европа",
            "Азия", "Европа", "Африка", "Европа", "Европа", "Америка"
    };

    private Button btnAll, btnFunc, btnPeople, btnGroup, btnHaving, btnSort;
    private EditText etFunc, etPeople, etRegionPeople;
    private RadioGroup rgSort;

    private DBHelper dbHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAll = findViewById(R.id.btnAll);
        btnFunc = findViewById(R.id.btnFunc);
        btnPeople = findViewById(R.id.btnPeople);
        btnGroup = findViewById(R.id.btnGroup);
        btnHaving = findViewById(R.id.btnHaving);
        btnSort = findViewById(R.id.btnSort);

        btnAll.setOnClickListener(this);
        btnFunc.setOnClickListener(this);
        btnPeople.setOnClickListener(this);
        btnGroup.setOnClickListener(this);
        btnHaving.setOnClickListener(this);
        btnSort.setOnClickListener(this);

        etFunc = findViewById(R.id.etFunc);
        etPeople = findViewById(R.id.etPeople);
        etRegionPeople = findViewById(R.id.etRegionPeople);

        rgSort = findViewById(R.id.rgSort);

        dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();

        Cursor c = db.query("mytables", null, null, null, null, null, null);
        if (c.getCount() == 0) {
            ContentValues cv = new ContentValues();

            for (int i = 0; i < 10; i++) {
                cv.put("name", name[i]);
                cv.put("people", people[i]);
                cv.put("region", region[i]);
                Log.d(TAG, "id = " + db.insert("mytables", null, cv));
            }
        }
        c.close();
        dbHelper.close();
        onClick(btnAll);
    }

    @SuppressLint("Range")
    @Override
    public void onClick(View view) {

        db = dbHelper.getWritableDatabase();

        String sFunc = etFunc.getText().toString();
        String sPeople = etPeople.getText().toString();
        String sRegionPeople = etRegionPeople.getText().toString();

        String[] colums = null;
        String selection = null;
        String[] selectionArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = null;

        Cursor c = null;

        switch (view.getId()) {
            // Все записи
            case R.id.btnAll:
                Log.d(TAG, "-----Все записи-----");
                c = db.query("mytables", null, null, null, null, null, null);
                break;
            // Функция
            case R.id.btnFunc:
                Log.d(TAG, "-----Функция " + sFunc + "----");
                colums = new String[]{sFunc};
                c = db.query("mytables", colums, null,  null, null, null, null);
                break;
                // Население больше, чем
            case R.id.btnPeople:
                Log.d(TAG, "-----Население больше " + sPeople + "-----");
                selection = "people > ?";
                selectionArgs = new String[]{sPeople};
                c = db.query("mytables", null, selection, selectionArgs, null, null, null);
                break;
                // Население по региону
            case R.id.btnGroup:
                Log.d(TAG, "-----Население по региону-----");
                colums = new String[]{"region", "sum(people) as people"};
                groupBy = "region";
                c = db.query("mytables", colums, null, null, groupBy, null, null);
                break;
                // Население по региону больше чем
            case  R.id.btnHaving:
                Log.d(TAG, "-----Регионы с населением больше чем " + sRegionPeople + "-----");
                colums = new String[] {"region", "sum(people) as people"};
                groupBy = "region";
                having = "sum(people) > " + sRegionPeople;
                c = db.query("mytables", colums, null, null, groupBy, having, null);
                break;
                // Сортировка
            case R.id.btnSort:
                switch (rgSort.getCheckedRadioButtonId()) {
                    case R.id.rName:
                        Log.d(TAG, "Сортировка по наименования");
                        orderBy = "name";
                        break;
                    case R.id.rPeople:
                        Log.d(TAG, "Сортировка по насилению");
                        orderBy = "people";
                        break;
                    case R.id.rRegion:
                        Log.d(TAG, "Сортировка по региону");
                        orderBy = "region";
                        break;
                }
                c = db.query("mytables",  null, null, null, null, null, orderBy);
                break;
        }

        if (c != null) {
            if (c.moveToFirst()) {
                String str;
                do {
                    str = "";
                    for (String cn : c.getColumnNames()) {
                        str = str.concat(cn + " = " + c.getString(c.getColumnIndex(cn)) + "; ");
                    }
                    Log.d(TAG, str);
                } while (c.moveToNext());
            }
            c.close();
        } else
            Log.d(TAG, "Cursor is null");
        dbHelper.close();
    }

    static class DBHelper extends SQLiteOpenHelper {

        public DBHelper(@Nullable Context context) {
            super(context, "myDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            Log.d(TAG, "------- On create database --------");

            sqLiteDatabase.execSQL("create table mytables (" +
                    " id integer primary key autoincrement," +
                    " name text," +
                    " people integer," +
                    " region text" +
                    " );");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
}