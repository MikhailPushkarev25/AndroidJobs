package ru.startandroid.p0371_sqliteinnerjoin;

import android.annotation.SuppressLint;
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

public class MainActivity extends AppCompatActivity {

    public final static String TAG = "myTag";

    public static int[] position_id = {1, 2, 3, 4};

    public static String[] position_name = {"Директор", "Программер", "Бухгалтер", "Охранник"};

    public static int[] position_salary = {15000, 13000, 10000, 8000};

    public static String[] people_name = {"Иван", "Петр", "Марья", "Антон", "Даша", "Борис", "Костя", "Игорь"};

    public static int[] people_posid = {2, 3, 2, 2, 3, 1, 2, 4};





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor c;

        Log.d(TAG, "--- Table position ---");
        c = db.query("position", null, null, null, null, null, null);
        logCursor(c);
        c.close();
        Log.d(TAG, "----- ------");

        Log.d(TAG, "----- Table people -------");
        c = db.query("people", null, null, null, null, null, null);
        logCursor(c);
        c.close();
        Log.d(TAG, "------ ------");

        Log.d(TAG, "---- INNER JOIN with join raw query -------");
        String rawQuery = "select PL.name as Name, PS.name as Position, salary as Salary " +
                " from people as PL " +
                " inner join position as PS " +
                " on PL.posid = PS.id " +
                " where salary > ?";
        c = db.rawQuery(rawQuery, new String[]{"12000"});
        logCursor(c);
        c.close();
        Log.d(TAG, "---- -----");

        Log.d(TAG, "---- INNER JOIN with join query ------");
        String table = "people as PL inner join position as PS on PL.posid = PS.id";
        String[] colums = {"PL.name as Name", "PS.name as Position", "salary as Salary"};
        String selection = "salary < ?";
        String[] selectionArgs = {"12000"};
        c = db.query(table, colums, selection, selectionArgs, null, null, null);
        logCursor(c);
        c.close();
        Log.d(TAG, "---- -----");
        dbHelper.close();
    }

    @SuppressLint("Range")
    private void logCursor(Cursor c) {
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
        }
    }

    static class DBHelper extends SQLiteOpenHelper {

        public DBHelper(@Nullable Context context) {
            super(context, "myDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

            Log.d(TAG, "------On Create Database-------");

            ContentValues cv = new ContentValues();

            sqLiteDatabase.execSQL(
                    "create table position (" +
                    " id integer primary key," +
                    " name text," +
                    " salary integer);");

            for (int i = 0; i < position_id.length; i++) {
                cv.clear();
                cv.put("id", position_id[i]);
                cv.put("name", position_name[i]);
                cv.put("salary", position_salary[i]);
                sqLiteDatabase.insert("position", null, cv);
            }

            sqLiteDatabase.execSQL(
                    "create table people (" +
                            " id integer primary key autoincrement," +
                            " name text," +
                            " posid integer);");

            for (int i = 0; i < people_name.length; i++) {
                cv.clear();
                cv.put("name", people_name[i]);
                cv.put("posid", people_posid[i]);
                sqLiteDatabase.insert("people", null, cv);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
}