package ru.startandroid.p0391_sqliteonupgradedb;

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

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "myTag";

    private static final String DB_NAME = "staff";

    private static final int DB_VERSION = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Log.d(TAG, "---- Staff db.v" + db.getVersion() + " -------");
        writeStaff(db);
        dbHelper.close();
    }

    private void writeStaff(SQLiteDatabase db) {
        Cursor c = db.rawQuery("SELECT * FROM people", null);
        LogCursor(c, "Table people");
        c.close();

        c = db.rawQuery("SELECT * FROM position", null);
        LogCursor(c, "Table position");
        c.close();

        String sqlQuery = "select PL.name as Name, PS.name as Position, salary as Salary " +
                " from people PL inner join position PS on PL.posid = PS.id";
        c = db.rawQuery(sqlQuery, null);
        LogCursor(c, "Inner join");
        c.close();
    }

    @SuppressLint("Range")
    private void LogCursor(Cursor c, String table_people) {
        if (c != null) {
            if (c.moveToFirst()) {
                Log.d(TAG, table_people + ". " + c.getCount() + " rows");
                StringBuilder sb = new StringBuilder();
                do {
                    sb.setLength(0);
                    for (String cn : c.getColumnNames()) {
                        sb.append(cn)
                                .append(" = ")
                                .append(c.getString(c.getColumnIndex(cn)))
                                .append(";");
                    }
                    Log.d(TAG, sb.toString());
                } while (c.moveToNext());
            }
        } else
            Log.d(TAG, table_people + ". Cursor is null");
    }

    class DBHelper extends SQLiteOpenHelper {

        public DBHelper(@Nullable Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            Log.d(TAG, "----- On create database ------");

            String[] people_name = {"Nick", "Mike", "Alex", "Vlad", "Flux", "Oleg", "Georgy", "Roman"};
            int[] people_posid = {2, 3, 2, 2, 3, 1, 2, 4};
            int[] position_id = {1, 2, 3, 4};
            String[] position_name = {"Director", "Programmer", "Buhgalter", "Ohrana"};
            int[] position_salary = {15000, 13000, 10000, 8000};

            ContentValues cv = new ContentValues();

           sqLiteDatabase.execSQL(
                   "create table position(" +
                           " id integer primary key," +
                           " name text, salary integer" +
                           " );");

           for (int i = 0; i < position_id.length; i++) {
               cv.clear();
               cv.put("id", position_id[i]);
               cv.put("name", position_name[i]);
               cv.put("salary", position_salary[i]);
               sqLiteDatabase.insert("position", null, cv);
           }

           sqLiteDatabase.execSQL("create table people(" +
                   " id integer primary key," +
                   " name text," +
                   " posid integer);");

           for (int i = 0; i < people_posid.length; i++) {
               cv.clear();
               cv.put("name", people_name[i]);
               cv.put("posid", people_posid[i]);
               sqLiteDatabase.insert("people", null, cv);
           }
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            Log.d(TAG, "----On upGrade database from " + i + " to " + i1 + " to version");

            if (i == 1 && i1 == 2) {
                 ContentValues cv =  new ContentValues();

                 int[] position_id = {1, 2, 3, 4};
                 String[] position_name = {"Director", "Programmer", "Buhgalter", "Ohrana"};
                 int[] position_salary = {15000, 13000, 10000, 8000};

                 sqLiteDatabase.beginTransaction();

                 try {
                      sqLiteDatabase.execSQL(
                              "create table position(" +
                                      " id integer primary key autoincrement," +
                                      " name text," +
                                      " salary integer);");

                      for (int pos = 0; pos < position_id.length; pos++) {
                          cv.clear();
                          cv.put("id", position_id[pos]);
                          cv.put("name", position_name[pos]);
                          cv.put("salary", position_salary[pos]);
                          sqLiteDatabase.insert("position", null, cv);
                      }

                      sqLiteDatabase.execSQL("alter table people add column posid integer");

                      for (int posid = 0; posid < position_id.length; posid++) {
                          cv.clear();
                          cv.put("posid", position_id[posid]);
                          sqLiteDatabase.update("people", cv, "position = ?",
                                  new String[] {position_name[posid]});
                      }

                      sqLiteDatabase.execSQL("create temp table people_tmp(" +
                              " id integer," +
                              " name txt," +
                              " position text," +
                              " posid integer);");

                      sqLiteDatabase.execSQL("insert into people_tmp (id, name, position, posid) " +
                              " select id, name, position, posid from people;");
                      sqLiteDatabase.execSQL("drop table people;");

                      sqLiteDatabase.execSQL("create table people(" +
                              " id integer primary key autoincrement," +
                              " name text," +
                              " posid integer);");

                      sqLiteDatabase.execSQL("insert into people (id, name, posid) " +
                              " select id, name, posid from people_tmp;");
                      sqLiteDatabase.execSQL("drop table people_tmp;");

                      sqLiteDatabase.setTransactionSuccessful();
                 } finally {
                     sqLiteDatabase.endTransaction();
                 }
            }
        }
    }
}