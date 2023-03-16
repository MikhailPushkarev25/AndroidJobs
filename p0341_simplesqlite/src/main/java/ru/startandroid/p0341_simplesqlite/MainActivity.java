package ru.startandroid.p0341_simplesqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "myLogs";

    private Button btnRead, btnAdd, btnClear, btnDel, btnUpd;
    private EditText etName, etEmail, etId;

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etId = findViewById(R.id.etID);

        btnRead = findViewById(R.id.btnRead);
        btnAdd = findViewById(R.id.btnAdd);
        btnClear = findViewById(R.id.btnClear);
        btnDel = findViewById(R.id.btnDel);
        btnUpd = findViewById(R.id.btnUpd);

        btnRead.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        btnDel.setOnClickListener(this);
        btnUpd.setOnClickListener(this);

        dbHelper = new DBHelper(this);
    }

    @Override
    public void onClick(View view) {

        ContentValues contentValues = new ContentValues();

        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String id = etId.getText().toString();

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        switch (view.getId()) {
            case R.id.btnAdd:
                Log.d(TAG, "---- insert in mytable -----");

                contentValues.put("name", name);
                contentValues.put("email", email);

                long rowId = db.insert("mytable", null, contentValues);
                Log.d(TAG, "row insertId -> " + rowId);
                break;
            case R.id.btnRead:
                Log.d(TAG, "---- rows in mytable -----");
                Cursor c = db.query("mytable", null, null, null, null, null, null);

                if (c.moveToFirst()) {
                    int idColIndex = c.getColumnIndex("id");
                    int nameColIndex = c.getColumnIndex("name");
                    int emailColIndex = c.getColumnIndex("email");

                    do {
                        Log.d(TAG,
                                "Id = " + c.getInt(idColIndex) + ", " +
                                 "name = " + c.getString(nameColIndex) + ", " +
                                "email = " + c.getString(emailColIndex));
                    } while (c.moveToNext());
                } else
                    Log.d(TAG, "0 rows");
                    c.close();
                    break;
            case R.id.btnClear:
                Log.d(TAG, "---- Clear mytable ------");
                int clearCount = db.delete("mytable", null, null);
                Log.d(TAG, "deleted rows count " + clearCount);
                break;
            case R.id.btnUpd:

                if (id.equalsIgnoreCase("")) {
                    break;
                }
                Log.d(TAG, "---- Update mytable ------");
                contentValues.put("name", name);
                contentValues.put("email", email);

                int updCount = db.update("mytable", contentValues, "id = ?", new String[]{id});
                Log.d(TAG, "---- Updaded rows count -> " + updCount);
                break;
            case R.id.btnDel:
                if (id.equalsIgnoreCase("")) {
                    break;
                }
                Log.d(TAG, "---- Delete mytable ------");
                int delCount = db.delete("mytable", "id = " + id, null);
                Log.d(TAG, "---- Deleted rows count -> " + delCount);
                break;
        }
        dbHelper.close();

    }

    class DBHelper extends SQLiteOpenHelper {

        public DBHelper(@Nullable Context context) {
            super(context, "mydb", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            Log.d(TAG, "------ On create database -------");
            sqLiteDatabase.execSQL(
                    "create table mytable (" +
                            " id integer primary key autoincrement," +
                            " name text," +
                            "email text" +
                            ");"
            );
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
}