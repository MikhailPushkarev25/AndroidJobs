package ru.startandroid.p0381_sqlitetransaction;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "myTag";

    private DBHelper dbHelper;

    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();

        activiti();
    }

    public void activiti() {
            db = dbHelper.getWritableDatabase();

            SQLiteDatabase db2 = dbHelper.getWritableDatabase();
            Log.d(TAG, "db = db2 -" + db.equals(db2));
            Log.d(TAG, "db open - " + db.isOpen() + ", db2 open -" + db2.isOpen());
            db2.close();
            Log.d(TAG, "db open - " + db.isOpen() + ", db2 open -" + db2.isOpen());
    }

    private void insert(SQLiteDatabase sql, String table, String value) {
        Log.d(TAG, "--- Insert table ----- " + table + " " + value);
        ContentValues cv = new ContentValues();
        cv.put("val", value);
        sql.insert(table, null, cv);
    }

    private void read(SQLiteDatabase sql, String table) {
        Log.d(TAG, "--- Read table ----- " + table);
        Cursor c = sql.query(table, null, null, null, null, null, null);
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    Log.d(TAG, "next to " + c.getCount());
                } while (c.moveToNext());
                c.close();
            }
        }
    }

    private void delete(SQLiteDatabase sql, String table) {
        Log.d(TAG, "--- Delete table ----- " + table);
        sql.delete(table, null, null);
    }

    static class DBHelper extends SQLiteOpenHelper {

        public DBHelper(@Nullable Context context) {
            super(context, "myDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            Log.d(TAG, "------ Create table -------");

            sqLiteDatabase.execSQL(
                    "create table myDB (" +
                            " id integer primary key autoincrement," +
                            " val text);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
}